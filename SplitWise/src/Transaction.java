import java.util.ArrayList;
import java.util.HashMap;

public class Transaction {
	private HashMap<String, Owe> oweData = new HashMap<String, Owe>();
	protected void expenseEqual (User payed, double amount, int numberOfUsers, ArrayList<User> sharingUser) {
		double dividedAmount = amount/numberOfUsers;
		for (User userOwe : sharingUser) {
			if (userOwe == payed)
				continue;
			updateOwe(payed, userOwe, dividedAmount);
		}
	}
	
	protected void expenseExact (User payed, double amount, int numberOfUsers, ArrayList<User> sharingUser, ArrayList<Integer> sharingUserAmount) {
		int i = 0;
		for (User userOwe : sharingUser) {
			updateOwe(payed, userOwe, sharingUserAmount.get(i));
			i++;
		}
	}
	
	protected void expensePercent (User payed, double amount, int numberOfUsers, ArrayList<User> sharingUser, ArrayList<Integer> sharingUserAmount) {
		int i = 0;
		for (User userOwe : sharingUser) {
			if (userOwe == payed)
				continue;
			double newamount = (amount*sharingUserAmount.get(i))/100;
			updateOwe(payed, userOwe, newamount);
			i++;
		}
	}
	
	private void updateOwe (User payed, User owed, double amount) {
		String key = getKey(payed, owed);
		Owe data = oweData.get(key);
		String [] whooweswho = key.split("-");
		if (Integer.parseInt(whooweswho[0]) == owed.getId())
			amount = amount + data.getamount();
		else
			amount = data.getamount() - amount;
		data.updateAmount(amount);
	}
	
	private String getKey (User id1, User id2) {
		String key = id1.getId() + "-" + id2.getId();
		if (! oweData.containsKey(key)) {
			key = id2.getId() + "-" + id1.getId();
			if (! oweData.containsKey(key))
				oweData.put(key, new Owe(id2, id1, 0));
		}
		
		return key;
	}
	
	public HashMap<String, Owe> getOweData () {
		return oweData;
	}

}
