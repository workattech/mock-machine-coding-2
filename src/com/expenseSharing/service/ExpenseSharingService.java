package com.expenseSharing.service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.expense.Expense;
import com.expense.ExpenseFactory;
import com.expense.ExpenseLog;
import com.expenseSharing.model.User;

public class ExpenseSharingService {
	Map<String, User> userMap;
	String pattern = "##.##";
	DecimalFormat decimalFormat = new DecimalFormat(pattern);
	public ExpenseSharingService() {
		userMap = new HashMap<String, User>();
	}
	public void addUser(User user) {
		userMap.put(user.getUserId(), user );
	}
	public User getUser(String userId) {
		return userMap.get(userId);
	}
	public void showBalance() {
		System.out.println("Printing whole Blance");
		for(Map.Entry<String, User> userEntry: userMap.entrySet()) {
			Map<String,Double> userLedger = userEntry.getValue().getWallet().getLedger();
			if(userLedger.size() == 0) {
				System.out.println("No Balance");
				return;
			}
			for(Map.Entry<String, Double> expenseDetails :  userLedger.entrySet()) {
				// Every user print how much others owes him
				String otherUser = expenseDetails.getKey();
				if(expenseDetails.getValue() > 0)
				{
					System.out.println(otherUser+" owes "+userEntry.getKey()+" : "+decimalFormat.format(expenseDetails.getValue()));
				}
			}
		}
	}
	public void showBalance(String userId) {
		System.out.println("Printing Blance for userID "+userId);
		User user = userMap.get(userId);
		Map<String,Double> userLedger = user.getWallet().getLedger();
		if(userLedger.size() == 0) {
			System.out.println("No Balance");
			return;
		}
		for(Map.Entry<String, Double> expenseDetails :  userLedger.entrySet()) {
			String otherUser = expenseDetails.getKey();
			if(expenseDetails.getValue() < 0)
			{
				System.out.println(userId+" owes "+otherUser+" : "+decimalFormat.format(-1*expenseDetails.getValue()));
			}
			else {
				System.out.println(otherUser+" owes "+userId+" : "+decimalFormat.format(expenseDetails.getValue()));
			}
		}
	}
	
	public void splitExpense(String expenseDetails) throws Exception {
		Expense expense = ExpenseFactory.getExpense(expenseDetails);
		if(!expense.validateExpense(expenseDetails)) {
			throw new Exception("validation failure");
		}
		List<ExpenseLog> spitDetails = expense.splitExpense(expenseDetails);
		for(ExpenseLog expenseLog  :spitDetails) {
			if(expenseLog.getPiadby().equals(expenseLog.getOwedBy()))
				continue;
			User paidByUser = userMap.get(expenseLog.getPiadby());
			User owedByUser = userMap.get(expenseLog.getOwedBy());
			paidByUser.getWallet().addOwedTo(owedByUser.getUserId(), expenseLog.getAmount());
			owedByUser.getWallet().addOwedFrom(paidByUser.getUserId(), expenseLog.getAmount());
		}
	}
}
