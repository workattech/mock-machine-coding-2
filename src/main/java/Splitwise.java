import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Splitwise {

	private BalanceSheet balanceSheet;

	public Splitwise(BalanceSheet sheet) {
		this.balanceSheet = sheet;

	}

	public List<String> process(String inputLine) {
		List<String> logs = new ArrayList<>();

		Scanner sc = new Scanner(inputLine);

		switch (sc.next()) {
		case "SHOW":
			String user = "";

			if (sc.hasNext())
				user = sc.next();

			logs.addAll(balanceSheet.showExpense(user));

			break;

		case "EXPENSE":
			String lender = sc.next();
			double amount = sc.nextDouble();

			int numberOfusers = sc.nextInt();
			List<String> borrowers = new ArrayList<>();

			for (int i = 0; i < numberOfusers; i++) {
				String borrower = sc.next();
				borrowers.add(borrower);
			}
			int lenderIdx = -1;

			for (int i = 0; i < numberOfusers; i++) {
				if (borrowers.get(i).equals(lender)) {
					lenderIdx = i;
					break;
				}
			}
			if (lenderIdx > -1)
				borrowers.remove(lenderIdx);

			String typeOfSharing = sc.next();
			Command command = null;

			switch (typeOfSharing) {

			case "EXACT":
				List<Double> shares = new ArrayList<>();
				for (int i = 0; i < numberOfusers; i++)
					shares.add(sc.nextDouble());

				double totalSum = shares.stream().reduce(0.0, (sum, amt) -> sum += amt).doubleValue();

				if (totalSum != amount) {
					logs.add(inputLine);
					logs.add("total isn't matching with amount");

				} else
					command = new ExactCommand(amount,shares);

				break;
			case "EQUAL":
				command = new EqualCommand(amount,numberOfusers);
				break;
			case "PERCENT":
				List<Double> percents = new ArrayList<>();
				for (int i = 0; i < numberOfusers; i++) {
					percents.add(sc.nextDouble());
				}

				double totalPercent = percents.stream().reduce(0.0, (sum, amt) -> sum += amt).doubleValue();

				percents.remove(lenderIdx);

				if (Double.compare(totalPercent, 100.0) != 0) {
					logs.add(inputLine);
					logs.add("total percent isn't 100%");
				} else
					command = new PercentCommand(amount,percents);

				break;

			}
			if (command != null)
				command.process(balanceSheet, lender, borrowers);

			break;
		}

		sc.close();
		return logs;
	}

}
