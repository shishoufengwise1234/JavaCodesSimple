package com.java_simple.codes.thread;

import com.java_simple.codes.PKt;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{

    private BlockingQueue<Integer> mQueue;
    public volatile boolean isCanceled = false;

    public Producer(BlockingQueue<Integer> blockingQueue){
        mQueue = blockingQueue;

        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        int num = 0;
        try {
            while (!isCanceled && num <= 1000000) {
                if (num % 30 == 0) {
                    mQueue.add(num);
                    PKt.out("已添加至阻塞队列中 "+num);
                }
                num++;
            }
        }finally {
            PKt.out("生产者结束运行 ...end");
        }
    }
}
