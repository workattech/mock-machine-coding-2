package models;

import java.util.ArrayList;
import java.util.List;

public class Users {
    List<User> users;

    public Users() {
        this.users = new ArrayList<>();
    }

    public User getUser(String name) {
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null;
    }
    public void addUser(User user) {
        users.add(user);
    }
}
