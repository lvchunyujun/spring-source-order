import sun.reflect.Reflection;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.math.BigInteger;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class TestA {
    private static final int MAXIMUM_CAPACITY = 1 << 30;
    private int i = 100;
    public static void main(String[] args) {
        int a = 0;
        boolean isOdd = false;

        for(int i=1;i<=2;i++)
        {
            if (i % 2 == 1) isOdd = true;
            else isOdd = false;
            a += i * (isOdd ? 1 : -1);
        }
        System.out.println(a);
    }

    private static final int tableSizeFor(int c) {
        int n = c - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
 class A {
    static int MAXIMUM_CAPACITY = Integer.MAX_VALUE;
     static int roundUpToPowerOf2(int number) {
        // assert number >= 0 : "number must be non-negative";
        return number >= MAXIMUM_CAPACITY
                ? MAXIMUM_CAPACITY
                : (number > 1)
                ? Integer.highestOneBit((number - 1) << 1)
                : 1;
    }
}
