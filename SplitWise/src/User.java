import java.util.HashMap;

public class User {
	private String name;
	private int id;
	private static int counter = 1;
//	private HashMap<User,Owe> IOwe = new HashMap<User,Owe>();
//	private HashMap<User,Owe> OwetoMe = new HashMap<User,Owe>();
	
	User (String name) {
		this.name = name;
		this.id = counter;
		counter = counter + 1;;
	}
	
	int getId () {
		return id;
	}
	
	String getName () {
		return name;
	}
	
//	HashMap<User,Owe> getOweData () {
//		return IOwe;
//	}
//	
//	HashMap<User,Owe> getOwetoMeData () {
//		return OwetoMe;
//	}
}
