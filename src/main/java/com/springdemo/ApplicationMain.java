package com.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class ApplicationMain {

    private final static String[] PATHS = {"config.xml"};

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext classPathXmlApplicationContext
                = new ClassPathXmlApplicationContext(PATHS);
        System.out.println("started！");
        System.out.println("按任意键退出……");
        System.in.read(); // 按任意键退出
    }
}
