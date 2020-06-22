package com.java_simple.codes.thread;

import com.java_simple.codes.PKt;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Consumer {

    BlockingQueue<Integer> mQueue;

    public Consumer(BlockingQueue<Integer> queue) {
        this.mQueue = queue;
    }

    public boolean needMoreNums() {
        if (Math.random() > 0.97) {
            return false;
        }
        return true;
    }


    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(8);

        Producer producer = new Producer(blockingQueue);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Consumer consumer = new Consumer(blockingQueue);
        while (consumer.needMoreNums()){
            try {
                PKt.out(consumer.mQueue.take() + " 被消费了...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        PKt.out("消费者不需要数据了。。。");

        producer.isCanceled = true;
        PKt.out(producer.isCanceled);

    }
}
