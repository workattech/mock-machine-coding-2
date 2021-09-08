package com.sameerpandit.models;

public class Transaction {
    private Integer unscaledAmount;
    private String creditorId;
    private String debitorId;

    public Transaction(Integer unscaledAmount, String creditorId, String debitorId) {
        this.unscaledAmount = unscaledAmount;
        this.creditorId = creditorId;
        this.debitorId = debitorId;
    }

    public Integer getUnscaledAmount() {
        return unscaledAmount;
    }

    public String getCreditorId() {
        return creditorId;
    }

    public String getDebitorId() {
        return debitorId;
    }
}
