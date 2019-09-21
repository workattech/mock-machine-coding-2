package com.workattech.sw.models;

public class Money {
       String personA;
       String personB;
       double amount;
       public Money(String moneyGivenBy,String moneyTakenBy,double amount)
       {
    	   this.personA=moneyGivenBy;
    	   this.personB=moneyTakenBy;
    	   this.amount=amount;
       }
       public String getPersonA()
       {
    	   return this.personA;
       }
       public String getPersonB()
       {
    	   return this.personB;
       }
       public double getAmount()
       {
    	   return this.amount;
       }
       public void addAmountGivenByPersonAToPersonB(double amountGivenByPersonA)
       {
    	   this.amount+=amountGivenByPersonA;
       }
       public void substractAmountTakenByPersonAFromPersonB(double amountTakenBypersonA)
       {
    	   this.amount-=amountTakenBypersonA;
    	   if(this.amount<0)
    	   {
    		   this.amount=0;                   //No money is owned by personA from personB
    	   }
       }
       
}
