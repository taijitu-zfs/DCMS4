package org.leetcode.dkm.ms4.juc;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal  VS  InheritableThreadLocal VS  TransmittableThreadLocal
 * 1. ThreadLocal：线程池中，无法获取到外部线程的数据
 * 2. InheritableThreadLocal：线程池中，可以获取到外部线程的数据，但主线程修改后，线程池中的数据不会跟着改变
 * 3. TransmittableThreadLocal：线程池中，可以获取到外部线程的数据，主线程修改后，线程池中的数据也会跟着改变
 */
public class ThreadLocalDemoV3 {
    public static void main(String[] args) {
//        m1();
//        m2();
//        m3();  //InheritableThreadLocal
//        m4();
        m5();   //TransmittableThreadLocal
    }

    private static void m5()
    {
        TransmittableThreadLocal<String> transmittableThreadLocal = new TransmittableThreadLocal<>();
        //为了看到效果，这里创建大小为1的线程池方便看到效果,池中只有1个线程才有效果，池中只有1个线程才有效果
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        //这里需要用 TtlExecutors.getTtlExecutorService 将原线程池包装下
        threadPool = TtlExecutors.getTtlExecutorService(threadPool);

        //这里是主线程，使用 transmittableThreadLocal.set 放入值：Java
        transmittableThreadLocal.set(Thread.currentThread().getName()+"-Java");
        System.out.println("major:{}"+transmittableThreadLocal.get());

        //在线程池中通过 transmittableThreadLocal 拿值，看看能否拿到 刚才放入的Java？
        threadPool.execute(() -> {
            System.out.println("threadPool第1次获取 major:{}"+transmittableThreadLocal.get());
        });

        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println();
        System.out.println();

        //这里又在主线程中放入了Vue
        transmittableThreadLocal.set(Thread.currentThread().getName()+"-Vue我已经修改了，O(∩_∩)O");
        System.out.println("major:{}"+transmittableThreadLocal.get());

        //这里又在线程池中通过 transmittableThreadLocal.get 方法拿值，看看能否拿到 刚才放入的Vue？
        threadPool.execute(() -> {
            //在线程池中通过 transmittableThreadLocal 拿值，看看能否拿到？
            System.out.println("threadPool第2次获取 major:{}"+transmittableThreadLocal.get());
        });
        System.out.println();

        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        threadPool.shutdown();

        /**
         * 解决线程池中线程因为复用而不能取得外部线程数据的问题
         */
    }
    private static void m4()
    {
        //InheritableThreadLocal：遇到线程池，会有问题

        InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal();
        //这里是主线程，使用 InheritableThreadLocal.set 放入值：Java
        inheritableThreadLocal.set(Thread.currentThread().getName()+"-Java");
        System.out.println("major:{}"+inheritableThreadLocal.get());

        //为了看到效果，这里创建大小为1的线程池方便看到效果,池中只有1个线程才有效果，池中只有1个线程才有效果
        ExecutorService threadPool = Executors.newFixedThreadPool(1);
        //在线程池中通过 InheritableThreadLocal 拿值，看看能否拿到 刚才放入的Java？
        threadPool.execute(() -> {
            System.out.println("threadPool第1次获取 major:{}"+inheritableThreadLocal.get());
        });

        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println();
        System.out.println();


        //这里又在主线程中放入了Vue
        inheritableThreadLocal.set(Thread.currentThread().getName()+"-Vue我已经修改了，O(∩_∩)O");
        System.out.println("major:{}"+inheritableThreadLocal.get());

        //这里又在线程池中通过 InheritableThreadLocal.get 方法拿值，看看能否拿到 刚才放入的Vue？
        threadPool.execute(() -> {
            //在线程池中通过 inheritableThreadLocal 拿值，看看能否拿到？
            System.out.println("threadPool第2次获取 major:{}"+inheritableThreadLocal.get());
        });

        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        threadPool.shutdown();

        /**
         * new新建可以
         * 复用不好使，没有new
         */
    }

    public static void m3(){
        InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

        //这里是主线程，使用InheritableThreadLocal.set()方法设置值
        inheritableThreadLocal.set(Thread.currentThread().getName() + "-Java");
        System.out.println("主线程" + inheritableThreadLocal.get());

        new Thread(() -> {
            //这里是子线程，使用InheritableThreadLocal.get()方法获取值
            System.out.println("子线程1" + inheritableThreadLocal.get());
        }, "thread1").start();

        new Thread(() -> {
            //这里是子线程，使用InheritableThreadLocal.get()方法获取值
            System.out.println("子线程2" + inheritableThreadLocal.get());
        }, "thread2").start();

        new Thread(() -> {
            //这里是子线程，使用InheritableThreadLocal.get()方法获取值
            System.out.println("子线程3" + inheritableThreadLocal.get());
        }, "thread3").start();

    }

    private static void m2()
    {
        ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> null);
        //这里是主线程，ThreadLocal中设置了值：Java
        threadLocal.set(Thread.currentThread().getName()+"-Java");
        System.out.println("major:{}"+threadLocal.get());

        //新建线程thread1，在子线程thread1中去ThreadLocal中拿main线程放入值，能否拿到？
        //自己set的才能自己get，别人的取不到,分灶吃饭，自取自划
        new Thread(() -> {
            System.out.println("major:{}"+threadLocal.get());
        }, "thread1").start();
    }

    /**
     * Thread里面有ThreadLocal里面有个ThreadLocalMap
     * <p>
     * ThreadLocalMap里面有set方法
     * private void set(ThreadLocal<?> key, Object value)
     */
    private static void m1() {
        //ThreadLocal可以在当前线程中共享数据，set/get需要在同一个线程中执行才行，别人的取不到
        ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> null);

        threadLocal.set(Thread.currentThread().getName() + "-Java");
        System.out.println("major:{}" + threadLocal.get());
        System.out.println();


        //新建线程thread1，设置Vue，然后取出学科名看看?
        new Thread(() -> {
            System.out.println("major:{}" + threadLocal.get());//thread1是否可以取得main线程上一步写入的值？
            threadLocal.set(Thread.currentThread().getName() + "-Vue");
            System.out.println("major:{}" + threadLocal.get());
        }, "thread1").start();
        System.out.println();
        //暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //新建线程thread2，设置Flink，然后取出学科名看看?
        new Thread(() -> {
            System.out.println("major:{}" + threadLocal.get());
            threadLocal.set(Thread.currentThread().getName() + "-Flink");
            System.out.println("major:{}" + threadLocal.get());
        }, "thread2").start();
        System.out.println();

        CompletableFuture.supplyAsync(() -> {
            System.out.println("major:{}" + threadLocal.get());
            threadLocal.set(Thread.currentThread().getName() + "-mysql");
            System.out.println("major:{}" + threadLocal.get());
            return null;
        });
        System.out.println();

        //暂停毫秒
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
