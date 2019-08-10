package com.springdemo.service;

import com.springdemo.common.RedisSentinelUtil;
import com.springdemo.sourcetest.service.StudentServer;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class TestJedisCluster extends TestBase {

    StudentServer studentServer;

    @Before
    public void initBean(){
        studentServer = (StudentServer)getBean("studentServer");

    }
    public TestJedisCluster(){
        super();
        int i = 20;
    }

    @Test
    public void testSave(){
        System.out.println(studentServer.saveName("name","zhangsan"));
    }

    @Test
    public void testGet(){
        System.out.println(studentServer.getName("name"));
    }

    @Test
    public void testSentinels(){
        for(int i = 0; i < 10000 ; i++){
            String key = "key:"+i;
            try {
                System.out.println(RedisSentinelUtil.getInstance().set(key,i+""));
                TimeUnit.MILLISECONDS.sleep(300);
                System.out.println(RedisSentinelUtil.getInstance().get(key));
            }catch (Exception e){
                System.out.println(e);
            }

        }
    }
}
