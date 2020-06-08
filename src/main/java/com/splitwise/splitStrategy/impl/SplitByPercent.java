package com.splitwise.splitStrategy.impl;

import com.splitwise.model.ExpenseTransactionInfo;
import com.splitwise.model.User;
import com.splitwise.splitStrategy.SplitStartegy;

public class SplitByPercent implements SplitStartegy {
    @Override
    public void executeSplit(ExpenseTransactionInfo expTxn) {
        User user = expTxn.getPaidByUser();
        for(int i=0;i<expTxn.getNoOfPeopleInTxn();i++){
            User u = expTxn.getUsersInTxn().get(i);
            if(user == u)
                continue;

            double percent = expTxn.getArgs().get(i);
            double amount = expTxn.getAmount() * (double)(percent/100);
            if(user.getExpenseKeepingBook().get(u) !=null){
                double val =user.getExpenseKeepingBook().get(u);
                user.getExpenseKeepingBook().put(u,val + amount);
                val = u.getExpenseKeepingBook().get(user);
                u.getExpenseKeepingBook().put(user,val - amount);
            }else{
                user.getExpenseKeepingBook().put(u,amount);
                u.getExpenseKeepingBook().put(user,-1*amount);
            }
        }
    }

}
