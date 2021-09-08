package com.sameerpandit.business;

import com.sameerpandit.models.Expense;

import org.jvnet.hk2.annotations.Service;

import jakarta.inject.Named;

@Service
@Named("PERCENT")
public class PercentSplitStrategy implements SplitStrategy {
    @Override
    public Integer[] split(Expense expense) {
        int amountUnScaled = expense.getUnscaledAmount();
        int totalParts = (expense.getParticipants().length);
        Integer[] splitUnscaledPercentages = expense.getSplitPercentages();
        Integer[] splitAmounts = new Integer[totalParts];
        int total = 0;
        for (int i = 0; i < splitAmounts.length; i++) {
            splitAmounts[i] = (splitUnscaledPercentages[i] * amountUnScaled) / Constants.PERCENT_SCALE;
            total += splitAmounts[i];
        }
        splitAmounts[0] += (amountUnScaled - total);
        return splitAmounts;
    }
}
