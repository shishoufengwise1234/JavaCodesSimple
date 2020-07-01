package com.java_simple.codes.thread;

import com.java_simple.codes.PKt;
import com.sun.corba.se.impl.oa.poa.AOMEntry;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceMain {

    private AtomicReference<Thread> atomicReference = new AtomicReference<>();
    private int count = 0;

    public void lock(){
        Thread t = Thread.currentThread();
        if (t == atomicReference.get()){
            ++count;
            return;
        }
        while (!atomicReference.compareAndSet(null,t)){
            PKt.out("自旋了...");
        }
    }

    public void unLock(){
        Thread t = Thread.currentThread();
        if (t == atomicReference.get()){
            if (count > 0){
                count--;
            }else{
                atomicReference.set(null);
            }
        }
    }

    public static void main(String[] args) {
        AtomicReferenceMain atomicReferenceMain = new AtomicReferenceMain();

        Runnable runnable = () -> {
            PKt.out("开始尝试获取自旋锁..");
            atomicReferenceMain.lock();
            try {
                PKt.out(Thread.currentThread().getName()+",获取到了自旋锁...");
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                PKt.out("释放自旋锁..");
                atomicReferenceMain.unLock();
            }
        };
        Thread thread1 = new Thread(runnable,"Thread-1");
        Thread thread2 = new Thread(runnable,"Thread-2");

        thread1.start();
        thread2.start();

    }
}
