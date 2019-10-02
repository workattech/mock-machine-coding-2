import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class BalanceSheet {

	private Map<Pair, List<Expense>> sheet;

	public BalanceSheet() {

		sheet = new HashMap<>();
	}

	public void addExpense(String lender, String borrower, Expense expense) {

		Pair pair = new Pair(lender, borrower);

		if (sheet.containsKey(pair))
			sheet.get(pair).add(expense);
		else
			sheet.computeIfAbsent(pair, k -> new ArrayList<>()).add(expense);
	}

	public List<String> showExpense(String user) {

		List<String> logs = new ArrayList<>();

		sheet.entrySet().stream().filter(entry -> {
			if (user.isEmpty())
				return true;
			return entry.getKey().getLender().equals(user) || entry.getKey().getBorrower().equals(user);
		}).forEach(entry -> {

			StringBuilder sb = new StringBuilder();
			String lender = entry.getKey().getLender();
			String borrower = entry.getKey().getBorrower();

			double expense = entry.getValue().stream().flatMap(i -> Stream.of(i.getAmount()))
					.reduce(0.0, (sum, cost) -> sum += cost).doubleValue();
			logs.add(sb.append(borrower + " owes " + lender + ": ").append(expense).toString());

		});

		if (logs.isEmpty())
			logs.add("No balances");

		return logs;
	}

}
