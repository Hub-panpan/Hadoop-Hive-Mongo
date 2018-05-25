package com.po;

import java.util.List;

public class lxrsInf {
    private String lxrname;
    private List<telsInf> telinformation;
    private long number;

    public lxrsInf() {
    }

    public lxrsInf(String lxrname,List<telsInf> telinformation, long number ) {
        this.lxrname = lxrname;
        this.telinformation = telinformation;
        this.number = number;
    }

    public List<telsInf> getTelinformation() {
        return telinformation;
    }

    public void setTelinformation(List<telsInf> telinformation) {
        this.telinformation = telinformation;
    }

    public String getLxrname() {
        return lxrname;
    }

    public void setLxrname(String lxrname) {
        this.lxrname = lxrname;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }
}
