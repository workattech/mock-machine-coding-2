package model;

public class UserInfo {
  private String name;
  private String email;
  private String userId;
  private String mobileNo;

  public UserInfo(String name, String email, String userId, String mobileNo) {
    this.name = name;
    this.email = email;
    this.userId = userId;
    this.mobileNo = mobileNo;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getUserId() {
    return userId;
  }

  public String getMobileNo() {
    return mobileNo;
  }
}
