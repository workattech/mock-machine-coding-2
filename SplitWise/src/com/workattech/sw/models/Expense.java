package com.workattech.sw.models;

public class Expense {
	private String expenseType;
	public Expense(String expenseType)
	{
		this.expenseType=expenseType;
	}
	public String getExpenseType()
	{
		return this.expenseType;
	}

}
