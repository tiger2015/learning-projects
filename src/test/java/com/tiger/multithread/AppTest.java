package com.tiger.multithread;

import org.junit.Test;

import java.util.Calendar;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    @Test
    public void test01(){
        long time = 1559041644;
        // 1559045144
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(time*1000);
        System.out.println(instance.getTime());

    }
}
