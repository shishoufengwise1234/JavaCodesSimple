package com.java_simple.codes.thread;

import com.java_simple.codes.PKt;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockMain {

    private static final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static final Lock readLock = readWriteLock.readLock();
    private static final Lock writeLock = readWriteLock.writeLock();

    public static void main(String[] args) {

        new Thread(ReadWriteLockMain::read,"read-1").start();
        new Thread(ReadWriteLockMain::read,"read-2").start();
        new Thread(ReadWriteLockMain::write,"write-1").start();
//        new Thread(ReadWriteLockMain::write,"write-2").start();
        new Thread(ReadWriteLockMain::read,"read-3").start();

    }

    public static void read(){
        readLock.lock();
        try {
            PKt.out(Thread.currentThread().getName() + " , 得到读锁 ");
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            PKt.out(Thread.currentThread().getName()+" 释放读锁");
            readLock.unlock();
        }
    }

    public static void write(){
        writeLock.lock();
        try {
            PKt.out(Thread.currentThread().getName()+" , 得到写锁..");
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            PKt.out(Thread.currentThread().getName()+" 释放写锁");
            writeLock.unlock();
        }
    }




}
