package com.java_simple.codes.jvm;

import com.java_simple.codes.PKt;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class VerifyCheckMain {

    static int b = 0;
    static{
        b = 1;
        c = 1;
    }
    static int c = 0;

    public static void main(String[] args) {
        PKt.out(b + ","+c);
    }
}
