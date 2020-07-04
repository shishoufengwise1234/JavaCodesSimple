package com.java_simple.codes.thread;

import com.java_simple.codes.PKt;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Condition 使用示例demo
public class ConditionTestMain {

    private Lock lock = new ReentrantLock(false);
    private Condition condition = lock.newCondition();

    private void method1(){
        lock.lock();
        try {
            PKt.out(Thread.currentThread().getName()+" 条件不满足，进行await()");
            condition.await();

            PKt.out(Thread.currentThread().getName()+" 条件满足开始进行...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void method2(){
        lock.lock();
        try {
            PKt.out(Thread.currentThread().getName()+" 需要5秒钟的准备时间、请稍后....");
            Thread.sleep(5000);
            PKt.out(Thread.currentThread().getName() + " 准备工作完成、开始工作 start ");
            condition.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionTestMain conditionTestMain = new ConditionTestMain();

        new Thread(conditionTestMain::method2).start();

        conditionTestMain.method1();
    }


}
