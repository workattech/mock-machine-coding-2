package model;

import java.awt.*;

public class ExpenseInfo {

  private User user1;
  private User user2;
  private String name;
  private Image image;
  private String notes;

  public ExpenseInfo(User user1, User user2) {
    this.user1 = user1;
    this.user2 = user2;
  }

  public User getUser1() {
    return user1;
  }

  public User getUser2() {
    return user2;
  }

  public Image getImage() {
    return image;
  }

  public String getNotes() {
    return notes;
  }
}
