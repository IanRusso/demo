package com.irusso.playingdocker.loaders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.irusso.playingdocker.constants.DataType;
import com.irusso.playingdocker.constants.WealthVariables;
import com.irusso.playingdocker.model.display.EconomicReport;
import com.irusso.playingdocker.model.display.CountryWealthDataset;
import com.irusso.playingdocker.model.display.CountryWealthDetails;
import com.irusso.playingdocker.model.oecd.Data;
import com.irusso.playingdocker.http.HttpClient;
import com.irusso.playingdocker.model.oecd.IdNamePair;
import com.irusso.playingdocker.model.oecd.WealthResponse;
import com.irusso.playingdocker.readers.OecdReader;
import com.irusso.playingdocker.redis.Redis;
import com.irusso.playingdocker.service.OecdService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class OecdLoader {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final Properties config;
    private final OecdService oecdService;
    private final Redis redis;

    public OecdLoader(Properties config, Redis redis) {
        this.config = config;
        this.oecdService = new OecdService(config, new HttpClient());
        this.redis = redis;
    }

    public void updateRedis() {
        WealthResponse wealthResponse = oecdService.getWealthDistribution();
        //ForestResponse forestResources = oecdService.getForestResources();

        List<String> countryIds = loadCountriesIntoRedis(wealthResponse);
        loadVariablesIntoRedis(wealthResponse);
        Map<String, EconomicReport> economicReports = getEconomicReportForCountries(wealthResponse, countryIds);
        economicReports.forEach((k,v) -> {
            try {
                redis.insert(DataType.DISPLAY_DATA + k,objectMapper.writeValueAsString(v));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
    }

    private Map<String, EconomicReport> getEconomicReportForCountries(WealthResponse wealthResponse,
                                                                      List<String> countryIds) {
        Map<String, EconomicReport> reports = new HashMap<>();
        for (String countryId : countryIds) {
            Map<Integer, Double> wealthInOnePercent = OecdReader.readVariableForCountry(
                wealthResponse, countryId, WealthVariables.WEALTH_IN_TOP_ONE_PERCENT, redis);

            Map<Integer, Double> wealthInFivePercent = OecdReader.readVariableForCountry(
                wealthResponse, countryId, WealthVariables.WEALTH_IN_TOP_FIVE_PERCENT, redis);

            Map<Integer, Double> wealthInTenPercent = OecdReader.readVariableForCountry(
                wealthResponse, countryId, WealthVariables.WEALTH_IN_TOP_TEN_PERCENT, redis);

            Map<Integer, Double> indebtedHouseholds = OecdReader.readVariableForCountry(
                wealthResponse, countryId, WealthVariables.INDEBTED_HOUSEHOLDS, redis);


            reports.put(getCountryName(countryId), new EconomicReport(wealthInOnePercent,
                wealthInFivePercent, wealthInTenPercent, indebtedHouseholds));
        }
        return reports;
    }

    private List<String> loadCountriesIntoRedis(WealthResponse response) {
        List<String> countryIds = new ArrayList<>();
        List<IdNamePair> orderedCountries = response.getStructure().getDimensions().getSeriesList().get(0).getValues();
        for (int countryId = 0; countryId < orderedCountries.size(); countryId++) {
            redis.insert(DataType.COUNTRY_NAME + countryId, orderedCountries.get(countryId).getName());
            countryIds.add(String.valueOf(countryId));
        }
        return countryIds;
    }

    private void loadDatasetsIntoRedis(WealthResponse wealthResponse, List<String> countryIds) {
        Map<String, Data> series = wealthResponse.getDataSets().get(0).getSeries();
        for (String countryId : countryIds) {
            List<String> keys = series.keySet()
                .stream()
                .filter(key -> key.startsWith(countryId))
                .collect(Collectors.toList());


            CountryWealthDetails details = new CountryWealthDetails();
            details.setCountryName(getCountryName(countryId));

            for (String key : keys) {
                CountryWealthDataset<List<Object>> dataset = new CountryWealthDataset<>();
                dataset.setVariableName(getVarName(key.split(":")[1]));
                dataset.setPopulation("Total Population");
                dataset.setData(series.get(key).getObservations());
                details.getDatasets().add(dataset);
            }
            try {
                redis.insert(DataType.DATASET + countryId, objectMapper.writeValueAsString(details));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadVariablesIntoRedis(WealthResponse response) {
        List<IdNamePair> variables = response.getStructure().getDimensions().getSeriesList().get(1).getValues();
        for (int varId = 0; varId < variables.size(); varId++) {
            redis.insert(DataType.VARIABLE_NAME + varId, variables.get(varId).getName());
        }
    }

    private String getCountryName(String countryId) {
        return redis.read(DataType.COUNTRY_NAME + countryId);
    }

    private String getVarName(String varId) {
        return redis.read(DataType.VARIABLE_NAME + varId);
    }

    private void listByCountry(WealthResponse response) {
        List<IdNamePair> orderedCountries = response.getStructure().getDimensions().getSeriesList().get(0).getValues();

        for (int countryId = 0; countryId < orderedCountries.size(); countryId++) {
            IdNamePair country = orderedCountries.get(countryId);
            System.out.println(country.getName() + " (" + countryId + ")");

            for (Map.Entry<String, Data> x : response.getDataSets().get(0).getSeries().entrySet()) {
                if (x.getKey().matches(countryId + ".*")) {
                    System.out.println(x.getKey() + " || " + x.getValue());
                }
            }

            System.out.println("--------------------\n\n");
        }
    }

    private void listByVariable(WealthResponse response) {
        List<IdNamePair> variables = response.getStructure().getDimensions().getSeriesList().get(1).getValues();

        for (int varId = 0; varId < variables.size(); varId++) {
            IdNamePair variable = variables.get(varId);
            System.out.println(variable.getName() + " (" + varId + ")");

            for (Map.Entry<String, Data> x : response.getDataSets().get(0).getSeries().entrySet()) {
                if (x.getKey().matches(".*" + ":" + varId + ":.*")) {
                    System.out.println(x.getKey() + " || " + x.getValue());
                }
            }

            System.out.println("--------------------\n\n");
        }
    }
}
