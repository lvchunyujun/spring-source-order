package com.springdemo.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GenUtil
{

    /**
     * 根据传入位数生成固定长度UUID值
     * @param l
     * @return
     */
    public static String generate(int l)
    {
        String prefix = "";

        StringBuilder sb = new StringBuilder(l);

        sb.append(generateNumNotZeroStr(1));
        sb.append(generateNumStr(l - 1));
        prefix = sb.toString();
        return prefix;
    }


    public static String generateNumNotZeroStr(int length)
    {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append("123456789abcdefghijklmnopqrstuvwxyz!@#$%^&*".charAt(random.nextInt("123456789abcdefghijklmnopqrstuvwxyz!@#$%^&*".length())));
        }
        return sb.toString();
    }

    public static String generateNumStr(int length)
    {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append("123456789abcdefghijklmnopqrstuvwxyz!@#$%^&*".charAt(random.nextInt("123456789abcdefghijklmnopqrstuvwxyz!@#$%^&*".length())));
        }
        return sb.toString();
    }


    public static void main(String[] args)
    {
       Map map=new HashMap();


    }


    public static String generateNoNotZeroStr(int length)
    {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append("123456789".charAt(random.nextInt("123456789".length())));
        }
        return sb.toString();
    }








}
