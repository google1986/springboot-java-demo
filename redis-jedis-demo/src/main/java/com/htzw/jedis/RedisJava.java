package com.htzw.jedis;

import redis.clients.jedis.Jedis;

/**
 * 测试RedisPool
 *
 * @author gu.lian.jun
 * @version 1.0
 * @date 2020/8/25 9:18
 */
public class RedisJava {

    public static void main(String[] args) {
//        RedisPool.getJedis().set("name", "张三");
//        System.out.println(RedisPool.getJedis().get("name"));

        RedisPool.getJedis().set("name","lisi");
        System.out.println(RedisPool.getJedis().get("name"));
    }

}
