
public class Owe {
	private User iOwe;
	private User to;
	private double amount;
	Owe (User iowe, User to, double amount) {
		this.iOwe = iowe;
		this.to = to;
		this.amount = amount;
	}
	
	void getDetails () {
		System.out.println(iOwe + " owes " + to + " : " + amount);
	}
	
	double getamount () {
		return amount;
	}
	
	void updateAmount (double amount) {
		this.amount = amount;
	}
}
