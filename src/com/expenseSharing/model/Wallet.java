package com.expenseSharing.model;

import java.util.HashMap;
import java.util.Map;

public class Wallet {
	private final Map<String,Double> ledger;
	
	public Wallet() {
		this.ledger = new HashMap<String, Double>();
	}
	public Map<String, Double> getLedger() {
		return ledger;
	}
	
	public void addOwedTo(String userId, double amount) {
		if(ledger.get(userId) == null)
			ledger.put(userId, 0.0);
		ledger.put(userId, ledger.get(userId)+amount);
	}
	public void addOwedFrom(String userId, double amount) {
		if(ledger.get(userId) == null)
			ledger.put(userId, 0.0);
		ledger.put(userId, ledger.get(userId)-amount);
	}
}
