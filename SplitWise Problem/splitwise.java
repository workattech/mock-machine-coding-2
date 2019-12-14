import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;




public class Application {
    //database that keeps track of user on the basis of their userId
	static HashMap<String,User> database = new HashMap<>();
	public static void main(String[] args) throws IOException {BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
		
		User u1 = new User("u1", "User1", "ramsingh@gmail.com", "8753493733");
		u1.addToDatabase(database);
		User u2 = new User("u2", "User2", "ankitsingh@gmail.com", "8676493733");
		u2.addToDatabase(database);
		User u3 = new User("u3", "User3", "premsingh@gmail.com", "875343433");
		u3.addToDatabase(database);
		User u4 = new User("u4", "User4", "shyamsingh@gmail.com", "87534456733");
        u4.addToDatabase(database);
        
        //variable that keeps track atleast one sharing took place.
		boolean sharingStarted = false;
		while(true) {
		String[] s = br1.readLine().split(" ");
		if(s[0].equalsIgnoreCase("SHOW")) {
			if(!sharingStarted) {
				System.out.println("No Balance");
			}else {
				if(s.length==1) {
					Iterator hmIterator = database.entrySet().iterator();
					while(hmIterator.hasNext()) {
						Map.Entry mapElement = (Map.Entry)hmIterator.next();
						User u = (User)mapElement.getValue();
						u.showDebter(database);
					}
				}else if(s.length==2 && database.containsKey(s[1])) {
					User u = (User)database.get(s[1]);
					u.showDebter(database);
					u.showBorrower(database);
				}else {
					System.out.println("Please Enter correct format.");
				}
			}
			
		}else if(s[0].equalsIgnoreCase("EXPENSE")) {
			sharingStarted = true;
			User u = (User)database.get(s[1]);
			double amount = Double.parseDouble(s[2]);
			int number =Integer.parseInt(s[3]);
			String type = s[3+number+1];
			String[] debtUsers = new String[number];
			for(int i=0;i<number;i++) {
				debtUsers[i] =s[4+i];
			}
			divideMoney(s, debtUsers, type, amount, u, number);
		}else {
			System.out.println("Error!, Please Enter the data in Correct format");
		}
	}
		
    }
    
    //helper function that split money on the basis of type
	public static void divideMoney(String[] s,String[] debtUsers,String type,Double amount,User u,int number) {
		if(type.equals("EQUAL")) {
			double a  = (amount*1.0)/number;
			for(int i=0;i<number;i++) {
					u.addDebt(debtUsers[i], a);
					User w = (User)database.get(debtUsers[i]);
					w.addBorrow(u.getUserId(), a);
			}
		}else if (type.equals("EXACT")) {
			int x = 3+number+2;
			double sum = 0;
			for(int i=x;i<s.length;i++) {
				double y = Double.parseDouble(s[i]);
				sum+=y;
			}
			if(amount==sum) {
				for(int i=0;i<number;i++) {
					double a  = Double.parseDouble(s[x+i]);
					u.addDebt(debtUsers[i], a);
					User w = (User)database.get(debtUsers[i]);
					w.addBorrow(u.getUserId(), a);
				}
			}else {
				System.out.println("Error!,Sum of all distribution is not equal to Amount");
			}
		}else if(type.equalsIgnoreCase("PERCENT")) {
			double sum = 0;
			for(int i=0;i<number;i++) {
				double x = Double.parseDouble(s[3+number+2+i]);
				sum+=x;
			}
			if(sum==100) {
				for(int i=0;i<number;i++) {
					double x = Double.parseDouble(s[3+number+2+i]);
					double a = amount*(x/100);
					u.addDebt(debtUsers[i], a);
					User w = (User)database.get(debtUsers[i]);
					w.addBorrow(u.getUserId(), a);
				}
			}else {
				System.out.println("Error!,Sum of all percentage is not equal to 100");
			}
		}
	}

}


//User Class that has userId,name,email,mobileNo,debt,borrower as its field
class User{
	private String userId;
	private String name;
	private String email;
	private String mobileNo;
	private HashMap<String,Double> debt;
	private HashMap<String, Double> borrow;
	
	public User(String userId,String name,String email,String mobileNo) {
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.mobileNo = mobileNo;
		debt = new HashMap<>();
		borrow = new HashMap<>();
	}
	
	public String getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getMobileNo() {
		return mobileNo;
	}
	
	public HashMap<String,Double > getDebt() {
		
		return debt;
	}
	
	public HashMap<String, Double> getBorrow() {
		return borrow;
    }
    
    //helper function that add all the user who had borrow money
	public void addDebt(String debtUser,double amount) {
			if(!debtUser.equals(this.userId)) {
				if(this.debt.containsKey(debtUser)) {
					double a =  (double)this.debt.get(debtUser);
					a +=amount;
					this.debt.put(debtUser, a);
				}else {
					this.debt.put(debtUser, amount);
				}
			}
        }

    //helper function that add the user whom current user has borrowed the money
	public void addBorrow(String borrower,double amount) {
		if(!borrower.equals(this.userId)) {
			if(this.borrow.containsKey(borrower)) {
				double a = (double)this.borrow.get(borrower);
				a+=amount;
				this.borrow.put(borrower, a);
			}else {
				this.borrow.put(borrower, amount);
			}
		}
    }
    
    //helper function that add the user to database
	public void addToDatabase(HashMap<String, User> database) {
		database.put(this.getUserId(),this);
    }

    //helper function list all user who has taken from him
	public void showDebter(HashMap<String, User> database) {
		Iterator debtIterator = debt.entrySet().iterator();
		while(debtIterator.hasNext()) {
			Map.Entry mapElement = (Map.Entry)debtIterator.next();
			double value = (double)mapElement.getValue();
			String userId = (String)mapElement.getKey();
			String username = (String)database.get(userId).getName();
			if(value>0) {
				System.out.println(String.format("%s owes %s : %.2f",username,this.name,value));
			}
			
		}
    }
    
    //helper function list all user from whom this user has taken the money.
	public void showBorrower(HashMap<String, User> database) {
		Iterator borrowerIterator = borrow.entrySet().iterator();
		while(borrowerIterator.hasNext()) {
			Map.Entry mapElement = (Map.Entry)borrowerIterator.next();
			double value = (double)mapElement.getValue();
			String userId = (String)mapElement.getKey();
			String username = (String)database.get(userId).getName();
			if(value>0) {
				System.out.println(String.format("%s owes %s : %.2f",this.name,username,value));
			}
		}
	}
	
}