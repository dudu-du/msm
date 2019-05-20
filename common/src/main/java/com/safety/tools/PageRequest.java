package com.safety.tools;


public class PageRequest {
    public static int defaultPageSize = 10;
    protected int page = 1;
    protected int size;
    protected String pagerId;
    protected int sort;

    public PageRequest() {
        this.size = defaultPageSize;
    }

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSort() {
        return this.sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getPagerId() {
        return this.pagerId;
    }

    public void setPagerId(String pagerId) {
        this.pagerId = pagerId;
    }

    public int calcOffset() {
        return (this.page - 1) * this.size;
    }

    public String toString() {
        return "PageRequest{page=" + this.page + ", size=" + this.size + ", size=" + this.size + ", pagerId=" + this.pagerId + '}';
    }
}

