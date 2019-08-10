package com.springdemo.sourcetest.service;

import com.springdemo.common.RedisSentinelUtil;
import com.springdemo.common.RedisUtil;
import org.redisson.Redisson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hexiaofei on 2019/5/31.
 */
@Service("studentServer")
public class StudentServerImpl implements StudentServer{

    public String saveName(String key,String value) {
        System.out.println("开始保存： "+key);
        String name = RedisSentinelUtil.getInstance().set(key,value);
        System.out.println("保存结果： "+name);
        return name;
    }

    public String getName(String key) {
        String name = RedisSentinelUtil.getInstance().get(key);
        return name;
    }

    public boolean modifyName(String name) {
        return false;
    }


}
