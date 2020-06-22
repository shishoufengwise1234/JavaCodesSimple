package com.java_simple.codes.thread;

import com.java_simple.codes.PKt;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 * 死锁的例子
 */
public class MyDeadLock {

    private Object obj1 = new Object();
    private Object obj2 = new Object();

    public void thread1() throws InterruptedException{
        synchronized (obj1){
            Thread.sleep(500);

            synchronized (obj2){
                PKt.out("thread1() : 同时获取到两把锁...");
            }
        }
    }
    public void thread2() throws InterruptedException{
        synchronized (obj2){
            Thread.sleep(500);

            synchronized (obj1){
                PKt.out("thread2() : 同时获取到两把锁...");
            }
        }
    }

    public static void main(String[] args) {
       MyDeadLock deadLock = new MyDeadLock();
       new Thread(new Runnable() {
           @Override
           public void run() {
               try {
                   deadLock.thread1();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       }).start();

       new Thread(new Runnable() {
           @Override
           public void run() {
               try {
                   deadLock.thread2();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       }).start();

    }
}
