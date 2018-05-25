package com.po;


import java.util.Date;

public class lxrUsers {
    private int uid;
    private String uname;
    private String upwd;
    private String truename;
    private String addtime;

    public lxrUsers() {
    }

    public lxrUsers(String uname, String upwd, String truename) {
        this.uname = uname;
        this.upwd = upwd;
        this.truename = truename;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }
}
