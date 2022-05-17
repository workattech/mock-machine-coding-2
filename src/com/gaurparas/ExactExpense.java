package com.gaurparas;

import java.util.List;

public class ExactExpense extends Expense {

    public ExactExpense(double amount, User paidBy, List<Split> splits, ExpenseMetaData metaData) {
        super(amount, paidBy, splits, metaData);
    }

    @Override
    public boolean validate() {
        List<Split> splits = getSplits();
        double splitsAmount = 0;

        for(Split split:splits) {
            if (!(split instanceof EqualSplit)){
                return false;
            }
            splitsAmount+=split.getAmount();
        }

        return splitsAmount == getAmount();
    }
}
