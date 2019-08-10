package com.springdemo.sourcetest.service;

/**
 * Created by hexiaofei on 2019/5/31.
 */
public interface StudentServer {

    String saveName(String key,String value);

    String getName(String key);

    boolean modifyName(String name);
}
