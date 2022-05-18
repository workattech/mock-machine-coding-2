import java.util.ArrayList;
import java.util.HashMap;

public class splitWiseService {
	HashMap<Integer, User> UserDetails = new HashMap<Integer, User>();
	private Transaction doWork;
	splitWiseService (ArrayList<User> users) {
		for (User currentUser : users) {
			int id = currentUser.getId();
			UserDetails.put(id, currentUser);
		}
		doWork = new Transaction();
	}
	
	void show () {
		double total = 0;
		HashMap<String, Owe> oweData = doWork.getOweData();
		for (String key : oweData.keySet()) {
			Owe oweRecord = oweData.get(key);
			String [] users = key.split("-");
			int user1Id = Integer.parseInt(users[0]);
			int user2Id = Integer.parseInt(users[1]);
			User one = UserDetails.get(user1Id);
			User two = UserDetails.get(user2Id);
			double value = oweRecord.getamount();
			if (value == 0)
				continue;
			if (value < 0)
				System.out.println(two.getName() + " owes " + one.getName() + ": " + value*-1);
			else if (value > 0)
				System.out.println(one.getName() + " owes " + two.getName() + ": " + value);
			total = total + value;
		}
		if (total == 0)
			System.out.println("No balances");
	}
	
	void show (User givenUser) {
		double total = 0;
		HashMap<String, Owe> oweData = doWork.getOweData();
		for (String key : oweData.keySet()) {
			Owe oweRecord = oweData.get(key);
			String [] users = key.split("-");
			int user1Id = Integer.parseInt(users[0]);
			int user2Id = Integer.parseInt(users[1]);
			User one = UserDetails.get(user1Id);
			User two = UserDetails.get(user2Id);
			if (one != givenUser && two != givenUser)
				continue;
			double value = oweRecord.getamount();
			if (value == 0)
				continue;
			if (value < 0)
				System.out.println(two.getName() + " owes " + one.getName() + ": " + value*-1);
			else if (value > 0)
				System.out.println(one.getName() + " owes " + two.getName() + ": " + value);
			total = total + value;
		}
		
		if (total == 0)
			System.out.println("No balances");
	}
	
	void expense (User payed, double amount, int numberOfUsers, ArrayList<User> sharingUsers, String type, ArrayList<Integer> division ) {
		if (type.equalsIgnoreCase("equal")) {
			doWork.expenseEqual(payed, amount, numberOfUsers, sharingUsers);
		}
		else if (type.equalsIgnoreCase("exact")) {
			
			doWork.expenseExact(payed, amount, numberOfUsers, sharingUsers, division);
		}
		else if (type.equalsIgnoreCase("percent")) {
			
			doWork.expensePercent(payed, amount, numberOfUsers, sharingUsers, division);
		}
	}
	
	User getUser (int id) {
		return UserDetails.get(id);
	}
	
//	private int printOwedData (User currentUser) {
//		HashMap<User,Owe> IOwe = currentUser.getOweData();
//		int total = 0;
//		for (User owedUser : IOwe.keySet()) {
//			Owe oweDetails = IOwe.get(owedUser);
//			if (oweDetails.getamount() > 0)
//				System.out.println(currentUser.getName() + " owes " + owedUser.getName() + ": " + oweDetails.getamount());
//			total = (int) (total + oweDetails.getamount());
//		}
//		return total;
//	}
//	
//	private int printOwetoMeData (User currentUser) {
//		HashMap<User,Owe> IOwetoMe = currentUser.getOwetoMeData();
//		int total = 0;
//		for (User owedUser : IOwetoMe.keySet()) {
//			Owe owetoMeDetails = IOwetoMe.get(owedUser);
//			if (owetoMeDetails.getamount() > 0) 
//				System.out.println(owedUser.getName() + " owes " + currentUser.getName() + ": " + owetoMeDetails.getamount());
//			total = (int) (total + owetoMeDetails.getamount());
//		}
//		return total;
//	}
	
}