package Demo;

import java.util.Arrays;
import java.util.List;

import Manager.TransactionManager;
import Manager.UserManager;
import Model.SplitType;

public class SplitDemo {

	public static void main(String[] args) {
		UserManager userManager = new UserManager();
		userManager.loadUsers();
		TransactionManager transManager = new TransactionManager(userManager);
		List<String> payee = Arrays.asList("Shika", "Anant", "Deepak");
		transManager.splitBill("Deepak", 1500.0, payee, SplitType.EQUAL);
		transManager.printAll();
		payee = Arrays.asList("Deepak", "Anant");
		transManager.splitBill("Shika", 1000.0, payee, SplitType.EQUAL);
		transManager.printAll();
	}

}
