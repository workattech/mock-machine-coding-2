package com.expense;

public class ExpenseFactory {
	public static Expense getExpense(String expenseDetails) throws Exception {
		if(expenseDetails.contains("EQUAL"))
				return new EqualExpense();
		else if(expenseDetails.contains("PERCENT"))
			return new PercentExpense();
		else if(expenseDetails.contains("EXACT"))
				return new ExactExpense();		
		throw new Exception("Unspoorted expense");
	}
}
