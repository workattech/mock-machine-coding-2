import java.io.File;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(new File("/home/jigar/Desktop/mock-machine-coding-2/src/main/java/in.txt"));

		BalanceSheet balanceSheet = new BalanceSheet();

		Splitwise splitwise = new Splitwise(balanceSheet);

		while (sc.hasNextLine()) {
			String inputLine = sc.nextLine();

			splitwise.process(inputLine).forEach(System.out::println);

		}

		sc.close();
	}
}