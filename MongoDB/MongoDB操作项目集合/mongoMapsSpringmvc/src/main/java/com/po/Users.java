package com.po;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class Users {
    @Id
    private String id;
    private String stuname;
    private int age;
    private String email;
    private double java;
    private double hadoop;
    private double hive;
    private String city;
    private String[] books;

    public Users() {
    }

    public Users(String stuname, int age, String email, double java, double hadoop, double hive, String city, String[] books) {
        this.stuname = stuname;
        this.age = age;
        this.email = email;
        this.java = java;
        this.hadoop = hadoop;
        this.hive = hive;
        this.city = city;
        this.books = books;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getJava() {
        return java;
    }

    public void setJava(double java) {
        this.java = java;
    }

    public double getHadoop() {
        return hadoop;
    }

    public void setHadoop(double hadoop) {
        this.hadoop = hadoop;
    }

    public double getHive() {
        return hive;
    }

    public void setHive(double hive) {
        this.hive = hive;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String[] getBooks() {
        return books;
    }

    public void setBooks(String[] books) {
        this.books = books;
    }
}
