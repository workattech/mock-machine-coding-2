package com.sameerpandit;

import com.sameerpandit.service.ExpenseManagerService;
import com.sameerpandit.service.UserService;

import org.jvnet.hk2.annotations.Service;

import jakarta.inject.Inject;

@Service
public class Splitwise {

    @Inject
    ExpenseManagerService expenseManagerService;

    @Inject
    UserService userService;

    public String  addUser(String name, String email, String phone) {
        return userService.addUser(name,email,phone);
    }

    public void addExpense(Integer amount, String type, String initiatorId, String[] participants){
        expenseManagerService.addExpense(amount,type,initiatorId,participants);
    }

    public void addExpense(Integer amount, String type, String initiatorId, String[] participants, Integer[] splits){
        expenseManagerService.addExpense(amount,type,initiatorId,participants,splits);
    }

    public void show(){
        expenseManagerService.show();
    }

    public void show(String userId){
        expenseManagerService.show(userId);
    }
}
