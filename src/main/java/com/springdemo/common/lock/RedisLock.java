package com.springdemo.common.lock;

import com.springdemo.common.RedisSentinelUtil;
import com.springdemo.common.SpringContextUtil;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

public class RedisLock {

    private static String KEY = "LOCK";

    public static RedisLock redisLock = new RedisLock();

    private RedisLock() {
    }

    public static RedisLock getInstance(){
        return redisLock;
    }

    public void releaseLock(String lockName) {
        RedisSentinelUtil.getInstance().remove(KEY);
    }

    public boolean tryLock(long time, TimeUnit unit) throws Exception {
        return false;
    }


    public boolean tryLock(String lockName) {
        long reuslt = RedisSentinelUtil.getInstance().setNx(KEY,lockName);
        return reuslt==1;
    }


}
