package com.java_simple.codes.thread;

import com.java_simple.codes.PKt;
import org.omg.CORBA.OBJ_ADAPTER;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

//死锁例子
public class DeadLockTest1 {

    private static Object obj1 = new Object();
    private static Object obj2 = new Object();

    public static void main(String[] args) {

        Thread thread1 = new Thread(DeadLockTest1::method1);
        Thread thread2 = new Thread(DeadLockTest1::method2);

        thread1.start();
        thread2.start();

        //利用 ThreadMXBean 分析死锁程序
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] deadLockThreads = threadMXBean.findDeadlockedThreads();
        ThreadInfo[] threadInfos = threadMXBean.getThreadInfo(deadLockThreads);
        if (threadInfos != null && threadInfos.length > 0){
            for (ThreadInfo threadInfo : threadInfos){
                PKt.out("死锁线程信息: "+threadInfo);
            }
        }

    }

    private static void method1(){
        synchronized (obj1){
            PKt.out("method1(): 获取到 obj1 锁");
            synchronized (obj2){
                PKt.out("method1(): 获取到 obj2 锁");
            }
        }
    }

    private static void method2(){
        synchronized (obj2){
            PKt.out("method2(): 获取到 obj2 锁");
            synchronized (obj1){
                PKt.out("method2(): 获取到 obj1 锁");
            }
        }
    }

}
