package com.expense;

import java.util.ArrayList;
import java.util.List;

public class ExactExpense extends Expense {

	@Override
	public List<ExpenseLog> splitExpense(String expenseDetails) {
		String[] details = expenseDetails.split(" ");
		String paidByUserId = details[1];
		int totalUsersInvolved = Integer.parseInt(details[3]);
		List<ExpenseLog> expenseLogList = new ArrayList<ExpenseLog>();
		for(int i = 0 ; i < totalUsersInvolved ; i++ ) {
			String userId = details[4+i];
			double contibution = Double.parseDouble(details[4+totalUsersInvolved+i+1]);
			ExpenseLog expenseLog = new ExpenseLog(paidByUserId, userId, contibution);
			expenseLogList.add(expenseLog);
		}
		return expenseLogList;
	}

	@Override
	public boolean validateExpense(String expenseDetails) {
		String[] details = expenseDetails.split(" ");
		double amount = Double.parseDouble(details[2]);
		int totalUsersInvolved = Integer.parseInt(details[3]);
		for(int i = 0 ; i < totalUsersInvolved; i++) {
			amount -= Double.parseDouble(details[4+totalUsersInvolved+i+1]);
		}
		return (amount == 0.0)?true:false;
	}

}
