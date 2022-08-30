package com.workattech.sw.service;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import com.workattech.sw.models.Money;
import com.workattech.sw.models.User;

public class PrintService {
	
	Map<String,ArrayList<Money>> listOfUserWhoOwesToUser;
	public void printMoneyOwnedByParticularUser(String user,SplitWiseService splitWiseService)
	{
		listOfUserWhoOwesToUser=splitWiseService.getlistOfUserWhoOwesToUser();
	     System.out.println("inside print user");
	    boolean borrowerListPrinted=false;
	    System.out.println("lender"+user);
	    for(Entry<String, ArrayList<Money>> userList:listOfUserWhoOwesToUser.entrySet()) {
	    	System.out.println("user"+userList.getKey());
	    }
		if(listOfUserWhoOwesToUser.containsKey(user)) {
		ArrayList<Money> borrowerList=listOfUserWhoOwesToUser.get(user);
		System.out.println("borrowerList "+borrowerList);
		for(int i=0;i<borrowerList.size();i++)
		{
			if(borrowerList.get(i).getAmount()!=0)
			{
				System.out.println(borrowerList.get(i).getPersonB()+" ownes "+user+" "+borrowerList.get(i).getAmount());
				borrowerListPrinted=true;
			}
			
			
		}
		
		}
		if(!borrowerListPrinted)
		{
			System.out.println("No expenses");
		}
	}
	public void printMoneyOwnedByAll()
	{
	
	    boolean borrowerListPrinted=false;
	    for(Entry<String, ArrayList<Money>> userList:listOfUserWhoOwesToUser.entrySet()) {
	    	String user=userList.getKey();
		ArrayList<Money> borrowerList=userList.getValue();
		for(int i=0;i<borrowerList.size();i++)
		{
			if(borrowerList.get(i).getAmount()!=0)
			{
				System.out.println(borrowerList.get(i).getPersonB()+" ownes "+user+" "+borrowerList.get(i).getAmount());
				borrowerListPrinted=true;
			}
			
			
		}
		
		}
		if(!borrowerListPrinted)
		{
			System.out.println("No expenses");
		}
	}

}
