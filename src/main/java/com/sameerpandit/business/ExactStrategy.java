package com.sameerpandit.business;

import com.sameerpandit.models.Expense;

import org.jvnet.hk2.annotations.Service;

import jakarta.inject.Named;

@Service
@Named("EXACT")
public class ExactStrategy implements SplitStrategy {

    @Override
    public Integer[] split(Expense expense) {
        return expense.getUnscaledSplitAmounts();
    }
}
