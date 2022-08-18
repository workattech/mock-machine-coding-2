package com.expense;

import java.util.ArrayList;
import java.util.List;

public class PercentExpense extends Expense {

	@Override
	public List<ExpenseLog> splitExpense(String expenseDetails) {
		String[] details = expenseDetails.split(" ");
		String paidByUserId = details[1];
		double amount = Double.parseDouble(details[2]);
		int totalUsersInvolved = Integer.parseInt(details[3]);
		List<ExpenseLog> expenseLogList = new ArrayList<ExpenseLog>();
		double amountDistributed = 0.0;
		for(int i = 0 ; i < totalUsersInvolved ; i++ ) {
			String userId = details[4+i];
			double percentContibution = Double.parseDouble(details[4+totalUsersInvolved+i+1]);
			double userContribution = Double.parseDouble(decimalFormat.format((amount*percentContibution)/100));
			amountDistributed += userContribution;
			ExpenseLog expenseLog = new ExpenseLog(paidByUserId, userId, userContribution);
			expenseLogList.add(expenseLog);
		}
		// calculated offset and add it to first user
		double contributionByFirstUser = expenseLogList.get(0).getAmount()+(amount - amountDistributed);
		expenseLogList.get(0).setAmount(contributionByFirstUser);
		return expenseLogList;
	}

	@Override
	public boolean validateExpense(String expenseDetails) {
		String[] details = expenseDetails.split(" ");
		int totalUsersInvolved = Integer.parseInt(details[3]);
		double totalPercent = 100;
		for(int i = 0 ; i < totalUsersInvolved ; i++ ) {
			double percentContibution = Double.parseDouble(details[4+totalUsersInvolved+i+1]);
			totalPercent -=percentContibution;
		}
		return (totalPercent == 0.0)?true:false;
	}

}
