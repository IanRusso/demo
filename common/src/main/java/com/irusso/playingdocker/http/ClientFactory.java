package com.irusso.playingdocker.http;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class ClientFactory {

  public static CloseableHttpClient getClient() {
    return HttpClients.custom().build();
  }
}
