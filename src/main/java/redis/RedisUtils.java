package redis;


import redis.clients.jedis.JedisPool;

import java.util.Map;

public class RedisUtils {

    public RedisUtils( ){

    }
    private static final RedisUtils redisInstance = new RedisUtils();
    public static RedisUtils getRedisInstance(){
        return redisInstance;
    }

    /**String(字符串)**/
    public String set(String key,String value){
        JedisPool jedisPool = RedisProxy.getCurrJedisPool();
        return jedisPool.getResource().set(key, value);

    }

    public String setex(String key,String value, int seconds){
        JedisPool jedisPool = RedisProxy.getCurrJedisPool();
        return jedisPool.getResource().setex(key, seconds, value);
    }

    public String get(String key){
        JedisPool jedisPool = RedisProxy.getCurrJedisPool();
        String value = jedisPool.getResource().get(key);
        return value;
    }

    public Long incr (String key) {
        JedisPool jedisPool = RedisProxy.getCurrJedisPool();
        return jedisPool.getResource().incr(key);
    }

    /**Key(键)**/
    public Long del(String key){
        JedisPool jedisPool = RedisProxy.getCurrJedisPool();
        return jedisPool.getResource().del(key);
    }

    public Boolean existKey (String key) {
        JedisPool jedisPool = RedisProxy.getCurrJedisPool();
        return jedisPool.getResource().exists(key);
    }

    /**Set(集合)**/
    public Long sadd (String key,String members) {
        JedisPool jedisPool = RedisProxy.getCurrJedisPool();
        return jedisPool.getResource().sadd(key, members);
    }

    /**Hash(哈希表)**/
    public Long hset (String key,String field,String value) {
        JedisPool jedisPool = RedisProxy.getCurrJedisPool();
        return jedisPool.getResource().hset(key, field, value);
    }

    public String hget(String key,String field){
        JedisPool jedisPool = RedisProxy.getCurrJedisPool();
        return jedisPool.getResource().hget(key, field);
    }

    public Long hdel(String key,String field){
        JedisPool jedisPool = RedisProxy.getCurrJedisPool();
        return jedisPool.getResource().hdel(key, field);
    }

    public Map<String,String> hgetAll(String key){
        JedisPool jedisPool = RedisProxy.getCurrJedisPool();
        return jedisPool.getResource().hgetAll(key);
    }

    public Boolean hexists(String key,String field){
        JedisPool jedisPool = RedisProxy.getCurrJedisPool();
        return jedisPool.getResource().hexists(key, field);
    }

    public String hmset(String key,Map<String ,String > hash){
        JedisPool jedisPool = RedisProxy.getCurrJedisPool();
        return jedisPool.getResource().hmset(key,hash);
    }
}
