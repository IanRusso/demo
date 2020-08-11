package com.irusso.playingdocker.service;

import com.irusso.playingdocker.model.GroundStationResponse;
import com.irusso.playingdocker.util.XmlMapper;
import com.irusso.playingdocker.http.HttpClient;
import com.irusso.playingdocker.model.GroundStation;
import com.irusso.playingdocker.model.Observatory;
import com.irusso.playingdocker.model.ObservatoryResponse;
import org.apache.http.HttpException;
import org.apache.http.client.methods.HttpGet;

import javax.xml.bind.JAXBException;
import java.util.List;
import java.util.Properties;

/**
 * Class to interact with the Satellite Situation Center REST API
 */
public class SscService {

    private static final String BASE_URL = "https://sscweb.gsfc.nasa.gov/WS/sscr/2";
    private static final String SPASE_OBSERVATORIES_ENDPOINT = "/spaseObservatories";
    private static final String OBSERVATORIES_ENDPOINT = "/observatories";
    private static final String GROUND_STATIONS_ENDPOINT = "/groundStations";
    private static final String NAME_SPACE = " xmlns=\"http://sscweb.gsfc.nasa.gov/schema\"";

    private static final String NONE = "";

    private final Properties config;
    private final HttpClient httpClient;

    public SscService(Properties config, HttpClient httpClient) {
        this.config = config;
        this.httpClient = httpClient;
    }

    public List<Observatory> getObservatories() throws HttpException {
        HttpGet request = new HttpGet(BASE_URL + OBSERVATORIES_ENDPOINT);
        try {
            String responseContent = httpClient.sendRequest(request).replace(NAME_SPACE, NONE);
            return XmlMapper.read(responseContent, ObservatoryResponse.class).getObservatories();
        } catch (HttpException | JAXBException e) {
            throw new HttpException("Failed to execute HTTP request", e);
        }
    }

    public List<Observatory> getSpaseObservatories() throws HttpException {
        HttpGet request = new HttpGet(BASE_URL + SPASE_OBSERVATORIES_ENDPOINT);
        try {
            String responseContent = httpClient.sendRequest(request).replace(NAME_SPACE, NONE);
            return XmlMapper.read(responseContent, ObservatoryResponse.class).getObservatories();
        } catch (HttpException | JAXBException e) {
            throw new HttpException("Failed to execute HTTP request", e);
        }
    }

    public List<GroundStation> getGroundStations() throws HttpException {
        HttpGet request = new HttpGet(BASE_URL + GROUND_STATIONS_ENDPOINT);
        try {
            String responseContent = httpClient.sendRequest(request).replace(NAME_SPACE, NONE);
            return XmlMapper.read(responseContent, GroundStationResponse.class).getGroundStations();
        } catch (HttpException | JAXBException e) {
            throw new HttpException("Failed to execute HTTP request", e);
        }
    }

    private String getApiKey() {
        return this.config.getProperty("NASA_API_KEY");
    }
}
