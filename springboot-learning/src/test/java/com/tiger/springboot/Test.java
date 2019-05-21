package com.tiger.springboot;

import java.util.Calendar;

public class Test {

    @org.junit.Test
    public void test01(){
        Calendar instance = Calendar.getInstance();
        System.out.println(instance.get(Calendar.ZONE_OFFSET)/3600);
    }

}
