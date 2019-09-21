package com.workattech.sw.driver;

import java.util.ArrayList;
import java.util.Scanner;

import com.workattech.sw.models.User;
import com.workattech.sw.service.PrintService;
import com.workattech.sw.service.SplitWiseService;

public class DriverClass {
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		User u1=new User("u1","u1.gmail.com","3879872789");
		User u2=new User("u2","u2.gmail.com","3877872789");
		User u3=new User("u3","u3.gmail.com","3879872889");
		User u4=new User("u4","u4.gmail.com","3877872989");
		
		PrintService printService=new PrintService();
		SplitWiseService splitWiseService=new SplitWiseService();
		while(true)
		{
			String input=sc.nextLine();
			String[] inputarray=input.split("\\s+");
			System.out.println("length "+inputarray.length);
			if(inputarray[0].equals("SHOW"))
			{
				if(inputarray.length==1)
				{
					printService.printMoneyOwnedByAll();
				}
				else
				{
					printService.printMoneyOwnedByParticularUser(inputarray[1],splitWiseService);
				}
			}
			else if(inputarray[0].equals("EXPENSE"))
			{
				System.out.println("inside expense");
				String lender=inputarray[1];
				int amount=Integer.parseInt(inputarray[2]);
				int noOfborrowers=Integer.parseInt(inputarray[3]);
				ArrayList<String> borrowerList=new ArrayList<String>();
				int i=4;
				int k=0;
			    while(k<noOfborrowers)
				{
			    	if(!inputarray[i].equals(lender)) {
					borrowerList.add(inputarray[i]);
			    	}
					k++;
					i++;
				}
			     	System.out.println("i "+i);
				String Expensetype=inputarray[i];
				
				if(Expensetype.equals("EQUAL"))
				{
					System.out.println("inside equal");
					splitWiseService.distributeMoneyIfEqual(borrowerList,lender,amount);
				}
				else if(Expensetype.equals("PERCENT"))
				{ 
					ArrayList<Integer> percentList=new ArrayList<>();
					int m=i+2;
					while(m<i+noOfborrowers)
					{
						percentList.add(Integer.parseInt(inputarray[m]));
						m++;
					}
					splitWiseService.distributeMoneyIfPercent(borrowerList, lender, amount, percentList);
				}
				else if(Expensetype.equals("EXACT"))
				{ 
					
					ArrayList<Integer> exactList=new ArrayList<>();
					int m=i+1;
					while(m<=i+noOfborrowers)
					{
						exactList.add(Integer.parseInt(inputarray[m]));
						m++;
					}
					splitWiseService.distributeMoneyIfExact(borrowerList, lender, exactList);
				}
			}
		}
		
		
	}

}
