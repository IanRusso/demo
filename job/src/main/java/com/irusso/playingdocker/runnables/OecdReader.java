package com.irusso.playingdocker.runnables;

import com.irusso.playingdocker.model.oecd.Data;
import com.irusso.playingdocker.http.HttpClient;
import com.irusso.playingdocker.model.oecd.IdNamePair;
import com.irusso.playingdocker.model.oecd.WealthResponse;
import com.irusso.playingdocker.redis.Redis;
import com.irusso.playingdocker.service.OecdService;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class OecdReader {

    private static final String CTRY = "CTRY_";
    private static final String VAR = "VAR_";

    private final Properties config;
    private final OecdService oecdService;
    private final Redis redis;

    public OecdReader(Properties config, Redis redis) {
        this.config = config;
        this.oecdService = new OecdService(config, new HttpClient());
        this.redis = redis;
    }

    public void read() {
        WealthResponse response = oecdService.getWealthDistribution();
        
        loadCountriesIntoRedis(response);
        loadVariablesIntoRedis(response);

        response.getDataSets().get(0).getSeries().forEach((k, v) -> {
            String[] ids = k.split(":");
            String countryId = ids[0];
            String varId = ids[1];
            System.out.println(getCountryName(countryId) + " - " + getVarName(varId));
            v.getObservations().forEach((x,y) -> y.forEach(a -> System.out.print(a + " ")));
            System.out.println("\n");
        });
    }

    private void loadCountriesIntoRedis(WealthResponse response) {
        List<IdNamePair> orderedCountries = response.getStructure().getDimensions().getSeriesList().get(0).getValues();
        for (int countryId = 0; countryId < orderedCountries.size(); countryId++) {
            redis.insert(CTRY + countryId, orderedCountries.get(countryId).getName());
        }
    }

    private void loadVariablesIntoRedis(WealthResponse response) {
        List<IdNamePair> variables = response.getStructure().getDimensions().getSeriesList().get(1).getValues();
        for (int varId = 0; varId < variables.size(); varId++) {
            redis.insert(VAR + varId, variables.get(varId).getName());
        }
    }

    private String getCountryName(String varId) {
        return redis.read(CTRY + varId);
    }

    private String getVarName(String varId) {
        return redis.read(VAR + varId);
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
