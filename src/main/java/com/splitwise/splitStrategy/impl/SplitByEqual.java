package com.splitwise.splitStrategy.impl;

import com.splitwise.driver.SplitwiseDriver;
import com.splitwise.model.ExpenseTransactionInfo;
import com.splitwise.model.User;
import com.splitwise.splitStrategy.SplitStartegy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SplitByEqual implements SplitStartegy {

    @Override
    public void executeSplit(ExpenseTransactionInfo expTxn) {
        User user = expTxn.getPaidByUser();
        for(int i=0;i<expTxn.getNoOfPeopleInTxn();i++){
            User u = expTxn.getUsersInTxn().get(i);
            if(user == u)
                continue;
            double amount = expTxn.getAmount()/expTxn.getNoOfPeopleInTxn();
            if(user.getExpenseKeepingBook().get(u) !=null){
                double val =user.getExpenseKeepingBook().get(u);
                user.getExpenseKeepingBook().put(u,val + amount);
                u.getExpenseKeepingBook().put(user,val - amount);
            }else{
                user.getExpenseKeepingBook().put(u,amount);
                u.getExpenseKeepingBook().put(user,-1*amount);
            }
        }
    }

}
