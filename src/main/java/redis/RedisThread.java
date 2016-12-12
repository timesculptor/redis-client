package redis;

public class RedisThread extends Thread {

    private String key;
    public RedisThread(String key){
        this.key = key;
    }

    @Override
    public void run() {
        RedisClient.getClient().set(key,"aa");
    }
}
