package com.gaurparas;

import com.sun.deploy.cache.BaseLocalApplicationProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseManager {

    List<Expense> expenses;
    Map<String, User> userMap;
    Map<String, Map<String, Double>> balanceSheet;

    public ExpenseManager() {
        expenses = new ArrayList<Expense>();
        userMap = new HashMap<String, User>();
        balanceSheet = new HashMap<String, Map<String, Double>>();
    }

    public void addUser(User user) {
        userMap.put(user.getId(), user);
        balanceSheet.put(user.getId(), new HashMap<String, Double>());
    }

    public void showBalances() {
        boolean isEmpty = true;
        for(Map.Entry<String, Map<String, Double>> allBalance : balanceSheet.entrySet()) {
            for (Map.Entry<String, Double> userBalance : allBalance.getValue().entrySet()) {
                if (userBalance.getValue() > 0) {
                    isEmpty = false;
                    printBalance(allBalance.getKey(), userBalance.getKey(), userBalance.getValue());
                }
            }
        }
        if (isEmpty) {
            System.out.println("No balances");
        }
    }

    public void showBalance(String userId) {
        boolean isEmpty = true;
        for(Map.Entry<String, Double> userBalance : balanceSheet.get(userId).entrySet()) {
            if(userBalance.getValue() != 0){
                isEmpty = false;
                printBalance(userId, userBalance.getKey(), userBalance.getValue());
            }
        }
        if (isEmpty) {
            System.out.println("No balances");
        }
    }

    private void printBalance(String user1, String user2, Double amount) {
        String user1Name = userMap.get(user1).getName();
        String user2Name = userMap.get(user2).getName();
        if (amount < 0) {
            System.out.println(user1Name + " owes " + user2Name + ": " + Math.abs(amount));
        } else if (amount > 0) {
            System.out.println(user2Name + " owes " + user1Name + ": " + Math.abs(amount));
        }
    }

    public void addExpense(ExpenseType expenseType, Double amount, String paidBy,
                           List<Split> splits, ExpenseMetaData metaData) {
        Expense expense = ExpenseService.createExpense(expenseType, amount, userMap.get(paidBy),
                splits, metaData);
        expenses.add(expense);

        Map<String, Double> userBalances = balanceSheet.get(paidBy);
        for(Split split:splits) {
            String oweUser = split.getUser().getId();
            double prevAmount = userBalances.getOrDefault(oweUser,0.0);
            double currentAmount = split.getAmount();
            userBalances.put(oweUser,prevAmount + currentAmount);

            Map<String, Double> OweUserBalances = balanceSheet.get(oweUser);
            prevAmount = OweUserBalances.getOrDefault(paidBy,0.0);
            OweUserBalances.put(paidBy,prevAmount - currentAmount);
        }
    }
}
