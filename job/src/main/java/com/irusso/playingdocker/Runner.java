package com.irusso.playingdocker;

import com.irusso.playingdocker.files.ResourceNotFoundException;
import com.irusso.playingdocker.files.ResourceReader;
import com.irusso.playingdocker.redis.Redis;
import com.irusso.playingdocker.runnables.OecdReader;

import java.util.Properties;

public class Runner {

    private static final String CONFIG_PATH = "config.properties";

    private static final ResourceReader resourceReader = new ResourceReader();

    public static void main(String[] args) throws ResourceNotFoundException {
        System.out.println("Starting up...");
        Properties config = resourceReader.getProperties(CONFIG_PATH);

        String host;
        int port;
        if (args.length > 1) {
            host = args[0];
            port = Integer.parseInt(args[1]);
        } else {
            host = "localhost";
            port = 6379;
        }
        Redis redis = new Redis(host, port);

        OecdReader oecdReader = new OecdReader(config, redis);
        oecdReader.read();

        System.out.println("Successfully completed run");
    }
}
