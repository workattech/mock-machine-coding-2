package com.gaurparas;

import java.util.List;

public class EqualExpense extends Expense{

    public EqualExpense(double amount, User paidBy, List<Split> splits, ExpenseMetaData metaData) {
        super(amount, paidBy, splits, metaData);
    }

    @Override
    public boolean validate() {
        List<Split> splits = getSplits();

        for(Split split:splits) {
            if (!(split instanceof EqualSplit)){
                return false;
            }
        }

        return true;
    }
}
