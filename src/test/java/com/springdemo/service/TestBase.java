package com.springdemo.service;

import com.springdemo.sourcetest.service.StudentServer;
import org.junit.Before;
import org.junit.runners.JUnit4;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


/**
 * Created by hexiaofei on 2019/5/31.
 */

public class TestBase  {

    static String[] path = {"classpath:config.xml"};
    ApplicationContext applicationContext;

    @Before
    public void init(){
         applicationContext = new FileSystemXmlApplicationContext(path);
         applicationContext.getBean(StudentServer.class);
    }

    public Object getBean(String service){
        return applicationContext.getBean(service);
    }

}
