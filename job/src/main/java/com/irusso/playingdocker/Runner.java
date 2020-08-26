package com.irusso.playingdocker;

import com.irusso.playingdocker.files.ResourceNotFoundException;
import com.irusso.playingdocker.files.ResourceReader;
import com.irusso.playingdocker.redis.Redis;
import com.irusso.playingdocker.loaders.OecdLoader;

import java.util.Properties;

public class Runner {

    private static final String CONFIG_PATH = "config.properties";

    private static final ResourceReader resourceReader = new ResourceReader();

    /**
     * TODO: maybe I could go through the data and determine what counrties are most likely to experience certain things
     * e.g.
     *  - recession
     *  - revolution
     *  - coup
     *  - external conflict
     * @param args
     * @throws ResourceNotFoundException
     */
    public static void main(String[] args) throws ResourceNotFoundException {
        System.out.println("Starting up...");
        Properties config = resourceReader.getProperties(CONFIG_PATH);

        OecdLoader oecdReader = new OecdLoader(config, Redis.newInstance());
        oecdReader.updateRedis();

        System.out.println("Exiting Runner.java successfully...");
    }
}
