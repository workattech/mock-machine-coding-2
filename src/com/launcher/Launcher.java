package com.launcher;

import com.expenseSharing.model.User;
import com.expenseSharing.service.ExpenseSharingService;

public class Launcher {
	/*
	 * SHOW
SHOW u1
EXPENSE u1 1250 4 u1 u2 u3 u4 EQUAL
SHOW u4
SHOW u1
EXPENSE u1 1250 2 u2 u3 EXACT 370 880
SHOW
EXPENSE u4 1200 4 u1 u2 u3 u4 PERCENT 40 20 20 20
SHOW u1
SHOW
	 */
	private static ExpenseSharingService service;
	public static void main(String args[]) {
		service = new ExpenseSharingService();
		User u1 = new User("User1", "u1", "u1@email", 9999999999L);
		User u2 = new User("User2", "u2", "u2@email", 9999999999L);
		User u3 = new User("User3", "u3", "u3@email", 9999999999L);
		User u4 = new User("User4", "u4", "u4@email", 9999999999L);
		service.addUser(u1);
		service.addUser(u2);
		service.addUser(u3);
		service.addUser(u4);
		
		String[] inputs = new String[10];
		inputs[0] = "SHOW";
		inputs[1] = "SHOW u1";
		inputs[2] = "EXPENSE u1 1000 3 u2 u1 u3 EQUAL";
		inputs[3] = "SHOW u4";
		inputs[4] = "SHOW u1";
		inputs[5] = "EXPENSE u1 1250 2 u2 u3 EXACT 370 880";
		inputs[6] = "SHOW";
		inputs[7] = "EXPENSE u4 1200 4 u1 u2 u3 u4 PERCENT 40 20 20 20";
		inputs[8] = "SHOW u1";
		inputs[9] = "SHOW";
		
		processInput(inputs);
	}
	
	private static void processInput(String[] inputs) {
		for(String input : inputs) {
			if(input.contains("EXPENSE")) {
				try {
					service.splitExpense(input);
				} catch (Exception e) {
					System.out.println("Exception occured");
				}
			}
			else if(input.equals("SHOW"))
				service.showBalance();
			else {
				String userId = input.split(" ")[1];
				service.showBalance(userId);
			}
		}
		
	}
	
}
