package com.irusso.playingdocker.endpoints;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.irusso.playingdocker.constants.DataType;
import com.irusso.playingdocker.constants.RedisConstants;
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
        //this is giving back 172.29.186.113 when the REDIS_SERVICE_HOST var on the server is 10.111.187.84
        String host = System.getenv(RedisConstants.HOST_ENV);
        int port = Integer.parseInt(System.getenv(RedisConstants.PORT_ENV));
        if (host == null || port == 0) {
            System.out.println("Connecting to redis with [host=" + RedisConstants.DEFAULT_HOST
                + "] and [port=" + RedisConstants.DEFAULT_PORT + "]");
            this.redis = new Redis(RedisConstants.DEFAULT_HOST, RedisConstants.DEFAULT_PORT);
        } else {
            System.out.println("Connecting to redis with [host=" + host + "] and [port=" + port + "]");
            this.redis = new Redis(host, port);
        }
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
