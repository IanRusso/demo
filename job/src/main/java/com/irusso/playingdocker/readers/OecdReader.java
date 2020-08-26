package com.irusso.playingdocker.readers;

import com.irusso.playingdocker.constants.DataType;
import com.irusso.playingdocker.model.oecd.Data;
import com.irusso.playingdocker.model.oecd.IdNamePair;
import com.irusso.playingdocker.model.oecd.WealthResponse;
import com.irusso.playingdocker.redis.Redis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OecdReader {

    public static Map<Integer, Double> readVariableForCountry(WealthResponse wealthResponse,
                                                              String countryId, String variable, Redis redis) {

        Map<String, Data> series = wealthResponse.getDataSets().get(0).getSeries();


        List<Integer> years = wealthResponse.getStructure().getDimensions()
            .getObservations()
            .get(0).getValues()
            .stream()
            .map(IdNamePair::getId)
            .map(Integer::parseInt)
            .collect(Collectors.toList());

        List<String> keys = series.keySet()
            .stream()
            .filter(key -> key.startsWith(countryId))
            .collect(Collectors.toList());

        Map<Integer, Double> results = new HashMap<>();
        for (String key : keys) {
            String variableName = getVarName(key.split(":")[1], redis);
            if (variableName == null) {
                System.out.println("Variable name not found, skipping...");
                continue;
            }

            if (variableName.equals(variable)) {
                series.get(key).getObservations().forEach((k, v) -> {
                    int year = years.get(Integer.parseInt(k));
                    double percent = Double.parseDouble(v.get(0).toString());
                    results.put(year, percent);
                });
            }
        }
        return results;
    }

    private static String getVarName(String varId, Redis redis) {
        return redis.read(DataType.VARIABLE_NAME + varId);
    }
}
