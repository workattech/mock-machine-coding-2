package com.soumya.splitwise;

import java.util.*;

// ExpenseManager : Handles the User Registration and Book keeping tasks. Core functionality of Split Wise. 
public class ExpenseManager {

	private Map<String, User> users; // Map of registered Users, uid:User
	private Map<String, Float> expenses; // Map of Expenses "uid_borrower uid_lender":amount
	private int userCount; // Keeps count of registered users
	
	// Default Constructor
	public ExpenseManager() {
		users = new HashMap<>();
		expenses = new HashMap<>();
		userCount = 0;
	}
	
	// Register Users
	public void registerUser(String name, String email, String mob_no) {
		userCount++;
		String uid="u"+userCount;
		users.put(uid, new User(uid,name,email,mob_no));
	}
	
	// Display all registered Users
	public void displayRegisteredUsers() {
		if(users.size()==0) {
			System.out.println("No Users Registgered");
			return;
		}
		System.out.println("UID\t\tNAME\t\tEMAIL\t\t\tMOBILE NO.");
		users.forEach((key, value)->{
			System.out.println(value.getUid()+"\t\t"+value.getName()+"\t\t"+value.getEmail()+"\t\t"+value.getMob_no());
		});
	}
	
	// Add new or update "uid_borrower uid_lender":amount entry in expenses
	private void registerExpense(String uid_borrow, String uid_lend, float amt) {
		// If uid_lend has previous entry of owing money to uid_borrow
		String key=uid_lend+" "+uid_borrow;
		if(expenses.containsKey(key)) {
			expenses.put(key, expenses.get(key)-amt);
			if(expenses.get(key)==0)
				expenses.remove(key);
		}
		else {
			// If uid_borrow has previous entry of owing money to uid_lend
			key=uid_borrow+" "+uid_lend;
			if(expenses.containsKey(key))
				expenses.put(key, expenses.get(key)+amt);
			else // Create entry 
				expenses.put(key, amt);
			if(expenses.get(key)==0) // If amount==0 delete expense entry from the map
				expenses.remove(key);
		}
	}
	
	// Display all expense entries
	private void showAllBalances() {
		if(expenses.size()==0) {
			System.out.println("No balances");
			return;
		}
		expenses.forEach((key, value) -> {
			String usrs[] = key.split(" ");
			User user1 = users.get(usrs[0]), user2=users.get(usrs[1]);
			float val=(float)(Math.round(Math.abs(value) * Math.pow(10, 2)) / Math.pow(10, 2));
			if(value>0) 
				System.out.println(user1.getName()+" owes "+user2.getName()+": "+val);
			else
				System.out.println(user2.getName()+" owes "+user1.getName()+": "+val);
		});
	}
	
	// Display expense entries for specific user
	private void showBalanceForUser(String uid) {
		if(!users.containsKey(uid)) {
			System.out.println("ERROR: Unknown User !");
			return;
		}
		int flag[]= {0};
		expenses.forEach((key, value) -> {
			if(!key.contains(uid))
				return;
			flag[0]++;
			String usrs[] = key.split(" ");
			User user1 = users.get(usrs[0]), user2=users.get(usrs[1]);
			if(value>0) 
				System.out.println(user1.getName()+" owes "+user2.getName()+": "+value);
			else
				System.out.println(user2.getName()+" owes "+user1.getName()+": "+Math.abs(value));
		});
		if(flag[0]==0)
			System.out.println("No balances");
	}
	
	// Handles EXACT distribution flow 
	private String exactDistribute(String expense[], String uid, float amt, int share_count, int indx) {
		float sum=(float) 0.0;
		for(int i=indx+1;i<=indx+share_count;i++)
			sum+=Float.parseFloat(expense[i]);
		if(sum!=amt)
			return "ERROR: Expenses do not Add Up !";
		for(int i=1,uindx=3,pindx=indx;i<=share_count;i++)
			if(!expense[uindx+i].equals(uid))
				registerExpense(expense[uindx+i],uid,Float.parseFloat(expense[pindx+i]));
		return "UPDATED";
		
	}
	
