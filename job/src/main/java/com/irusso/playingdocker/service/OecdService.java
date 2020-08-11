package com.irusso.playingdocker.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.irusso.playingdocker.http.HttpClient;
import com.irusso.playingdocker.model.oecd.WealthResponse;
import org.apache.http.HttpException;
import org.apache.http.client.methods.HttpGet;

import java.util.Properties;

public class OecdService {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final String BASE_URL = "http://stats.oecd.org/SDMX-JSON/data";
    private static final String WEALTH_DISTRIBUTION_ENDPOINT = "/WEALTH";

    private final Properties config;
    private final HttpClient httpClient;

    public OecdService(Properties config, HttpClient httpClient) {
        this.config = config;
        this.httpClient = httpClient;
    }

    public WealthResponse getWealthDistribution() {
        HttpGet request = new HttpGet(BASE_URL + WEALTH_DISTRIBUTION_ENDPOINT);
        try {
            String responseContent = httpClient.sendRequest(request);
            return objectMapper.readValue(responseContent, WealthResponse.class);
        } catch (HttpException | JsonProcessingException e) {
            e.printStackTrace();
            return new WealthResponse();
        }
    }
}
