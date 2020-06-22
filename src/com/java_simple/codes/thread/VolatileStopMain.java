package com.java_simple.codes.thread;

import com.java_simple.codes.PKt;

import java.time.chrono.IsoChronology;

public class VolatileStopMain implements Runnable{

    private volatile boolean isCanceled = false;

    @Override
    public void run() {
        int num = 0;
        while (!isCanceled && num <= 1000000){
            if (num % 10 == 0) {
                PKt.out("10的倍数 num : " + num);
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            num++;
        }
    }

    public static void main(String[] args) {

        VolatileStopMain stopMain = new VolatileStopMain();
        Thread thread = new Thread(stopMain);
        thread.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stopMain.isCanceled = true;


    }
}
