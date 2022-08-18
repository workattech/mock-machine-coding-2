package com.splitwise.machine.coding;

import java.util.List;
import java.util.Map;

public class LedgerService {

	LedgerBook ledgerBook = new LedgerBook();

	public void splitExpenses(String expenseType, String payer, double amount, List<String> splitTo,
			List<Integer> scales) {
		List<UserExpenseProfile> userExpenseProfiles = null;

		// Calling factory which will create a Expense Type object, we can take
		// any type in future.
		Expense expenseTypeInstance = ExpenseFactory.getInstance(ExpenseType.valueOf(expenseType));
		userExpenseProfiles = expenseTypeInstance.splitExpense(amount, splitTo, scales);

		for (UserExpenseProfile userExpenseProfile : userExpenseProfiles) {
			if (!payer.equals(userExpenseProfile.getUserId())) {
				ledgerBook.addExpenseEntry(payer, userExpenseProfile);
			}
		}

	}

	public void getUserExpensesSheet(String userId) {
		List<UserExpenseProfile> userExpensesSheet = ledgerBook.getExpensesForUser(userId);
		PrintReport.printUserBalances(userId, userExpensesSheet);
	}

	public void getAllUserExppenses() {
		Map<String, List<UserExpenseProfile>> userExpensesSheet = ledgerBook.getAllLedgerExpenses();

		PrintReport.printAllBalances(userExpensesSheet);
	}
}