	// Handles EQUAL distribution flow
	private String equalDistribute(String expense[], String uid, float amt, int share_count, int indx) {
		float val=amt/share_count;
		for(int uindx=4;uindx<4+share_count;uindx++)
			if(!expense[uindx].equals(uid))
				registerExpense(expense[uindx],uid,val);
		return "UPDATED"; 
	}
	
	// Handles PERCENT distribution flow
	private String percentDistribute(String expense[], String uid, float amt, int share_count, int indx) {
		float sum=(float) 0.0;
		for(int i=indx+1;i<=indx+share_count;i++)
			sum+=Float.parseFloat(expense[i]);
		if(sum!=100.0)
			return "ERROR: Expenses do not Add Up !";
		for(int i=1,uindx=3,pindx=indx;i<=share_count;i++) {
			float val=(float) (amt*1.0*Float.parseFloat(expense[pindx+i])/100);
			if(!expense[uindx+i].equals(uid))
				registerExpense(expense[uindx+i],uid,val);
		}
		return "UPDATED";
		
	}
	
	// Handles SHARE distribution flow
	private String shareDistribute(String expense[], String uid, float amt, int share_count, int indx) {
		float sum=(float) 0.0;
		for(int i=indx+1;i<=indx+share_count;i++)
			sum+=Float.parseFloat(expense[i]);
		for(int i=1,uindx=3,pindx=indx;i<=share_count;i++) {
			float val=(float) (amt*1.0*Float.parseFloat(expense[pindx+i])/sum);
			if(!expense[uindx+i].equals(uid))
				registerExpense(expense[uindx+i],uid,val);
		}
		return "UPDATED";

	}
	
	// Driver for the EXPENSE flow, also performs data validation
	private String computeExpenses(String expense[]) {
		try {
			String uid_lend=expense[1];
			if(!users.containsKey(uid_lend))
				return "ERROR: Unknown UID- "+uid_lend+" !";
			float amt = Float.parseFloat(expense[2]);
			int share_count=Integer.parseInt(expense[3]);
			int indx=4+share_count;
			for(int uindx=4;uindx<4+share_count;uindx++)
				if(!users.containsKey(expense[uindx]))
					return "ERROR: Unknown UID- "+expense[uindx]+" !";
			if(expense[indx].equalsIgnoreCase("EQUAL")) {
				return equalDistribute(expense, uid_lend, amt, share_count, indx);
			}
			else if(expense[indx].equalsIgnoreCase("EXACT")) {
				return exactDistribute(expense, uid_lend, amt, share_count, indx);
			}
			else if(expense[indx].equalsIgnoreCase("PERCENT")) {
				return percentDistribute(expense, uid_lend, amt, share_count, indx);
			}
			else if(expense[indx].equalsIgnoreCase("SHARE")) {
				return shareDistribute(expense, uid_lend, amt, share_count, indx);
			}
			else
				return "ERROR: Unknown Expense Code !";
		}
		catch(ArrayIndexOutOfBoundsException e) {
			return "ERROR: Malformed Input !";
		}
			
	}
	
	// Handler to parse the requests and call appropriate functions  
	public void handleRequest(String request) {
		
/*		--DEBUGGING PURPOSE--
 * 
 * 		System.out.println("HANDLING REQUEST FOR : "+request);
 * 		System.out.println(expenses);
 */
		String req[]=request.split(" "),resp="";
		if(req[0].equalsIgnoreCase("EXPENSE")) {
			if(!(resp=computeExpenses(req)).equals("UPDATED"))
				System.out.println(resp);
		}
		else if(req[0].equalsIgnoreCase("SHOW")) {
			if(req.length==1)
				showAllBalances();
			else if(req.length==2)
				showBalanceForUser(req[1]);
			else
				System.out.println("ERROR: Malformed Input !");
		}
		else
			System.out.println("ERROR: Malformed Input !");
	}

}
