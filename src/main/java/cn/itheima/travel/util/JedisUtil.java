package cn.itheima.travel.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JedisUtil {
    private static JedisPool jedisPool;
    static{
        Properties pro = new Properties();
        InputStream is = JedisUtil.class.getClassLoader().getResourceAsStream("jedis.properties");
        try {
            pro.load(is);
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxIdle(Integer.parseInt(pro.getProperty("maxIdle")));
            config.setMaxTotal(Integer.parseInt(pro.getProperty("maxTotal")));
            jedisPool = new JedisPool(config,pro.getProperty("host"), Integer.parseInt(pro.getProperty("port")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Jedis getJedis(){
        return jedisPool.getResource();
    }

    public static void close(Jedis jedis){
        if(jedis!=null){
            jedis.close();
        }
    }
}
