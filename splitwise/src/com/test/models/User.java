package com.test.models;

public class User {
    private String uid;
    private String name;
    private String email;
    private String phoneno;

    public User(String uid,String name, String email, String phoneno)
    {
        this.uid=uid;
        this.name=name;
        this.email=email;
        this.phoneno=phoneno;
    }
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

}
