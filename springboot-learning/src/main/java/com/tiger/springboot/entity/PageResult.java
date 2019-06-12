package com.tiger.springboot.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果
 * @param <T>
 */
public class PageResult<T> implements Serializable {
    private int pageSize;
    private int pageNumber;
    private int pageCount;
    private List<T> result;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}
