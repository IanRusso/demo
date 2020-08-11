package com.irusso.playingdocker.endpoints;

import com.irusso.playingdocker.redis.Redis;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collections;

@Path("oecd")
@Produces(MediaType.APPLICATION_JSON)
public class OecdResource {

    private final Redis redis;

    public OecdResource(Redis redis) {
        this.redis = redis;
    }

    @GET
    public GeneralList<String> getKeys() {
        return new GeneralList<>(Collections.singletonList("hello!"));
        //return new GeneralList<>(new ArrayList<>(redis.keys()));
    }
}
