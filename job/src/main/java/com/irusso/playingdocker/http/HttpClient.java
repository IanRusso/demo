package com.irusso.playingdocker.http;

import org.apache.http.HttpException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClient {

    public String sendRequest(HttpUriRequest request) throws HttpException {
        try (CloseableHttpClient httpClient = ClientFactory.getClient()) {
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                return EntityUtils.toString(response.getEntity());
            }
        } catch (IOException e) {
            throw new HttpException("Failed to execute HTTP request", e);
        }
    }
}
