package redis;

import org.junit.Test;

public class RedisTest {

    @Test
    public void redisTest(){
        RedisClient.getClient().setex("myTestKey", "testValue", 60 * 60);
        String value = RedisClient.getClient().get("myTestKey");
        System.out.println(value);
    }
}
