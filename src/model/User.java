package model;

import java.util.HashMap;
import java.util.Map;

public class User {
  private UserInfo userInfo;
  private Map<User, Long> balance;

  private static long userId = 0;

  public User(String name, String email, String mobileNo) {
    balance = new HashMap<>();
    userInfo = new UserInfo(name, email, String.valueOf(++userId), mobileNo);
  }

  public UserInfo getUserInfo() {
    return userInfo;
  }

  public void setUserInfo(UserInfo userInfo) {
    this.userInfo = userInfo;
  }

  public Map<User, Long> getBalance() {
    return balance;
  }

  public String getName() { return userInfo.getName(); }

  public String getId() { return userInfo.getUserId(); }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof User) {
      User otherUser = (User) obj;
      return this.getId().equalsIgnoreCase(otherUser.getId());
    }
    else {
      return false;
    }
  }
}
