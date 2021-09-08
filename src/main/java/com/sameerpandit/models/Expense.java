package com.sameerpandit.models;

import com.sameerpandit.business.Constants;

public class Expense {
    private Integer unscaledAmount;
    private String type;
    private String initiatorId;
    private String[] participants;
    private Integer[] unscaledSplitAmounts;
    private Integer[] splitPercentages;


    public Expense(Integer amount, String type, String initiatorId, String[] participants) {
        this.unscaledAmount = amount*Constants.EXPENSE_SCALING_FACTOR;
        this.type = type;
        this.initiatorId = initiatorId;
        this.participants = participants;
    }

    public Integer[] getUnscaledSplitAmounts() {
        return unscaledSplitAmounts;
    }

    public Integer[] getSplitPercentages() {
        return splitPercentages;
    }

    public Expense(Integer amount, String type, String initiatorId, String[] participants, Integer[] splits) {
        this.unscaledAmount = amount*Constants.EXPENSE_SCALING_FACTOR;
        this.type = type;
        this.initiatorId = initiatorId;
        this.participants = participants;
        if("EXACT".equals(type)){
            unscaledSplitAmounts = splits;
            for(int i=0;i<unscaledSplitAmounts.length;i++){
                unscaledSplitAmounts[i] = unscaledSplitAmounts[i]*Constants.EXPENSE_SCALING_FACTOR;
            }
        }else{
            splitPercentages = splits;
        }

    }

    public Integer getAmount() {
        return unscaledAmount / Constants.EXPENSE_SCALING_FACTOR;
    }

    public Integer getUnscaledAmount(){
        return unscaledAmount;
    }

    public String getType() {
        return type;
    }

    public String getInitiatorId() {
        return initiatorId;
    }

    public String[] getParticipants() {
        return participants;
    }
}
