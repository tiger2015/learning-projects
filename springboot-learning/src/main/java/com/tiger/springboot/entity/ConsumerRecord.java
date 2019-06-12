package com.tiger.springboot.entity;

import java.io.Serializable;
import java.util.Date;

public class ConsumerRecord implements Serializable {

    private int id;

    private Date consumerDate;

    private double consumerAmount;

    private ConsumerType consumerType;

    private String user;

    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getConsumerDate() {
        return consumerDate;
    }

    public void setConsumerDate(Date consumerDate) {
        this.consumerDate = consumerDate;
    }

    public double getConsumerAmount() {
        return consumerAmount;
    }

    public void setConsumerAmount(double consumerAmount) {
        this.consumerAmount = consumerAmount;
    }

    public ConsumerType getConsumerType() {
        return consumerType;
    }

    public void setConsumerType(ConsumerType consumerType) {
        this.consumerType = consumerType;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
