import java.util.List;
import java.util.stream.Collectors;

public class PercentCommand extends Command {

	public PercentCommand(double amount, List<Double> percents) {
		super(amount);
		this.ratios = percents.stream().map(i -> i / 100.0).collect(Collectors.toList());
	}

	@Override
	void process(BalanceSheet sheet, String lender, List<String> borrowers) {
		for (int i = 0; i < ratios.size(); i++)
			sheet.addExpense(lender, borrowers.get(i), new Expense(ratios.get(i) * amount));

	}

}
