package com.po;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "myLxrs")
public class myLxrs {
    @Id
    private String id;
    private String username;
    private List<lxrsInf> lxrinformation;
    private long countLxr;

    public myLxrs() {
    }

    public myLxrs(String username, List<lxrsInf> lxrinformation, long countLxr) {
        this.username = username;
        this.lxrinformation = lxrinformation;
        this.countLxr = countLxr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<lxrsInf> getLxrinformation() {
        return lxrinformation;
    }

    public void setLxrinformation(List<lxrsInf> lxrinformation) {
        this.lxrinformation = lxrinformation;
    }

    public long getCountLxr() {
        return countLxr;
    }

    public void setCountLxr(long countLxr) {
        this.countLxr = countLxr;
    }
}
