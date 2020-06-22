package com.java_simple.codes.thread;

import com.java_simple.codes.PKt;

public class ThreadInterruptMain implements Runnable{


    private int num = 0;
    @Override
    public void run() {
        while (true){
            if (Thread.currentThread().isInterrupted()){
                return;
            }
            PKt.out("num : "+num++);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    public static void main(String[] args) {

        ThreadInterruptMain threadInterruptMain = new ThreadInterruptMain();
        Thread thread = new Thread(threadInterruptMain);
        thread.start();

        try {
            Thread.sleep(1*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();

    }

}
