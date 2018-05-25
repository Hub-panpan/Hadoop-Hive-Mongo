package com.po;

public class telsInf {
    private String tel;
    private String addtime;

    public telsInf() {
    }

    public telsInf(String tel, String addtime) {
        this.tel = tel;
        this.addtime = addtime;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }
}
