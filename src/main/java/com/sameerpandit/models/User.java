package com.sameerpandit.models;

public class User {
    public User(String userId, String name, String email, String mobileNo) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.mobileNo = mobileNo;
    }

    private String userId;
    private String name;

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                '}';
    }

    private String email;
    private String mobileNo;

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNo() {
        return mobileNo;
    }



}
