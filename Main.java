/* Machine Coding of SplitWise by TUSHAR ANAND, KIIT DEEMED TO BE UNIVERISTY*/


/* Importing Necessary Packages*/

import java.util.Random;
import java.util.*;
import java.io.*;
import java.lang.*;
public class Main
{
	/** 
	In this project we are maintaining a two dimensional matrix to keep the record of transaction between the users
	The matrix is named as 'Matrix' and we will call it as Transaction Matrix.
	**/

	
	/*
	printTable() method print the Matrix of Transction done between the users.
	It keep records from the begining meaning if there is no borrow between the users still we maintain the amount.
	**/
	
	public static void printTable(int Matrix[][])
	{
		System.out.println();
		for(int i=1;i<Matrix.length;i++)
		{
			for(int j=1;j<Matrix[i].length;j++)
			{
				System.out.print((Matrix[i][j]+0.00)+" ");
			}
			System.out.println();

		}
	}


	/**
	updateTransctionTableEqual() method updates the Transaction Matrix on 'EQUAL' option 
	**/

	public static void updateTransctionTableEqual(int Matrix[][],String  payer, String whom[] , int amount)
	{
		int split_wise = (amount)/whom.length;
		// 33.34 CASE NOT HANDLED
		int indexPayer=Integer.parseInt(payer.substring(1)); // Parsing to integer because to get the ID of payer
		
		/**
		Since all the ID's are in string therefor parsing in integer is necessary
		to find their ID which will be there index to Transaction Matrix
		**/

		int indexWhom[]=new int[whom.length]; 

		for(int i=0;i<whom.length;i++)
		{
			indexWhom[i]=Integer.parseInt(whom[i].substring(1));
		}

		for(int i=0;i<whom.length;i++)
		{
			if(indexPayer!=indexWhom[i])                     /* This conditon is to avoid payer from paying for himself */
			{
				Matrix[indexPayer][indexWhom[i]]+=split_wise;
			} 
		}
		
	}


	
	/**
	updateTransctionTableExact() method updates the Transaction Matrix on 'EXACT' option
	**/

	public static void updateTransctionTableExact(int Matrix[][] , String payer , String  whom[] , int amount , int shareExact[])
	{
		int check_sum =0;                        // This variable is used to check All Exact values sum should be equal to amount
		
		int  indexPayer=Integer.parseInt(payer.substring(1));  // Getting Payer's ID 
		
		int indexWhom[]=new int[whom.length];    // Getting all users ID in int for indexing by parsing from String array of ID

		for(int i=0;i<whom.length;i++)
		{
			indexWhom[i]=Integer.parseInt(whom[i].substring(1));
		}

		/* checksum calculation*/

		for(int i=0;i<whom.length;i++)
		{
			check_sum+=shareExact[i];
		}

		/* Here the further transaction will only proceed when the checksum is equal to sum of all EXACT values */

		if(check_sum==amount)
		{
			for(int i=0;i<whom.length;i++)
			{
				if(indexPayer!=indexWhom[i])
				Matrix[indexPayer][indexWhom[i]]+=shareExact[i];
			}
		}
		else
		{
			System.out.println("INVALID DISTRIBTUION OF AMOUNT");
		}
		/* printTable(Matrix); FOR DEBUGGING */
	}

	

	/**
	updateTransctionTableExact() method updates the Transaction Matrix on 'SHARE' option
	**/
	
	public static void updateTransctionTablePercent(int Matrix[][] , String payer , String whom[] , int amount , int share_Percent[])
	{
		int check_sum=0;                            /*This variable is used to check All share values sum should be equal to 100*/

		
		int indexPayer=Integer.parseInt(payer.substring(1));    /* Getting Payer's ID */
		
		int indexWhom[]=new int[whom.length];    /* Getting all users ID in int for indexing by parsing from String array of ID */

		for(int i=0;i<whom.length;i++)
		{
			indexWhom[i]=Integer.parseInt(whom[i].substring(1));
		}


		/* checksum calculation */

		for(int i=0;i<share_Percent.length;i++)
		{
			check_sum+=share_Percent[i];
		}

		/* Here the further transaction will only proceed when the checksum is equal to 100  */

		if(check_sum==100)
		{
			for(int i=0;i<whom.length;i++)
			{
				int part_wise =0;                         /* Variable for calculating share in amount for each user */

				part_wise = (share_Percent[i]*amount)/100;

				if(indexPayer!=indexWhom[i])              /* This conditon is to avoid payer from paying for himself */
				{
					Matrix[indexPayer][indexWhom[i]]+=part_wise;
				}
			}
		}
		else
		{
			System.out.println("INVALID DISTRIBTUION OF SHARES");
		}
		
	}
	


	/**
	showCompleteTransactionTable() method is used when the 'SHOW' command is given 
	It shows the complete description of all user and the net balance between them if exists 
	**/   	

	public static  void showCompleteTransactionTable(int [][] Matrix )
	{
		int net_amount=0;							/* Variable for calculating the net amount between the users */														
		
		boolean empty=false;                        /* Variable for checking if there is atleast one transaction otherwise
													   'No Balances' will be displayed
													*/
		
		for(int i=1;i<Matrix.length;i++)
		{
			for(int j=i;j<Matrix[i].length;j++)
			{
				/* To avoid payer paying from himself below conditon is implemented */

				if(i!=j)
				{
					if(Matrix[i][j]>Matrix[j][i])
					{
						System.out.println("User"+j+" owes "+"User"+i+": "+(Matrix[i][j]-Matrix[j][i]));
						empty=true;
					}
					else if(Matrix[i][j]<Matrix[j][i])
					{
						System.out.println("User"+i+" owes "+"User"+j+": "+(Matrix[j][i]-Matrix[i][j]));
						empty=true;
					}
				}

			}
		}

		/* If no transaction is found */

		if(empty==false)
		{
			System.out.println("No Balances");
		}
	}



