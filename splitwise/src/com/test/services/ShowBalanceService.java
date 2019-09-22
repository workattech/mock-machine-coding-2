package com.test.services;

import com.test.models.User;

import java.util.HashMap;
import java.util.Map;


public class ShowBalanceService {
    void showBalance(HashMap<User,HashMap<User,Integer>> expenseTracker)
    {
        boolean noData=true;

        for (Map.Entry mapElement : expenseTracker.entrySet()) {
            User user = (User)mapElement.getKey();
            HashMap<User,Integer> userBalance = (HashMap<User,Integer>)mapElement.getValue();
            if(userBalance.size()>0)
            {
                for (Map.Entry e : userBalance.entrySet()) {
                    User otherUser = (User)e.getKey();
                    int amount = (int)e.getValue();
                    if(amount>0) {
                        System.out.println(otherUser.getName() + " owes " + user.getName() +": "+amount);
                    }
                }
                noData=false;

            }
        }

        if(noData)
        {
            System.out.println("No balances");
        }
    }
    void showUserBalance(HashMap<User,HashMap<User,Integer>> expenseTracker, User uid)
    {
        if(expenseTracker.get(uid).size()>0)
        {
            for (Map.Entry mapElement : expenseTracker.get(uid).entrySet())
            {
                User otherUser = (User)mapElement.getKey();
                int amount  = 	(int)mapElement.getValue();
                if(amount>0) {
                    System.out.println(otherUser.getName() + " owes " + uid.getName() +": "+amount);
                }
                else
                {
                    System.out.println(uid.getName() + " owes " + otherUser.getName() +": "+-amount);
                }
            }
        }
        else
        {
            System.out.println("No balances");
        }
    }
}
