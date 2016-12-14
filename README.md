# redis-client
#### 全写随机读
代码中4台redis server，写操作4台顺序写，随机从一台server读，无主从模式。
#### 适用场景
适用于server较少、读多写少的场景
#### 用法
将RedisConstant类中的redisHosts替换即可
