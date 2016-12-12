package redis;

public class RedisClient {

    public static RedisUtils getClient(){
        RedisProxy redisProxy = new RedisProxy();
        RedisUtils proxy = (RedisUtils)redisProxy.getProxy(RedisUtils.getRedisInstance());
        return proxy;
    }
}
