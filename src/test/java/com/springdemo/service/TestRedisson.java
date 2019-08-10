package com.springdemo.service;

import com.springdemo.common.RedisSentinelUtil;
import com.springdemo.common.lock.RedisLock;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class TestRedisson extends TestBase{


    @Test
    public void testLock() throws InterruptedException {

        final String lockName = "100";
        new Thread(new Runnable() {
            public void run() {
                for(int i = 0 ; i < 5 ; i++) {
                    System.out.println(Thread.currentThread() + "    wait lock!");

                    if (RedisSentinelUtil.getInstance().setNx("lock",lockName)==1) {
                        System.out.println(Thread.currentThread() + "    get lock!");
                        try {
                            TimeUnit.SECONDS.sleep(5);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        RedisSentinelUtil.getInstance().remove("lock");
                    }else{

                    }
                }

            }
        }).start();

        TimeUnit.SECONDS.sleep(2000);
    }

}
