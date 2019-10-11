package com.splitwise.machine.coding;

public class ExpenseFactory {

	public static Expense getInstance(ExpenseType expenseType) {

		switch (expenseType) {
		case EQUAL:
			return new EqualExpense();
		case EXACT:
			return new ExactExpense();
		case PERCENT:
			return new PercentExpense();

		default:
			break;
		}
		return null;
	}
}

enum ExpenseType {
	EQUAL,EXACT,PERCENT
}