	/**
	showUserTransactionTable() method is used when the 'SHOW < user_name >' command is given 
	It shows the complete description of particular user. 
	**/

	public static void showUserTransactionTable(int [][] Matrix , String userId )
	{
		int indexUser = Integer.parseInt(userId.substring(1));    /* Getting Payer's ID */
		
		int net_amount=0;
		boolean empty = false;			/* Variable for checking if there is atleast one transaction otherwise
										'No Balances' will be displayed
										*/

		for(int i=1;i<Matrix.length;i++)
		{
			/* To avoid user checking balance from himself below condition is implemented */

			if(indexUser!=i)
			{
				if(Matrix[indexUser][i]>Matrix[i][indexUser])
				{
					System.out.println("User"+i+" owes "+"User"+indexUser+": "+(Matrix[indexUser][i]-Matrix[i][indexUser]));
					empty=true;
				}
				else if(Matrix[indexUser][i]<Matrix[i][indexUser])
				{
					System.out.println("User"+indexUser+" owes "+"User"+i+": "+(Matrix[i][indexUser]-Matrix[indexUser][i]));
					empty=true;
				}
			}
		}
		if(empty==false)
		{
			System.out.println("No Balances");
		} 
	}





	/*--------------------------------------------------    MAIN   METHIOD   ---------------------------------------------------*/ 




	public static void main(String args[])
	{
		final int MAX=5;                                  /* Maximum Number of User is set to 5 for Demo Purpose */
		 
		 
		User user_array[]=new User[MAX];                  /* Creating the reference variables of the User Class */
	     
	    int Matrix[][] = new int[MAX][MAX];         /* Declaration of Transaction Matrix */



	    /* Initializing the User reference variables */

	    for(int i=0; i<MAX ;i++)                            
		{
		 	user_array[i]=new User();                      
			user_array[i].input();
		}

		String str="";                                      /* Query string */

		Scanner sc=new Scanner(System.in);                  /* Scanner Class object for input query from keyboard */
		
		int operations=0;                                   /* Total number of operations. */

		System.out.println("Enter No. of operations :");

		operations=sc.nextInt();                            /* Input from keyboard number of operations */
		
		sc.nextLine();                                      /* Flushing the buffer for string query input */
		
		for(int t=0;t<operations;t++)                       /*Loop for input and processing of query */
		{
			str=sc.nextLine();
			
			String words[]=str.split(" ");                  /* Spliting the whole string to get required parameters */ 
			
			int no_args=words.length;
			
			if(no_args==1)                                  /* OPERATION : SHOW */
			{
				showCompleteTransactionTable(Matrix);
			}

			else if(no_args==2)                             /* OPERATION : SHOW < user_name > */
			{
				String userId = words[1];
				showUserTransactionTable(Matrix,userId);
			}
			
			else    
			{
			        /**
			        	OTHER OPERATIONS LIKE:
			         1) EXPENSE u1 1000 4 u1 u2 u3 u4 EQUAL
	                 2) EXPENSE u4 1200 4 u1 u2 u3 u4 PERCENT 40 20 20 20
	                 3) EXPENSE u1 1250 2 u2 u3 EXACT 370 880
	                 
	                 **/
				
				int shareExact[];                          		/* Array to collect 'EXACT' */

				int sharePercent[];                             /* Array to collect 'EXACT' */ 

				String payer = words[1];                        /*  user who pays the amount */

				int amount = Integer.parseInt(words[2]);        /* Amount of money paid */

				int noUser=Integer.parseInt(words[3]);          /* Number of user involved in the transction */      
				
				String userArr[] = new String[noUser];          /* Array of users  involved Transction */
				
				
				int i=4;                                        /* Because we will get users from 4th index */               
				
				for(i=4 ; i<4+noUser ;i++)                               
				{
					userArr[i-4]=words[i];						/* To put the elements in userArr from 0th index */
				}
				
				String type = words[i++];                       /* Options Like : 'EQUAL' 'SHARE' 'EXACT' */
				

				if(type.equals("EQUAL"))                       
				{
					updateTransctionTableEqual(Matrix,payer, userArr,amount);

				}

				else if(type.equals("PERCENT"))                
				{
				    sharePercent=new int[noUser];
				    
				    for(int k=0;k<noUser;k++)
				    {
				    	sharePercent[k] = Integer.parseInt(words[i++]);

				    }
				    
				    updateTransctionTablePercent(Matrix,payer, userArr,amount ,sharePercent);
			    }
			    
			    else if(type.equals("EXACT"))                 
				{
					shareExact=new int[noUser];
					
					for(int k=0;k<noUser;k++)
				    {
				    	shareExact[k] = Integer.parseInt(words[i++]);

				    }
				    updateTransctionTableExact(Matrix,payer,userArr,amount,shareExact);

				}

			}
		}
	}
}
/*----------------------------------------------  THANK YOU FOR USING SPLIT WISE :) -------------------------------------------*/