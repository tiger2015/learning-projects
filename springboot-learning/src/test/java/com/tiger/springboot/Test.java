package com.tiger.springboot;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Test {

    @org.junit.Test
    public void test01(){
        Calendar instance = Calendar.getInstance();
        System.out.println(instance.get(Calendar.ZONE_OFFSET)/3600);
    }

    @org.junit.Test
   public void test02() throws IOException {
       Map<String,double[]> map = new HashMap<>();
       map.put("1",new double[]{1.2334,3.444,56.7777});
       map.put("3",new double[]{1.2334,3.44466,56.775577});

       ObjectMapper mapper = new ObjectMapper();
       String value = mapper.writeValueAsString(map);
       System.out.println(value);

       String key="{\"1\":[1.2334,3.444,56.7777],\"3\":[1.2334,3.44466,56.775577]}";

        HashMap<String,double[]> hashMap = mapper.readValue(key, HashMap.class);

        System.out.println(hashMap.get("1"));

    }

}
