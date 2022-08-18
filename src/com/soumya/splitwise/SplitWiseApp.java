package com.soumya.splitwise;

// Driver Application
public class SplitWiseApp {

	public static void main(String[] args) {
		// Initializing ExpenseManager Object
		ExpenseManager expman =  new ExpenseManager();
		
		// Hard Coded Users
		expman.registerUser("User1", "user1@hello.com", "+918685483458");
		expman.registerUser("User2", "user2@hello.com", "+918085483643");
		expman.registerUser("User3", "user3@hello.com", "+919056783458");
		expman.registerUser("User4", "user4@hello.com", "+919885434258");
		
		// Display Registered Users with UID
		expman.displayRegisteredUsers();
		
		// TEST - Taken from SAMPLE INPUT Section
		expman.handleRequest("SHOW");
		expman.handleRequest("SHOW u1");
		// EQUAL
		expman.handleRequest("EXPENSE u1 1000 4 u1 u2 u3 u4 EQUAL");
		expman.handleRequest("SHOW u4");
		expman.handleRequest("SHOW u1");
		// EXACT
		expman.handleRequest("EXPENSE u1 1250 2 u2 u3 EXACT 370 880");
		expman.handleRequest("SHOW");
		// PERCENT
		expman.handleRequest("EXPENSE u4 1200 4 u1 u2 u3 u4 PERCENT 40 20 20 20");
		expman.handleRequest("SHOW u1");
		expman.handleRequest("SHOW");
		// SHARE
		expman.handleRequest("EXPENSE u4 1200 4 u1 u2 u3 u4 SHARE 2 1 1 1");
		expman.handleRequest("SHOW");
	}

}
