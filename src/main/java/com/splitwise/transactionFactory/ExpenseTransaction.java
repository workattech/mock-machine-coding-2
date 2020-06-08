package com.splitwise.transactionFactory;

import com.splitwise.driver.SplitwiseDriver;
import com.splitwise.model.ExpenseTransactionInfo;
import com.splitwise.model.User;
import com.splitwise.splitStrategy.SplitStartegy;
import com.splitwise.splitStrategy.impl.SplitByEqual;
import com.splitwise.splitStrategy.impl.SplitByExact;
import com.splitwise.splitStrategy.impl.SplitByPercent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ExpenseTransaction implements Transaction{


    SplitStartegy byEqual;
    SplitStartegy byExact;
    SplitStartegy byPercent;
    public ExpenseTransaction(){
        byEqual = new SplitByEqual();
        byPercent = new SplitByPercent();
        byExact = new SplitByExact();
    }
    @Override
    public void processTransaction(String[] inputArr) {
        boolean isValid = validateInput(inputArr);
        if(!isValid){
            return;
        }

        ExpenseTransactionInfo expTxn = getExpenseTransactionInfo(inputArr);
        String splitType = inputArr[4+expTxn.getNoOfPeopleInTxn()];
        switch(splitType.toUpperCase()){
            case "EXACT":
               byExact.executeSplit(expTxn);
               break;
            case "PERCENT":
                byPercent.executeSplit(expTxn);
                break;
            case "EQUAL":
                byEqual.executeSplit(expTxn);
                break;

        }

    }
    private ExpenseTransactionInfo getExpenseTransactionInfo(String[] inputArr){
        double amount = Integer.parseInt(inputArr[2]);
        int noOfPeopleinTxn = Integer.parseInt(inputArr[3]);
        List<User> usersInTxn = new ArrayList();
        for(int i = 4; i < noOfPeopleinTxn+4;i++){
            String userName = inputArr[i];
            Optional<User> u = SplitwiseDriver.getUserRegistry().searchUserFromRegistry(userName);
            // TODO should be part of validation
            if(u.isPresent()){
                usersInTxn.add(u.get());
            }else{
                System.err.println("User in Transaction not present in user Registory: "+ Arrays.toString(inputArr));
                //return;
            }
        }
        String splitType = inputArr[4+noOfPeopleinTxn];

        List<Double> args = new ArrayList<>();
        for(int i = 5+noOfPeopleinTxn;i<inputArr.length;i++){
            Double num = Double.parseDouble(inputArr[i]);
            args.add(num);
        }
        Optional<User> u = SplitwiseDriver.getUserRegistry().searchUserFromRegistry(inputArr[1]);
        User paidByUser = u.get();
        ExpenseTransactionInfo expTxn = new ExpenseTransactionInfo(amount,noOfPeopleinTxn,usersInTxn,paidByUser,args);
        return expTxn;
    }

    @Override
    public boolean validateInput(String[] inputArr) {
        int size = inputArr.length;
        try {
            int amount = Integer.parseInt(inputArr[2]);
            int noOfPeopleinTxn = Integer.parseInt(inputArr[3]);
            int index = 3 +noOfPeopleinTxn+1;
            String splitType = inputArr[index];
            if(splitType.equalsIgnoreCase("EXACT") || splitType.equalsIgnoreCase("PERCENT")){
                if(size-1- index != noOfPeopleinTxn){
                    System.err.println("Not valid  Transaction: "+ Arrays.toString(inputArr));
                    return false;
                }
                else
                    return true;
            }else if(splitType.equalsIgnoreCase("EQUAL") && index == size-1){
                return true;
            }else{
                System.err.println("Not valid amount in Transaction: "+ Arrays.toString(inputArr));
                return false;
            }
        }catch (NumberFormatException e){
            System.err.println("Not valid integer input in Transaction: "+ Arrays.toString(inputArr));
            return false;
        }
    }
}
