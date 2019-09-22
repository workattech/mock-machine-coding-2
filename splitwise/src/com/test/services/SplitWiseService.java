package com.test.services;
import com.test.models.User;

import java.util.*;

public class SplitWiseService {
    Map<String,User> usersMap=new HashMap<String,User>();
    HashMap<User,HashMap<User,Integer>> expenseTracker=new HashMap<User,HashMap<User,Integer>>();

    public void setUsers(List<User> users) {
        for(User user:users)
        {
            usersMap.put(user.getUid(),user);
            expenseTracker.put(user,new HashMap<User,Integer>());
        }
    }

    public void splitBalance(String command) {
        String[] expensedata=command.split(" ");
        String paidUser=expensedata[1];
        int totalPaid=Integer.parseInt(expensedata[2]);
        int usersInvolved=Integer.parseInt(expensedata[3]);

        if (expensedata[3+usersInvolved+1].equals("EQUAL"))
        {
            for (int i=1;i<=usersInvolved;i++)
            {
                updateExpenses(paidUser,expensedata[3+i],totalPaid/usersInvolved);
            }
        }
        else if(expensedata[3+usersInvolved+1].equals("EXACT"))
        {
            if(isValidCommand(expensedata,"EXACT",usersInvolved))
            {
                for (int i=1;i<=usersInvolved;i++)
                {
                    updateExpenses(paidUser,expensedata[3+i],Integer.parseInt(expensedata[3+usersInvolved+1+i]));
                }
            }
            else {
                System.out.println("Given Values are not equals to Total amount paid");
            }
        }
        else if(expensedata[3+usersInvolved+1].equals("PERCENT"))
        {
            if(isValidCommand(expensedata,"PERCENT",usersInvolved)) {
                for (int i = 1; i <= usersInvolved; i++) {
                    updateExpenses(paidUser, expensedata[3 + i], (totalPaid * Integer.parseInt(expensedata[3 + usersInvolved + 1 + i])) / 100);
                }
            }
            else
            {
                System.out.println("Given Percentages are not 100%");
            }
        }

    }
    public void executeCommand(String command)
    {
        if(command.contains("SHOW"))
        {
            ShowBalanceService showBalanceService=new ShowBalanceService();
            if(command.split(" ").length>1)
            {
                showBalanceService.showUserBalance(expenseTracker,usersMap.get(command.split(" ")[1]));
            }
            else
            {
                showBalanceService.showBalance(expenseTracker);
            }
        }
        else
        {
            splitBalance(command);
        }
    }

    public void updateExpenses(String paidUser,String oweUser,int amount)
    {
        if(!paidUser.equals(oweUser)) {
            if (!expenseTracker.get(usersMap.get(paidUser)).containsKey(usersMap.get(oweUser))) {
                expenseTracker.get(usersMap.get(paidUser)).put(usersMap.get(oweUser), amount);
            } else {
                expenseTracker.get(usersMap.get(paidUser)).put(usersMap.get(oweUser), expenseTracker.get(usersMap.get(paidUser)).get(usersMap.get(oweUser)) + amount);
            }
            if (!expenseTracker.get(usersMap.get(oweUser)).containsKey(usersMap.get(paidUser))) {
                expenseTracker.get(usersMap.get(oweUser)).put(usersMap.get(paidUser), -amount);
            } else {
                expenseTracker.get(usersMap.get(oweUser)).put(usersMap.get(paidUser), expenseTracker.get(usersMap.get(oweUser)).get(usersMap.get(paidUser)) - amount);
            }
        }
    }

    boolean isValidCommand(String expensedata[],String expenseType,int usersInvolved)
    {
        int sum=0;
        for(int i=1;i<=usersInvolved;i++)
        {
           sum+=Integer.parseInt(expensedata[3+usersInvolved+1+i]);
        }
        if(expenseType.equals("EXACT"))
        {
            return sum==Integer.parseInt(expensedata[2]);
        }
        else if(expenseType.equals("PERCENT"))
        {
            return sum==100;
        }
        return false;
    }


}
