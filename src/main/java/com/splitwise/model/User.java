package com.splitwise.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {

    private static int U_ID=0;
    private int userId;
    private String name;
    private String phoneNo;
    private String email;
    private int balance;
    private Map<User,Double> expenseKeepingBook;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return userId == user.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    public User(String name, String phoneNo, String email) {
        this.userId = U_ID++;
        this.name = name;
        this.phoneNo = phoneNo;
        this.email = email;
        expenseKeepingBook= new HashMap<>();
    }

    public static int getuId() {
        return U_ID;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Map<User, Double> getExpenseKeepingBook() {
        return expenseKeepingBook;
    }

    public void setExpenseKeepingBook(Map<User, Double> expenseKeepingBook) {
        this.expenseKeepingBook = expenseKeepingBook;
    }

    public static void setuId(int uId) {
        U_ID = uId;
    }
}
