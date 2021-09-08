package com.sameerpandit.business;

import com.sameerpandit.models.Expense;

import org.jvnet.hk2.annotations.Service;

import jakarta.inject.Named;

@Service
@Named("EQUAL")
public class EqualSplitStrategy  implements  SplitStrategy{
    @Override
    public Integer[] split(Expense expense) {
        int amountUnScaled = expense.getUnscaledAmount();
        int totalParts = (expense.getParticipants().length);
        boolean cleanSplit = (amountUnScaled%totalParts == 0);
        Integer[] splitAmounts = new Integer[totalParts];
        int start = 0;
        if(!cleanSplit){
            while(amountUnScaled!=0){
                int part = amountUnScaled/(totalParts-start);
                splitAmounts[start] = part;
                amountUnScaled-=part;
                start++;
            }
        }else{
            for(;start<splitAmounts.length;start++){
                splitAmounts[start] = (amountUnScaled/totalParts);
            }
        }
        return splitAmounts;
    }
}
