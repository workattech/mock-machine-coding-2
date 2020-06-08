package com.splitwise.transactionFactory;

import com.splitwise.driver.SplitwiseDriver;
import com.splitwise.model.User;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class BalanceTransaction implements Transaction{

    @Override
    public void processTransaction(String[] inputArr) {
        boolean isValid = validateInput(inputArr);
        if(!isValid){
            System.err.println("Not valid input in Transaction: "+ Arrays.toString(inputArr));
            return;
        }
        if(inputArr.length == 1 && inputArr[0].toUpperCase().equals("SHOW")){
            Set<User> users = SplitwiseDriver.getUserRegistry().getUserRegistry();
            users = users.stream().filter(user-> !user.getExpenseKeepingBook().isEmpty()).collect(Collectors.toSet());
            if(users.isEmpty()){
                System.out.println("No balances");
                return;
            }

            for(User user : users){
                Map<User,Double> expenseMapping = user.getExpenseKeepingBook();
                expenseMapping.forEach((u,amount)->{
                    if(amount < 0){
                        System.out.println("User "+user.getName()+"owes User "+u.getName()+"amount :"+(-1*amount));
                    }
                });
            }

        }else if(inputArr.length == 2){
            User u =  SplitwiseDriver.getUserRegistry().searchUserFromRegistry(inputArr[1]).get();
            if(u.getExpenseKeepingBook().isEmpty()){
                System.out.println("No balances");
                return;
            }

            Map<User,Double> expenseMapping = u.getExpenseKeepingBook();
            expenseMapping.forEach((user,amount)->{
                if(amount < 0){
                    System.out.println("User "+u.getName()+"owes User "+user.getName()+"amount :"+(-1*amount));
                }else {
                    System.out.println("User "+user.getName()+"owes User "+u.getName()+"amount :"+amount);
                }
            });
        }

    }

    @Override
    public boolean validateInput(String[] inputArr) {
        if(inputArr.length > 2)
            return false;
        if(!inputArr[0].equalsIgnoreCase("show"))
            return false;
        if(inputArr.length == 2){
            String userName = inputArr[1];
            Optional<User> user = SplitwiseDriver.getUserRegistry().searchUserFromRegistry(userName);
            if(!user.isPresent())
                return false;
        }
        return true;
    }
}
