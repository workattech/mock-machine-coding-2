import java.util.List;

public abstract class Command {
	protected double amount;
	protected List<Double> ratios;
	
	protected Command(double amount) {
		this.amount = amount;
	}
	
	
	abstract void process(BalanceSheet sheet,String lender,List<String> borrower);
	
}
