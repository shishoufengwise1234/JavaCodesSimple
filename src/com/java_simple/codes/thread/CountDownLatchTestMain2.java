package com.java_simple.codes.thread;

import com.java_simple.codes.PKt;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTestMain2 {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 1; i <= 5 ; i++) {
            int number = i;
            executorService.submit(() -> {
                PKt.out(number+" 号运动员已准备好");
                try {
                    countDownLatch.await();
                    PKt.out(number+" 号运动员开始运行...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        PKt.out("准备开始...");
        countDownLatch.countDown();

        executorService.shutdown();

    }
}
