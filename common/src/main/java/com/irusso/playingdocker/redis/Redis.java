package com.irusso.playingdocker.redis;

import com.irusso.playingdocker.constants.RedisConstants;
import redis.clients.jedis.Jedis;

import java.util.Set;

import static redis.clients.jedis.Protocol.Keyword.PONG;

public class Redis {

    private final Jedis jedis;

    public Redis(String host, int port) {
        this.jedis = new Jedis(host, port);
        String response = jedis.ping();
        if (response.equals(PONG.name())) {
            System.out.println("Successfully connected to Redis");
        } else {
            System.out.println("Unknown response from Redis ping - " + response);
        }
    }

    public static Redis newInstance() {
        String host = System.getenv(RedisConstants.HOST_ENV);
        String portStr = System.getenv(RedisConstants.PORT_ENV);

        if (host == null) {
            host = RedisConstants.DEFAULT_HOST;
            System.out.println("Using default host - " + host);
        } else {
            System.out.println("Using environment host - " + host);
        }

        int port;
        if (portStr == null) {
            port = RedisConstants.DEFAULT_PORT;
            System.out.println("Using default port - " + port);
        } else {
            System.out.println("Using environment port - " + portStr);
            port = Integer.parseInt(portStr);
        }
        return new Redis(host, port);
    }

    public void insert(String k, String v) {
        jedis.set(k, v);
    }

    public String read(String k) {
        return jedis.get(k);
    }

    public Set<String> keys() {
        return jedis.keys("*");
    }
}
