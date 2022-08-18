package com.expense;

import java.text.DecimalFormat;
import java.util.List;

public abstract class Expense {
	protected String pattern = "##.##";
	protected DecimalFormat decimalFormat = new DecimalFormat(pattern);
	public abstract boolean validateExpense(String expenseDetails);
	public abstract List<ExpenseLog> splitExpense(String expenseDetails);
	
}
