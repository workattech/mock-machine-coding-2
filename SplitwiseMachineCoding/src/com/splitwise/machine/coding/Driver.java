package com.splitwise.machine.coding;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {

	static LedgerService ledgerService = new LedgerService();

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		while (sc.hasNext()) {
			String line = sc.nextLine();
			String[] inputs = line.split(" ");
			String command = inputs[0];
			if (command.equals("EXPENSE")) {
				String payerId = inputs[1];
				double amount = Double.valueOf(inputs[2]);
				int users = Integer.valueOf(inputs[3]);
				List<String> splitTo = new ArrayList<>(users);
				int x = 0;
				for (; x < users; x++) {
					splitTo.add(inputs[x + 4]);
				}
				String expenseType = inputs[x + 4];
				List<Integer> scales = new ArrayList<>();
				if (!expenseType.equals("EQUAL")) {
					for (int y = 0; y < users; y++) {
						scales.add(Integer.valueOf((inputs[x + 4 + 1 + y])));
					}
				}

				createUserBase();
				ledgerService.splitExpenses(expenseType, payerId, amount, splitTo, scales);

			}
			if (command.equals("SHOW")) {
				inputs = line.split(" ");
				if (inputs.length > 1) {
					ledgerService.getUserExpensesSheet(inputs[1]);
				} else {
					ledgerService.getAllUserExppenses();
				}
			}
		}
	}

	private static void createUserBase() {
		// Creating dummy users for now , it can be created using some file or
		// some other ways.
		User user1 = new User("u1", "User1", "User1@gmail.com", "123");
		User user2 = new User("u2", "User2", "User2@gmail.com", "1234");
		User user3 = new User("u3", "User3", "User3@gmail.com", "1235");
		User user4 = new User("u4", "User4", "User4@gmail.com", "123455");
		UserBase.addUser(user1);
		UserBase.addUser(user2);
		UserBase.addUser(user3);
		UserBase.addUser(user4);

	}
}
