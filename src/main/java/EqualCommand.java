import java.util.ArrayList;
import java.util.List;

public class EqualCommand extends Command {

	EqualCommand(double amount, int numOfUsers) {
		super(amount);
		List<Double> ratios = new ArrayList<>();

		for (int i = 1; i <= numOfUsers - 1; i++) {
			double ratio = 1 / (double) numOfUsers;

			ratio = Math.round(ratio * 100.0) / 100.0;

			ratios.add(ratio);

		}

		this.ratios = ratios;
	}

	void process(BalanceSheet sheet, String lender, List<String> borrowers) {

		for (int i = 0; i < ratios.size(); i++)
			sheet.addExpense(lender, borrowers.get(i), new Expense(ratios.get(i) * amount));

	}

}
