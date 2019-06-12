package com.tiger.springboot.entity;

import java.io.Serializable;

/**
 * 消费类型
 */
public class ConsumerType implements Serializable {

    private String type;
    private String description;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
