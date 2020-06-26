package com.java_simple.codes.thread;

import com.java_simple.codes.PKt;

import java.util.concurrent.*;

//线程池拒绝策略测试demo
public class ThreadPoolRejectedMain {


    public static void main(String[] args) {

        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(10);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,10,
                5, TimeUnit.MINUTES,workQueue);

        //向线程池中提交 20个任务
        for (int i = 0; i < 20; i++) {
            executor.execute(new Task());
        }
        PKt.out("任务提交完毕....");

        //紧接着提交一个任务
//        executor.execute(new Task());
//        executor.shutdown();
        executor.shutdownNow();

        try {
            boolean isTermination = executor.awaitTermination(22*1000,TimeUnit.MILLISECONDS);
            PKt.out(isTermination);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static class Task implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(10*1000);
            } catch (InterruptedException e) {
//                e.printStackTrace();
                PKt.out("收到中断信号...");
                return;
            }
            PKt.out("thread name : "+Thread.currentThread().getName());
        }
    }

}
