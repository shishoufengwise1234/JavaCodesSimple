package com.java_simple.codes.thread;

import com.java_simple.codes.PKt;

public class ThreadMain {

    public static void main(String[] args) {


        new Thread(new Runnable() {
            @Override
            public void run() {
                PKt.out("run----");
            }
        }).start();
    }


}
