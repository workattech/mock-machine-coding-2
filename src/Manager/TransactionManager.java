package Manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.SplitType;

public class TransactionManager {
	private HashMap<String, HashMap<String, Double>> transaction;
	
	public TransactionManager(UserManager userManager) {
		super();
		transaction = new HashMap<>();
		ArrayList<String> list = userManager.getAllUsers();
		for(String user: list) {
			HashMap<String, Double> map = new HashMap<>();
			transaction.put(user, map);
		}
	}
	public void splitBill(String payerId, Double amount, List<String> payeeId, SplitType type) {
		assert(payeeId.size() > 0);
		Double splitAmount = amount/(payeeId.size());
		splitAmount = Math.round(splitAmount*100)/100.0;
		HashMap<String, Double> map = transaction.get(payerId);
		for(String payee: payeeId) {
			if(payee.equalsIgnoreCase(payerId)) {
				continue;
			}
			HashMap<String, Double> payeeMap = transaction.get(payee);
			if(payeeMap.containsKey(payerId)) {
				payeeMap.put(payerId, payeeMap.get(payerId)-splitAmount);
			} else {
				payeeMap.put(payerId, -splitAmount);
			}
			if(map.containsKey(payee)) {
				map.put(payee, map.get(payee)+splitAmount);
			} else {
				map.put(payee, splitAmount);
			}
		}
	}
	public void splitBill(String payer, Double amount, List<String> payeeId, SplitType type, 
			List<Double> values) {
		if(type == SplitType.EXACT) {
			double sum = 0;
			for(Double value: values) {
				sum += value;
			}
			if(amount != sum || payeeId.size() != values.size()) {
				System.out.println("Values don't sum up to total.");
				return;
			}
			HashMap<String, Double> map = transaction.get(payer);
			for(int i = 0; i < payeeId.size(); i++) {
				String payee = payeeId.get(i);
				Double amt = values.get(i);
				if(payee.equalsIgnoreCase(payer)) {
					continue;
				}
				HashMap<String, Double> payeeMap = transaction.get(payee);
				if(payeeMap.containsKey(payer)) {
					payeeMap.put(payer, payeeMap.get(payer)-amt);
				} else {
					payeeMap.put(payer, -amt);
				}
				if(map.containsKey(payee)) {
					map.put(payee, map.get(payee)+amt);
				} else {
					map.put(payee, amt);
				}
			}
		} else if(type == SplitType.PERCENTAGE) {
			Double total = 0.0, amt = 0.0;
			List<Double> owed = new ArrayList<>();
			for(Double value: values) {
				total += value;
				amt = (amount*value)/100;
				amt = Math.round(amt*100)/100.0;
				owed.add(amt);
			}
			if(total != 100 || payeeId.size() != values.size()) {
				System.out.println("Total doesn't add up.");
			}
			splitBill(payer, amount, payeeId, SplitType.EXACT, owed);
		} else {
			splitBill(payer, amount, payeeId, SplitType.EQUAL);
		}
	}
	
	public void printAll() {
		for(Map.Entry<String, HashMap<String, Double>> entrySet: transaction.entrySet()) {
			String user = entrySet.getKey();
			HashMap<String, Double> payer = entrySet.getValue();
			System.out.println("Details of User: "+user);
			for(Map.Entry<String, Double> entry: payer.entrySet()) {
				Double amt = entry.getValue();
				if(amt < 0) {
					System.out.println(user+" owes "+-amt+" to "+entry.getKey());
				} else if(amt > 0) {
					System.out.println(user+" is owed "+amt+" by "+entry.getKey());
				} else {
					System.out.println("All settled between "+user+" and "+entry.getKey());
				}
			}
			System.out.println();
		}
		System.out.println("\n\n");
	}
}
