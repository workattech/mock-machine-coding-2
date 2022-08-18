package Manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import Model.User;

public class UserManager {
	private HashMap<String, User> userMap;
	
	public UserManager() {
		super();
		userMap = new HashMap<>();
	}

	public void loadUsers() {
		List<User> users = Arrays.asList(
			new User("Shika", "Shika", "Shika@gmail", "76543"),
			new User("Deepak", "Deepak", "Deepak@gmail", "76543"),
			new User("Anant", "Anant", "Anant@gmail", "76543"),
			new User("Nikhil", "Nikhil", "Nikhil@gmail", "76543"),
			new User("Pankaj", "Pankaj", "Pankaj@gmail", "76543")
		);
		for(User user: users) {
			userMap.put(user.getUserName(), user);
		}
	}

	public ArrayList<String> getAllUsers() {
		ArrayList<String> users = new ArrayList<>();
		for(String user: userMap.keySet()) {
			users.add(user);
		}
		return users;
	}
}
