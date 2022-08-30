package com.workattech.sw.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.workattech.sw.models.Expense;
import com.workattech.sw.models.Money;
import com.workattech.sw.models.User;

public class SplitWiseService {
	ArrayList<User> userList=new ArrayList<User>();
	CalculateExpense calculateExpense=new CalculateExpense();
	public Map<String,ArrayList<Money>> listOfUserWhoOwesToUser=new HashMap<>();
	public Map<String,ArrayList<Money>> getlistOfUserWhoOwesToUser()
	{
		
		return this.listOfUserWhoOwesToUser;
	}
	public void setUserList(ArrayList<User> userList)
	{
		this.userList=userList;
	}
	public void distributeMoneyIfEqual(ArrayList<String> borrowers,String lender,int amount)
	{
		
		double amountToBeDistributed=calculateExpense.CalculateMoneyEqual(amount,borrowers.size()+1);
		for(int i=0;i<borrowers.size();i++) {
		addMoneyToLender(borrowers.get(i),lender,amountToBeDistributed);
		subtractMoneyFromBorrowers(borrowers.get(i),lender,amountToBeDistributed);
		}
	}
	public void distributeMoneyIfExact(ArrayList<String> borrowers,String lender,ArrayList<Integer> exactShare)
	{
		System.out.println("inside exact");
		for(int i=0;i<borrowers.size();i++) {
			addMoneyToLender(borrowers.get(i),lender,exactShare.get(i));
			
			subtractMoneyFromBorrowers(borrowers.get(i),lender,exactShare.get(i));
			}
	}
	public void distributeMoneyIfPercent(ArrayList<String> borrowers,String lender,int amount,ArrayList<Integer> PercentShare)
	{
		for(int i=0;i<borrowers.size();i++) {
			double amountToBeDistributed=calculateExpense.CalculateMoneyPercent(amount,PercentShare.get(i));
			System.out.println("amountToBeDistributed"+amountToBeDistributed);
			addMoneyToLender(borrowers.get(i),lender,amountToBeDistributed);
			
			subtractMoneyFromBorrowers(borrowers.get(i),lender,amountToBeDistributed);
			}
	}
	
	public void addMoneyToLender(String borrower,String lender,double amountToBeDistributed)
	{
		System.out.println("lender"+lender);
		if(listOfUserWhoOwesToUser.containsKey(lender))
		{
			System.out.println("inside addMoneyToLender1 ");
			ArrayList<Money> moneyBorrowers=listOfUserWhoOwesToUser.get(lender);
			boolean balanceAddedToBorrower=false;
			for(int i=0;i<moneyBorrowers.size();i++)
			{
				if(moneyBorrowers.get(i).getPersonB().equals(borrower))
				{
					moneyBorrowers.get(i).addAmountGivenByPersonAToPersonB(amountToBeDistributed);
					listOfUserWhoOwesToUser.put(lender, moneyBorrowers);
					balanceAddedToBorrower=true;
				}
			}
			if(!balanceAddedToBorrower)
			{
				moneyBorrowers.add(new Money(lender,borrower,amountToBeDistributed));
				listOfUserWhoOwesToUser.put(lender,moneyBorrowers);
			}
		}
		else
		{
			System.out.println("inside addMoneyToLender2 ");
			ArrayList<Money> moneyBorrowers=new ArrayList<Money>();
			moneyBorrowers.add(new Money(lender,borrower,amountToBeDistributed));
			listOfUserWhoOwesToUser.put(lender,moneyBorrowers);
		}
	}
	public void subtractMoneyFromBorrowers(String borrower,String lender,double amountToBeDistributed)
	{
		if(listOfUserWhoOwesToUser.containsKey(borrower))
		{
			ArrayList<Money> moneyBorrowers=listOfUserWhoOwesToUser.get(borrower);
			boolean balanceAddedToBorrower=false;
			for(int i=0;i<moneyBorrowers.size();i++)
			{
				if(moneyBorrowers.get(i).getPersonB().equals(lender))
				{
					moneyBorrowers.get(i).substractAmountTakenByPersonAFromPersonB(amountToBeDistributed);
					listOfUserWhoOwesToUser.put(borrower, moneyBorrowers);
					balanceAddedToBorrower=true;
				}
			}
			
		}
		
	}
	
	

}
