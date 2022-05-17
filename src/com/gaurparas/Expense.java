package com.gaurparas;

import java.util.List;

public abstract class Expense {
    private String id;
    private List<Split> splits;
    private User paidBy;
    private double amount;
    private ExpenseMetaData metaData;

    public Expense(double amount, User paidBy, List<Split> splits, ExpenseMetaData metaData) {
        this.amount = amount;
        this.paidBy = paidBy;
        this.splits = splits;
        this.metaData = metaData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(User paidBy) {
        this.paidBy = paidBy;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public void setSplits(List<Split> splits) {
        this.splits = splits;
    }

    public ExpenseMetaData getMetadata() {
        return metaData;
    }

    public void setMetadata(ExpenseMetaData metaData) {
        this.metaData = metaData;
    }

    public abstract boolean validate();
}
