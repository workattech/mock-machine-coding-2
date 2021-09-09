package com.gaurparas;

import java.util.List;

public class ExpenseService {
    public static Expense createExpense(ExpenseType expenseType, double amount, User paidBy,
                                        List<Split> splits, ExpenseMetaData metaData) {
        switch (expenseType) {
            case EXACT:
                return new ExactExpense(amount, paidBy, splits, metaData);
            case EQUAL:
                int totalSplits = splits.size();
                double splitAmount = ((double) Math.round(amount*100/totalSplits))/100.0;
                for(Split split:splits){
                    split.setAmount(splitAmount);
                }
                splits.get(0).setAmount(splitAmount + (amount-splitAmount*totalSplits));
                return new EqualExpense(amount, paidBy, splits, metaData);
            case PERCENT:
                for(Split split:splits){
                    double percentSplit = ((PercentSplit)split).getPercent();
                    split.setAmount(amount*percentSplit/100);
                }
                return new PercentExpense(amount, paidBy, splits, metaData);
            default:
                return null;
        }
    }
}
