package com.irusso.playingdocker.endpoints;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.irusso.playingdocker.constants.DataType;
import com.irusso.playingdocker.model.GeneralList;
import com.irusso.playingdocker.model.display.EconomicReport;
import com.irusso.playingdocker.redis.Redis;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Path("oecd")
@Produces(MediaType.APPLICATION_JSON)
public class OecdResource {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final Redis redis;

    public OecdResource() {
        this.redis = Redis.newInstance();
    }

    @GET
    public GeneralList<String> getKeys() {
        System.out.println("Returning keys...");
        return new GeneralList<>(new ArrayList<>(redis.keys()));
    }

    @GET
    @Path("/query/{key}")
    public String query(@PathParam("key") String key) {
        System.out.println("Returning data for key " + key + "...");
        return redis.read(key);
    }

    @GET
    @Path("/countryNames")
    public List<String> getCountryNames() {
        System.out.println("Returning country names...");
        return getKeys().getContent().stream()
            .filter(k -> k.startsWith(DataType.COUNTRY_NAME))
            .map(redis::read)
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    @GET
    @Path("/displayData/{countryName}")
    public EconomicReport getDisplayData(@PathParam("countryName") String countryName) {
        System.out.println("Returning display data for " + countryName);
        try {
            return objectMapper.readValue(redis.read(DataType.DISPLAY_DATA + countryName), EconomicReport.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new EconomicReport();
        }
    }
}
