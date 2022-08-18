package com.splitwise.machine.coding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author Hakim.s
 *
 */
public class LedgerBook {

	private Map<String, List<UserExpenseProfile>> userExpensesProfile;

	public LedgerBook() {

		this.userExpensesProfile = new HashMap<>();
	}

	public void addExpenseEntry(String payer, UserExpenseProfile paidTo) {

		UserExpenseProfile payerProfile = new UserExpenseProfile(payer, (paidTo.getExpenses() * -1));

		updateExpense(payer, paidTo);

		// update reverse association.
		updateExpense(paidTo.getUserId(), payerProfile);

	}

	private void updateExpense(String payer, UserExpenseProfile paidTo) {

		if (getUserExpensesProfile().containsKey(payer)) {
			List<UserExpenseProfile> profiles = getUserExpensesProfile().get(payer);

			if (profiles.contains(paidTo)) {
				UserExpenseProfile existingProfile = new CollectionUtils<UserExpenseProfile>().get(profiles, paidTo);
				existingProfile.setExpenses(existingProfile.getExpenses() + paidTo.getExpenses());
				// System.out.println(existingProfile.getExpenses());
			} else {
				profiles.add(paidTo);
			}

		} else {
			// Add new entry.
			List<UserExpenseProfile> userExpenseProfiles = new ArrayList<>();
			userExpenseProfiles.add(paidTo);
			getUserExpensesProfile().put(payer, userExpenseProfiles);
		}
	}

	public List<UserExpenseProfile> getExpensesForUser(String userId) {
		return getUserExpensesProfile().get(userId);
	}

	public Map<String, List<UserExpenseProfile>> getAllLedgerExpenses() {

		Map<String, List<UserExpenseProfile>> filteredUserExpenses = new HashMap<>();
		Set<Entry<String, List<UserExpenseProfile>>> entrySet = userExpensesProfile.entrySet();
		for (Entry<String, List<UserExpenseProfile>> entry : entrySet) {
			List<UserExpenseProfile> value = entry.getValue();
			List<UserExpenseProfile> filteredProfiles = new ArrayList<>();
			for (UserExpenseProfile userExpenseProfile : value) {
				if (userExpenseProfile.getExpenses() < 0) {
					filteredProfiles.add(userExpenseProfile);
				}
			}
			if(!filteredProfiles.isEmpty())
				filteredUserExpenses.put(entry.getKey(), filteredProfiles);
		}
		return filteredUserExpenses;

	}

	private Map<String, List<UserExpenseProfile>> getUserExpensesProfile() {
		return userExpensesProfile;
	}

}
