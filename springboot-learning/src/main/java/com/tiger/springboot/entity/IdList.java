package com.tiger.springboot.entity;

import java.util.ArrayList;
import java.util.List;

public class IdList {
    private List<Long> ids;

    public IdList() {
        ids = new ArrayList<>();
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}