package com.splitwise.splitStrategy;

import com.splitwise.model.ExpenseTransactionInfo;

public interface SplitStartegy {

    public void executeSplit(ExpenseTransactionInfo expTxn);
}
