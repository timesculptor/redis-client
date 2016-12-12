package redis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;

public class RedisClientPool {

    private static List<JedisPool> jedisPoolList;

    private static RedisClientPool instancePool = new RedisClientPool();
    private RedisClientPool(){
        List<JedisPool> jedisPool = new ArrayList<JedisPool>();
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(RedisConstant.maxIdle);
        config.setTestOnBorrow(RedisConstant.testOnBorrow);
        String[] ip = RedisConstant.redisHosts.split(";");
        //String[] ip = SystemInfo.redisMap.get("redisHost").split(";");
        for (int i = 0; i < ip.length; i++) {
            String[] ipinfo = ip[i].split(":");
            if (ipinfo.length == 2) {
                JedisPool pool = new JedisPool(config,ipinfo[0],Integer.valueOf(ipinfo[1]));
                jedisPool.add(pool);
            }
        }
        this.jedisPoolList = jedisPool;
    }

    public List<JedisPool> getJedisPoolList() {
        return jedisPoolList;
    }

    public static RedisClientPool getInstance() {
        return instancePool;
    }
}
