package com.splitwise.transactionFactory;

public interface Transaction {

    public void processTransaction(String [] inputArr);
    public boolean validateInput(String[] inputArr);
}
