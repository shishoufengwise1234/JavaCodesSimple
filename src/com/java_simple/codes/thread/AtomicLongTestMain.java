package com.java_simple.codes.thread;

import com.java_simple.codes.PKt;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class AtomicLongTestMain {


    public static void main(String[] args) {
//        AtomicLong atomicLong = new AtomicLong(0);
        LongAdder longAdder = new LongAdder();

        ExecutorService executorService = Executors.newFixedThreadPool(16);
        for (int i = 0; i < 100; i++) {
//            executorService.submit(new Task(atomicLong));
            executorService.submit(new LongAdderTask(longAdder));
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();

//        PKt.out(atomicLong.get());
        PKt.out(longAdder.longValue());
    }

    private static class Task implements Runnable{

        private AtomicLong atomic;
        public Task(AtomicLong atomicLong){
            atomic = atomicLong;
        }
        @Override
        public void run() {
            //自增 且获取数据
            atomic.incrementAndGet();
        }
    }

    private static class LongAdderTask implements Runnable{

        private LongAdder longAdder;
        public LongAdderTask(LongAdder adder){
            longAdder = adder;
        }

        @Override
        public void run() {
            //自增
            longAdder.increment();
        }
    }
}
