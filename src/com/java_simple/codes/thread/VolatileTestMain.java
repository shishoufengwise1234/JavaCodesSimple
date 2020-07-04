package com.java_simple.codes.thread;

import com.java_simple.codes.PKt;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileTestMain implements Runnable{
    volatile int count = 0;
    AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            count++;

            atomicInteger.incrementAndGet();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileTestMain runnable = new VolatileTestMain();
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        PKt.out("运行结束 ...");
        PKt.out("count = "+runnable.count);
        PKt.out("atomicInteger = "+runnable.atomicInteger.get());
    }
}
