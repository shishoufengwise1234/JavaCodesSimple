package com.java_simple.codes.thread;

import com.java_simple.codes.PKt;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskTestMain {

    public static void main(String[] args) {

        FutureTask<Integer> futureTask = new FutureTask<>(new SumTask());
        Thread thread = new Thread(futureTask);
        thread.start();

        int re = 0;
        try {
            re = futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        PKt.out(re);


    }

    private static class SumTask implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
            PKt.out("执行任务...");
            int sum = 0;
            for (int i = 0; i < 1000; i++) {
                sum= sum+i;
            }
            Thread.sleep(400);

            return sum;
        }
    }
}
