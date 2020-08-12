package com.irusso.playingdocker;

import com.irusso.playingdocker.constants.RedisConstants;
import com.irusso.playingdocker.files.ResourceNotFoundException;
import com.irusso.playingdocker.files.ResourceReader;
import com.irusso.playingdocker.redis.Redis;
import com.irusso.playingdocker.runnables.OecdReader;

import java.util.Arrays;
import java.util.Properties;

public class Runner {

    private static final String CONFIG_PATH = "config.properties";

    private static final ResourceReader resourceReader = new ResourceReader();

    public static void main(String[] args) throws ResourceNotFoundException {
        System.out.println("Starting up...");
        Properties config = resourceReader.getProperties(CONFIG_PATH);

        Redis redis;
        String host = System.getenv(RedisConstants.HOST_ENV);
        int port = Integer.parseInt(System.getenv(RedisConstants.PORT_ENV));
        if (host == null || port == 0) {
            System.out.println("Connecting to redis with [host=" + RedisConstants.DEFAULT_HOST
                + "] and [port=" + RedisConstants.DEFAULT_PORT + "]");
            redis = new Redis(RedisConstants.DEFAULT_HOST, RedisConstants.DEFAULT_PORT);
        } else {
            System.out.println("Connecting to redis with [host=" + host + "] and [port=" + port + "]");
            redis = new Redis(host, port);
        }

        OecdReader oecdReader = new OecdReader(config, redis);
        oecdReader.read();

        System.out.println("Exiting Runner.java successfully...");
    }
}
