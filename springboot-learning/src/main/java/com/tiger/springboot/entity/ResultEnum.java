package com.tiger.springboot.entity;

public enum ResultEnum {

    SUCCESS(200, "success"),
    SERVER_ERROR(500, "server error"),
    USER_EXISTS(101,"user exists"),
    USER_ERROR(102,"user name or password error");

    private int code;
    private String desc;

    ResultEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
