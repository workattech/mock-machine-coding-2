package com.splitwise.process;

import com.splitwise.transactionFactory.BalanceTransaction;
import com.splitwise.transactionFactory.ExpenseTransaction;
import com.splitwise.transactionFactory.Transaction;

import java.security.BasicPermission;

public class TransactionProcesser {
    Transaction expenseProcessor;
    Transaction balanceProcessor;
    public TransactionProcesser(){
        expenseProcessor = new ExpenseTransaction();
        balanceProcessor = new BalanceTransaction();
    }
    public void process(String input){
        String []inputArr = input.trim().split("\\s+");
        String action = inputArr[0];
        switch (action){
            case "EXPENSE":
                expenseProcessor.processTransaction(inputArr);
                break;
            case "SHOW":
                balanceProcessor.processTransaction(inputArr);
                break;
        }
    }
}
