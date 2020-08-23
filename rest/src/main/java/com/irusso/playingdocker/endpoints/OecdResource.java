package com.irusso.playingdocker.endpoints;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.irusso.playingdocker.constants.DataType;
import com.irusso.playingdocker.model.GeneralList;
import com.irusso.playingdocker.model.display.CountryWealthDetails;
import com.irusso.playingdocker.redis.Redis;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
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
    @Path("/datasets")
    public List<CountryWealthDetails> getDatasets() {
        System.out.println("Returning datasets...");
        return getKeys().getContent().stream()
            .filter(k -> k.startsWith(DataType.DATASET))
            .map(x -> {
                try {
                    return objectMapper.readValue(redis.read(x), CountryWealthDetails.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    return null;
                }
            })
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
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
    @Path("/variableNames")
    public List<String> getVariableNames() {
        System.out.println("Returning variable names...");
        return getKeys().getContent().stream()
            .filter(k -> k.startsWith(DataType.VARIABLE_NAME))
            .map(redis::read)
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }
}
