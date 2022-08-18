package com.expense;

import java.util.ArrayList;
import java.util.List;

public class EqualExpense extends Expense{

	@Override
	public List<ExpenseLog> splitExpense(String expenseDetails) {
		String[] details = expenseDetails.split(" ");
		String paidByUserId = details[1];
		double amount = Double.parseDouble(details[2]);
		int totalUsersInvolved = Integer.parseInt(details[3]);
		double eachContribution = Double.parseDouble(decimalFormat.format(amount/totalUsersInvolved));
		double amountDistributed = 0.0;
		List<ExpenseLog> expenseLogList = new ArrayList<ExpenseLog>();
		for(int i = 0 ; i < totalUsersInvolved ; i++ ) {
			String userId = details[4+i];
			ExpenseLog expenseLog = new ExpenseLog(paidByUserId, userId, eachContribution);
			expenseLogList.add(expenseLog);
			amountDistributed += eachContribution;
		}
		// calculated offset and add it to first user
		double contributionByFirstUser = expenseLogList.get(0).getAmount()+(amount - amountDistributed);
		expenseLogList.get(0).setAmount(contributionByFirstUser);
		return expenseLogList;
	}

	@Override
	public boolean validateExpense(String expenseDetails) {
		return true;
	}

}
