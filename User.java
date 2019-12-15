/* Machine Coding of SplitWise by TUSHAR ANAND, KIIT DEEMED TO BE UNIVERISTY*/

/* Importing Necessary Packages*/

import java.util.Random;
import java.util.*;
import java.io.*;
import java.lang.*;

/**
User class for users
**/

class User
{
	private String  user_id;
	private String  email_id;
	private String  phone;
	private String  name;
	public static int count =0;
	
	User()
	{
		user_id=null;
		email_id=null;
		phone=null;
		name=null;
	}
	
	/**
	Getter and Setter Methods for accessing the private data Membewr of the class
	**/

	public String getUserId()
	{
		return this.user_id;
	}

	public String getName()
	{
		return this.name;
	}

	public String getEmailId()
	{
		return this.email_id;
	}

	public String getPhone()
	{
		return this.phone;
	}
	
	public void setUserId(String user_id)
	{
		this.user_id=user_id;
	}
	
	public void setName(String name)
	{
		this.name=name;
	}
	
	public void  setEmail(String email_id)
	{
		this.email_id=email_id;
	}
	
	public void setPhone(String phone)
	{
		this.phone=phone;
	}

	/**
	The input() function dynamically fill the details of the user class upto 10 users
	**/
	
	public void input()
	{
		count++;
		Random rand = new Random();
		String id = "u"+count;
		String phone = "9775"+rand.nextInt(10)+"3434"+count;
		String email=id+"@xyz.com";
		String name="ABC"+count;
		this.setName(name);
		this.setUserId(id);
		this.setPhone(phone);
		this.setEmail(email);
	}

	/**
	The output() function prints the details of the user class upto 10 users
	**/

	public void output()
	{
	   System.out.print(this.getUserId()+"  ");	
	   System.out.print(this.getEmailId()+"  ");
	   System.out.print(this.getName()+"  ");
	   System.out.println(this.getPhone());
	}

}
/*----------------------------------------------  THANK YOU FOR USING SPLIT WISE :) -------------------------------------------*/