package com.po;

public class Paging {
    private long rows;

    public Paging() {
    }

    public Paging(long rows) {
        this.rows = rows;
    }

    public long getRows() {
        return rows;
    }

    public void setRows(long rows) {
        this.rows = rows;
    }
}
