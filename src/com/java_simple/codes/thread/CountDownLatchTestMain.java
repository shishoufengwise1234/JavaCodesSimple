package com.java_simple.codes.thread;

import com.java_simple.codes.PKt;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RunnableFuture;

//CountDownLatch 倒数测试demo
public class CountDownLatchTestMain {


    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(5);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 1; i <= 5; i++) {
            int number = i;
            Runnable runnable = () -> {
                try {
                    Thread.sleep((long) (Math.random() * 10000));
                    PKt.out(number+" 号同学完成运行...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //倒数处理
                    countDownLatch.countDown();
                }
            };
            executorService.submit(runnable);
        }
        PKt.out("等待全部完成...");
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        PKt.out("所有人都完成运行...");

        executorService.shutdown();
    }

}
