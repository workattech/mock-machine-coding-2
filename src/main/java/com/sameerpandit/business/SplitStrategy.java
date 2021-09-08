package com.sameerpandit.business;

import com.sameerpandit.models.Expense;

import org.jvnet.hk2.annotations.Contract;

@Contract
public interface SplitStrategy {
    public Integer[] split(Expense expense);
}
