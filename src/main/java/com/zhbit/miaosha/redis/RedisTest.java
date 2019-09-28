package com.zhbit.miaosha.redis;

import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
public class RedisTest {

    public static  void main(String[] args){
        String host="192.168.25.128";
        int port = 6379;
        Jedis jedis = new Jedis(host,port);
        jedis.auth("123");

        System.out.println(jedis.ping());


    }

 }
