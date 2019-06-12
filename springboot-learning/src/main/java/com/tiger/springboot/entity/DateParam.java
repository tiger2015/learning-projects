package com.tiger.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class DateParam {
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date start;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date end;

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
