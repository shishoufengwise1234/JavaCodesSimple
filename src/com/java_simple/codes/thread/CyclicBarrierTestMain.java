package com.java_simple.codes.thread;

import com.java_simple.codes.PKt;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//CyclicBarrier 测试示例
public class CyclicBarrierTestMain {


    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

        ExecutorService executorService = Executors.newFixedThreadPool(6);
        for (int i = 1; i <= 6; i++) {
           executorService.submit(new TaskBarrier(i,cyclicBarrier));
        }

        executorService.shutdown();

    }


    private static class TaskBarrier implements Runnable {

        private int id;
        private CyclicBarrier cyclicBarrier;

        public TaskBarrier(int i, CyclicBarrier barrier) {
            id = i;
            cyclicBarrier = barrier;
        }

        @Override
        public void run() {
            PKt.out("同学" + id + "现在从大门出发，前往自行车驿站");
            try {
                Thread.sleep((long) (Math.random() * 10000));
                PKt.out("同学" + id + "到了自行车驿站，开始等待其他人到达");
                cyclicBarrier.await();

                PKt.out("同学" + id + "开始骑车");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }


}
