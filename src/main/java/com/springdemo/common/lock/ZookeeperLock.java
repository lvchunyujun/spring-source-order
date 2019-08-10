package com.springdemo.common.lock;

import java.util.concurrent.TimeUnit;

public class ZookeeperLock implements LockInterface {


    public boolean tryLock(String lockName) {
        return false;
    }

    public void releaseLock(String lockName) {

    }

    public boolean tryLock(long time, TimeUnit unit) throws Exception {
        return false;
    }
}
