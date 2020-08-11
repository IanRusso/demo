package com.irusso.playingdocker.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.irusso.playingdocker.http.ClientFactory;
import com.irusso.playingdocker.model.Quote;
import org.apache.http.HttpException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Properties;

public class FinnhubService {

  private static final String FINNHUB_TOKEN_HEADER = "X-Finnhub-Token";
  private static final String FINNHUB_BASE_URL = "https://finnhub.io/api/v1";
  private static final String FINNHUB_QUOTE_SYMBOL = "/quote?symbol=";

  //utils
  private static final ObjectMapper objectMapper = new ObjectMapper();

  private final Properties config;

  public FinnhubService(Properties config) {
    this.config = config;
  }

  public Quote getQuote(String ticker) throws HttpException {
    try (CloseableHttpClient httpClient = ClientFactory.getClient()) {
      String apiKey = getApiKey();
      HttpGet request = new HttpGet(FINNHUB_BASE_URL + FINNHUB_QUOTE_SYMBOL + ticker);
      request.setHeader(FINNHUB_TOKEN_HEADER, apiKey);
      try (CloseableHttpResponse response = httpClient.execute(request)) {
        String responseData = EntityUtils.toString(response.getEntity());
        return objectMapper.readValue(responseData, Quote.class);
      }
    } catch (IOException e) {
      throw new HttpException("Failed to execute HTTP request", e);
    }
  }

  private String getApiKey() {
    return config.getProperty("FINN_API_KEY");
  }
}
