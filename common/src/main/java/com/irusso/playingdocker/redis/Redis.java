package com.irusso.playingdocker.redis;

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
