package org.leetcode.dkm.ms4.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class MyData{
    static ThreadLocal<Integer> threadLocalField = ThreadLocal.withInitial(() -> 0);
    public void add() {
        threadLocalField.set(threadLocalField.get() + 1);
    }
}

public class ThreadLocalDemoV2 {
    public static void main(String[] args) {
        MyData myData = new MyData();
        //模拟一个银行有3个办理业务受理窗口
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        try{
            for (int i = 1; i <= 10; i++) {
                int finalI = i;
                threadPool.submit(() -> {
                    try{
                        Integer beforeInt =  myData.threadLocalField.get();
                        myData.add();
                        Integer afterInt = myData.threadLocalField.get();
                        System.out.println(Thread.currentThread().getName() + "\t" + "工作窗口，受理第：" + finalI + "个顾客业务。" +
                                "窗口办理业务前： " + beforeInt + ", 窗口办理业务后: " + afterInt);
                    }finally {
                        MyData.threadLocalField.remove();
                    }
                });
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally{
            threadPool.shutdown();
        }

    }
}
