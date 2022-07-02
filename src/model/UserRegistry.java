package model;

import java.util.HashSet;
import java.util.Set;

public class UserRegistry {

  private static Set<User> userRegistry = new HashSet<>();

  public static void addUser(User user) {
    userRegistry.add(user);
  }

  public static Set<User> getUserRegistry() { return userRegistry; }

  public static User fromName(String userName) {
    User user = null;
    for(User u: userRegistry) {
      if(u.getName().equals(userName)) {
        user = u;
        break;
      }
    }
    return user;
  }
}
