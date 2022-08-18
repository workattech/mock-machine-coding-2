import java.util.List;

public class ExactCommand extends Command{
	
	public ExactCommand(double amount,List<Double> shares) {
		super(amount);
		this.ratios = shares;
	}



	@Override
	void process(BalanceSheet sheet, String lender, List<String> borrowers) {
		
		for (int i = 0; i < ratios.size(); i++)
			sheet.addExpense(lender, borrowers.get(i), new Expense(ratios.get(i)));

	}

}
