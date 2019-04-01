package com.tiger.memcached.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    private static final long serialVersionUID = 1043213787242906546L;
    private String key = null;
    private String name;
    private int age;

    private List<String> favorites = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getKey() {
        if (key == null) {
            key = name;
        }
        return key;
    }

    public List<String> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<String> favorates) {
        this.favorites.clear();
        this.favorites.addAll(favorates);
    }
}
