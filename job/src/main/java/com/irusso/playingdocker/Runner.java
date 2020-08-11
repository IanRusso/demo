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

        OecdReader oecdReader = new OecdReader(config, new Redis());

        oecdReader.read();

        Redis redis = new Redis();

        System.out.println("Successfully completed run");
    }
}
