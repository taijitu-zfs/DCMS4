package org.leetcode.dkm.ms4.juc;

import lombok.Getter;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

class SU7{
    //本门店总体销售总额
    @Getter
    private int saleTotal;
    public synchronized void saletotal() {
        saleTotal++;
    }

    ThreadLocal<Integer> salePersonal = ThreadLocal.withInitial(() -> 0);
    public void saletotal2() {
        salePersonal.set(salePersonal.get() + 1);
    }
}

public class ThreadLocalDemoV1 {
    public static void main(String[] args) throws InterruptedException {
        //线程  操作  资源类
        SU7 su7 = new SU7();
        CountDownLatch countDownLatch = new CountDownLatch(3);

        for (int i = 1; i <= 3 ; i++) {
            new Thread(() -> {
                try{
                    for (int j = 1; j <= new Random().nextInt(3) + 1; j++) {
                        su7.saletotal();//本门店销售总额
                        su7.saletotal2();//各个销售独立线程的销售额
                    }
                    System.out.println(Thread.currentThread().getName() + "\t" + " 号销售卖出：" + su7.salePersonal.get() + "件");
                }finally {
                    countDownLatch.countDown();
                }
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t" + "销售总额：" + su7.getSaleTotal());

    }
}
