package com.splitwise.machine.coding;

public class User {

	String userId;

	String name;

	String emailId;

	String mobileNumber;

	public User(String userId, String name, String emailId, String mobileNumber) {
		this.userId = userId;
		this.name = name;
		this.emailId = emailId;
		this.mobileNumber = mobileNumber;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

}
