package redis;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import redis.clients.jedis.JedisPool;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class RedisProxy implements MethodInterceptor {


    private static final ThreadLocal<JedisPool> currJedisPool = new ThreadLocal<JedisPool>();
    private static final Map<String, OP> methodNameMap = new HashMap<String, OP>();
    private RedisUtils target;

    static {
        methodNameMap.put("set", OP.WRITE);
        methodNameMap.put("setex", OP.WRITE);
        methodNameMap.put("get", OP.READ);
        methodNameMap.put("del", OP.WRITE);
        methodNameMap.put("existKey", OP.READ);
        methodNameMap.put("sadd", OP.WRITE);
        methodNameMap.put("incr", OP.WRITE);
        methodNameMap.put("hset", OP.WRITE);
        methodNameMap.put("hget", OP.READ);
        methodNameMap.put("hdel", OP.WRITE);
        methodNameMap.put("hgetAll", OP.READ);
        methodNameMap.put("hexists", OP.READ);
        methodNameMap.put("hmset", OP.WRITE);
    }

    public static JedisPool getCurrJedisPool(){
        return currJedisPool.get();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        OP op = null;
        op = (OP) methodNameMap.get(methodProxy.getSignature().getName());
        if (OP.READ == op){
            //读  随机
            int randomIndex = (int)(Math.random()*RedisClientPool.getInstance().getJedisPoolList().size());
            JedisPool randomJedisPool = RedisClientPool.getInstance().getJedisPoolList().get(randomIndex);
            currJedisPool.set(randomJedisPool);
            Object result = method.invoke(target, objects);
            return result;
        }else {
            //写  顺序
            for(JedisPool jedisPool :RedisClientPool.getInstance().getJedisPoolList()){
                currJedisPool.set(jedisPool);
                Object result = method.invoke(target, objects);
            }
        }
        return null;
    }

    public  Object getProxy(Object target) {
        this.target = (RedisUtils) target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    public enum OP {
        READ,
        WRITE
    }
}
