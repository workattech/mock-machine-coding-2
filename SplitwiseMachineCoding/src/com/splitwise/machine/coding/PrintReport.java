package com.splitwise.machine.coding;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author Hakim.s
 * 
 *         Optional requirement 3 can be done using this class. A way to show
 *         the passbook for a user. [The entries should show all the
 *         transactions a user was part of. You can print in any format you
 *         like]
 *
 */
public class PrintReport {

	public static void printUserBalances(String userId, List<UserExpenseProfile> userExpensesSheet) {
		if (userExpensesSheet == null || userExpensesSheet.isEmpty()) {
			System.out.println("No balances");
			return;
		}
		for (UserExpenseProfile userExpenseProfile : userExpensesSheet) {
			if (userExpenseProfile.getExpenses() < 0) {
				printMessage(userId, userExpenseProfile.getUserId(), userExpenseProfile.getExpenses());
			} else if (userExpenseProfile.getExpenses() > 0) {
				printMessage(userExpenseProfile.getUserId(), userId, userExpenseProfile.getExpenses());

			}
		}

	}

	public static void printAllBalances(Map<String, List<UserExpenseProfile>> userExpensesMap) {
		if (userExpensesMap == null || userExpensesMap.isEmpty()) {
			System.out.println("No balances");
			return;
		}
		Set<Entry<String, List<UserExpenseProfile>>> entries = userExpensesMap.entrySet();
		for (Entry<String, List<UserExpenseProfile>> entry : entries) {
			printUserBalances(entry.getKey(), entry.getValue());
		}

	}

	private static void printMessage(String borrower, String payer, double expenses) {
		System.out.println(UserBase.getUser(borrower).getName() + " owes " + UserBase.getUser(payer).getName() + ": "
				+ Math.abs(expenses));
	}

}
