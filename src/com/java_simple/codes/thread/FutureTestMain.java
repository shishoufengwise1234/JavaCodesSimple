package com.java_simple.codes.thread;

import com.java_simple.codes.PKt;

import java.util.Random;
import java.util.concurrent.*;

public class FutureTestMain {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<Integer> future = executorService.submit(new CallAbleTask());
//        if (future.isDone()){

        executorService.shutdown();
            try {
                PKt.out(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
//        }
    }

    private static class CallAbleTask implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
            Thread.sleep(500);

            return new Random().nextInt(500);
        }
    }
}
