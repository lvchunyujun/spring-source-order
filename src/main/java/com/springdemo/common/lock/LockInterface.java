package com.springdemo.common.lock;

import java.util.concurrent.TimeUnit;

public interface LockInterface {

    boolean tryLock(String lockName);

    void releaseLock(String lockName);

    boolean tryLock(long time, TimeUnit unit) throws Exception;
}
