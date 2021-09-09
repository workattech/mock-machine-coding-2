package com.gaurparas;

import java.util.List;

public class PercentExpense extends Expense{


    public PercentExpense(double amount, User paidBy, List<Split> splits, ExpenseMetaData metaData) {
        super(amount, paidBy, splits, metaData);
    }

    @Override
    public boolean validate() {
        List<Split> splits = getSplits();
        double splitsPercent = 0;
        double total = 100;

        for(Split split:splits) {
            if (!(split instanceof PercentSplit)){
                return false;
            }
            splitsPercent+=((PercentSplit) split).getPercent();
        }

        return splitsPercent == total;
    }
}
