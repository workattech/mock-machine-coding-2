package com.splitwise.machine.coding;

import java.util.ArrayList;
import java.util.List;

public class ExactExpense implements Expense {

	@Override
	public List<UserExpenseProfile> splitExpense(double amount, List<String> splitTo, List<Integer> scales) {
		List<UserExpenseProfile> userExpenseProfiles = new ArrayList<>();
		
		for(int x=0; x< splitTo.size(); x++) {
			UserExpenseProfile profile = new UserExpenseProfile(splitTo.get(x), scales.get(x));
			userExpenseProfiles.add(profile);
		}
		return userExpenseProfiles;
	}
}
