package org.leetcode.dkm.othertest;

import org.leetcode.dkm.ms4.TestDemo;
import org.leetcode.dkm.utils.MyyTest;

import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

public class AutoTestClient {
    public static void main(String[] args) {
        TestDemo testDemo = new TestDemo();
        int param1 = 10;
        int param2 = 0;

        Method[] methods = testDemo.getClass().getMethods();
        AtomicInteger bugCount = new AtomicInteger();

        for (int i = 0; i <methods.length ; i++) {
            if (methods[i].isAnnotationPresent(MyyTest.class)) {
                try {
                    System.out.println("测试方法：" + methods[i].getName());
                    methods[i].invoke(testDemo, param1, param2);
                } catch (Exception e) {
                    bugCount.incrementAndGet();
                    System.out.println("测试方法：" + methods[i].getName() + "失败");
                    System.out.println("异常名称:" + e.getClass().getName());
                    System.out.println("异常原因:" + e.getMessage());
                    e.printStackTrace();


                }
            }
        }
    }
}
