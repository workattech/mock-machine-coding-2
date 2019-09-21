package com.workattech.sw.models;

import java.util.UUID;

public class User {
	private String userId;
	private String userName;
	private String email;
	private String mobileNumber;
	public User(String userName,String email,String mobileNumber)
	{
		this.userId=UUID.randomUUID().toString();
		this.userName=userName;
		this.email=email;
		this.mobileNumber=mobileNumber;
		
	}
	public String getUserName()
	{
		return this.userName;
	}
	public String getUserId()
	{
		return this.userId;
	}
	public String getEmail()
	{
		return this.email;
	}
	public String getMobileNumber()
	{
		return this.mobileNumber;
	}
	
	

}
