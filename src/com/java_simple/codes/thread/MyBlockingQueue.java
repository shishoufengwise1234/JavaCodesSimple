package com.java_simple.codes.thread;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.LinkedList;

public class MyBlockingQueue<E> {

    private int mMaxSize;
    private LinkedList<E> mList;

    public MyBlockingQueue(int maxSize){
        this.mMaxSize = maxSize;
        this.mList = new LinkedList<>();
    }

    public void put(E e) throws InterruptedException{
        synchronized (this){
            while (mMaxSize == mList.size()){
                wait();
            }
            mList.add(e);

            notifyAll();
        }
    }

    public E take() throws InterruptedException{
        synchronized (this){
            while (mMaxSize == 0){
                wait();
            }
            notifyAll();
            return mList.remove();
        }
    }

}
