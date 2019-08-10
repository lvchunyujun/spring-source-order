package com.springdemo.sourcetest.service;

import org.springframework.stereotype.Service;

@Service
public class StudentServerMock implements StudentServer {
    public String saveName(String key, String value) {

        System.out.println("服务降级调用！！！！");

        return "mock";
    }

    public String getName(String key) {
        return null;
    }

    public boolean modifyName(String name) {
        return false;
    }
}
