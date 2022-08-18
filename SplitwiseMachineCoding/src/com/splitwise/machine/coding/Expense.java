package com.splitwise.machine.coding;

import java.util.List;

public interface Expense {

	List<UserExpenseProfile> splitExpense(double amount, List<String> splitTo, List<Integer> scales);
}
