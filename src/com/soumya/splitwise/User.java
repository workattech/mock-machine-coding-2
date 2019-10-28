package com.soumya.splitwise;

// Defines the characteristics of a user
public class User {
	
	private String name,uid,email,mob_no;

	public User(String uid, String name, String email, String mob_no) {
		super();
		this.name = name;
		this.uid = uid;
		this.email = email;
		this.mob_no = mob_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMob_no() {
		return mob_no;
	}

	public void setMob_no(String mob_no) {
		this.mob_no = mob_no;
	}
	
}
