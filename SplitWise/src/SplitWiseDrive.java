import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SplitWiseDrive {
	public static void main (String [] args) {
		ArrayList<User> users = new ArrayList<User>();
		users.add(new User("User1"));
		users.add(new User("User2"));
		users.add(new User("User3"));
		users.add(new User("User4"));
		HashMap<Integer, User> map = new HashMap<Integer, User>();
		for (User usertoid : users)
			map.put(usertoid.getId(), usertoid);
		
		Queue<String> events = new LinkedList<String>();
		Scanner sc = new Scanner(System.in);
		int numberOfTransactions = sc.nextInt();
		while (numberOfTransactions-- > 0) {
			String currentTransaction = sc.next();
			events.add(currentTransaction);
		}
		
		splitWiseService service = new splitWiseService(users);
		while (! events.isEmpty()) {
			String currentEvent = events.remove();
			String [] current = currentEvent.split(",");
			if (current[0].equalsIgnoreCase("show") && current.length == 2) {
				service.show(map.get(Integer.parseInt(current[1])));
				System.out.println();
			}
			else if (current[0].equalsIgnoreCase("show") && current.length == 1) {
				service.show();
				System.out.println();
			}
			else {
				ArrayList<User> al = new ArrayList<User>();
				ArrayList<Integer> division = new ArrayList<Integer>();
				int i = 0;
				for (i = 0 ; i < Integer.parseInt(current[3]) ; i++)
					al.add(map.get(Integer.parseInt(current[i+4])));
				if (i+5 < current.length) {
					for (int j = i+5 ; j < current.length ; j++)
						division.add(Integer.parseInt(current[j]));
				}
				service.expense(map.get(Integer.parseInt(current[1])), Double.parseDouble(current[2]), Integer.parseInt(current[3]), al, current[i+4], division);
				System.out.println();
			}	
		}
		
		
		
		
		
	} 
}
