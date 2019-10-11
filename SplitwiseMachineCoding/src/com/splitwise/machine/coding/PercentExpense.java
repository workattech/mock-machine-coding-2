package com.splitwise.machine.coding;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PercentExpense implements Expense {

	@Override
	public List<UserExpenseProfile> splitExpense(double amount, List<String> splitTo, List<Integer> scales) {
		List<UserExpenseProfile> userExpenseProfiles = new ArrayList<>();

		for (int x = 0; x < splitTo.size(); x++) {
			DecimalFormat df = new DecimalFormat("#.##");
			double percentAmount = Double.valueOf(df.format((amount * scales.get(x)) / 100));

			UserExpenseProfile profile = new UserExpenseProfile(splitTo.get(x), percentAmount);
			userExpenseProfiles.add(profile);
		}
		return userExpenseProfiles;
	}

}
