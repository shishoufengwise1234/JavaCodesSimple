package com.java_simple.codes.thread;

import com.java_simple.codes.PKt;
import sun.jvm.hotspot.utilities.MessageQueue;

public class MyBlockingQueueMain {

    public static void main(String[] args) {
        MyBlockingQueue<Integer> queue = new MyBlockingQueue<>(10);

        Producter producter = new Producter(queue);
        Consumer consumer = new Consumer(queue);

        Thread producterThread = new Thread(producter);
        Thread consumerThread = new Thread(consumer);

        producterThread.start();
        consumerThread.start();
    }

    public static class Producter implements Runnable{

        private MyBlockingQueue<Integer> mQueue;
        public Producter(MyBlockingQueue<Integer> queue){
            mQueue = queue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    mQueue.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class Consumer implements Runnable{
        private MyBlockingQueue<Integer> mQueue;
        public Consumer(MyBlockingQueue<Integer> queue){
            mQueue = queue;
        }
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    int re = mQueue.take();
                    PKt.out("re = "+re);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
