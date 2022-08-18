package com.splitwise.machine.coding;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class EqualExpense implements Expense {

	@Override
	public List<UserExpenseProfile> splitExpense(double amount, List<String> splitTo, List<Integer> scales) {
		List<UserExpenseProfile> userExpenseProfiles = new ArrayList<>();
		
		DecimalFormat df = new DecimalFormat("#.##");      
		double equalAmount = Double.valueOf(df.format(amount/splitTo.size()));
		
		for(int x=0; x< splitTo.size(); x++) {
			UserExpenseProfile profile = new UserExpenseProfile(splitTo.get(x), equalAmount);
			userExpenseProfiles.add(profile);
		}
		return userExpenseProfiles;
	}

}
