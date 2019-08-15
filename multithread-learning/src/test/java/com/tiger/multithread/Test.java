package com.tiger.multithread;

import com.tiger.multithread.ch14.Singleton7;

public class Test {

    @org.junit.Test
    public void test01() {
        Singleton7 instance = Singleton7.getInstance();
        System.out.println(instance == null);
    }


}
