package com.splitwise.model;

import java.util.List;

public class ExpenseTransactionInfo {
    private double amount;
    private int noOfPeopleInTxn;
    private List<User> usersInTxn;
    private String splitType;
    private User paidByUser;
    private List<Double> args;
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getNoOfPeopleInTxn() {
        return noOfPeopleInTxn;
    }

    public void setNoOfPeopleInTxn(int noOfPeopleInTxn) {
        this.noOfPeopleInTxn = noOfPeopleInTxn;
    }

    public List<User> getUsersInTxn() {
        return usersInTxn;
    }

    public void setUsersInTxn(List<User> usersInTxn) {
        this.usersInTxn = usersInTxn;
    }

    public String getSplitType() {
        return splitType;
    }

    public void setSplitType(String splitType) {
        this.splitType = splitType;
    }

    public User getPaidByUser() {
        return paidByUser;
    }

    public void setPaidByUser(User paidByUser) {
        this.paidByUser = paidByUser;
    }

    public List<Double> getArgs() {
        return args;
    }

    public void setArgs(List<Double> args) {
        this.args = args;
    }

    public ExpenseTransactionInfo(double amount, int noOfPeopleInTxn, List<User> usersInTxn,  User paidByUser, List<Double> args) {
        this.amount = amount;
        this.noOfPeopleInTxn = noOfPeopleInTxn;
        this.usersInTxn = usersInTxn;

        this.paidByUser = paidByUser;
        this.args = args;
    }
}
