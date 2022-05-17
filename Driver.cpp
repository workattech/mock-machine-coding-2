#include<bits/stdc++.h>
#include<iostream>
using namespace std;


vector<string> tokenize(string input)
{
	vector<string> tokens;
	string temp="";
			for(int j=0;j<input.size();j++)
			{
				if(input[j]==' ')
				{
					tokens.push_back(temp);
					temp="";
				}
				else
					temp+=input[j];
			}
			tokens.push_back(temp);
			//cout<<tokens[0]<<endl;
	return tokens;		
}

class User
{
public:
	int userId;
	string name;
	string email;
	int mobile_no;
	User(int userId, string name, string email,int mobile_no)
	{
		this->userId=userId;
		this->name=name;
		this->email=email;
		this->mobile_no=mobile_no;
	}
};


class SplitwiseController
{
public:
	vector<User> user_list;
	vector<vector<int> > balance_sheet;
	int user_count;
	int max_users;
	SplitwiseController()
	{
		max_users=100;
		user_count=0;
		
		User u(0,"none","none",0);
		vector<User> t1(max_users+1,u);
		user_list=t1;

		vector<vector<int> >	t2;
		t2.assign(max_users+1,vector<int>(max_users+1,0));	
		balance_sheet=t2;
	}
	void AddUser(int userId, string name, string email,int mobile_no)
	{
		User u(userId, name, email, mobile_no);
		user_count++;
		user_list[user_count]=u;
	}
	void FunctionCall(int n)
	{
		string input;
		getline(cin,input);
		for(int i=0;i<n;i++)
		{
			getline(cin,input);
			//cin>>input;
			//cout<<"INPUT LINE--"<<input<<endl;
			vector<string> tokens;
			tokens=tokenize(input);
			//cout<<tokens[0]<<endl;
			if(tokens[0].compare("SHOW")==0)
			{
				//cout<<"SHOW MethoD"<<input<<endl;
				if(tokens.size()==2)
				{
					int id=stoi(tokens[1].substr(1,tokens[1].size()-1));
					showIdMethod(id);
				}
				else
				{
					showAllMethod();
				}
			}
			else 
			{
				
				int paid_user_id=stoi(tokens[1].substr(1,tokens[1].size()-1));
				//cout<<paid_user_id<<endl;
				int value=stoi(tokens[2]);
				//cout<<value<<endl;
				int no_of_users=stoi(tokens[3]);
				//cout<<no_of_users<<endl;
				vector<int> owing_users_id(no_of_users);
				int k;
				for(k=4;k<=4+no_of_users-1;k++)
				{
					owing_users_id[k-4]=stoi(tokens[k].substr(1,tokens[k].size()-1));
					//cout<<owing_users_id[k-4]<<endl;
				}

				vector<int> owing_users_value(no_of_users);
				if(tokens[k].compare("EQUAL")==0)
				{
					int index=0;
					//vector<int> owing_users_value(no_of_users);
					for(int index=0;index<no_of_users;index++)
					{
						owing_users_value[index]=(ceil((value/no_of_users)*100)/100);
					}
					//EqualMethod(paid_user_id,value,no_of_users,owing_users_id);
				}
				else if(tokens[k].compare("EXACT")==0)
				{
					k++;
					int index=0;
					//vector<int> owing_users_value(no_of_users);
					for(;k<tokens.size();k++)
					{
						owing_users_value[index]=stoi(tokens[k]);
						index++;
					}
					//ExactMethod(
				}
				else
				{
					k++;
					int index=0;
					for(;k<tokens.size();k++)
					{
						owing_users_value[index]=((stoi(tokens[k])*0.01)*value);
						index++;
					}
					//PercentMethod();
				}
				FillBalances(paid_user_id,no_of_users,owing_users_id,owing_users_value);
			}
			
		}
	}
	void FillBalances(int paid_user_id,int no_of_users,vector<int> owing_users_id,vector<int> owing_users_value)
	{
		for(int i=0;i<no_of_users;i++)
		{
			balance_sheet[paid_user_id][owing_users_id[i]]+=owing_users_value[i];
			balance_sheet[owing_users_id[i]][paid_user_id]+=(-1*owing_users_value[i]);
		}
	}

	void showAllMethod()
	{
		bool flag=false;
		for(int i=1;i<=user_count;i++)
		{
			for(int j=1;j<=user_count;j++)
			{
				if(i==j)
					continue;
				if(balance_sheet[i][j]<0)
				{
					flag=true;
					cout<<user_list[i].name<<" owers "<<user_list[j].name<<" : "<<-1*balance_sheet[i][j]<<endl;
				}
			}
		}
		if(flag==false)
			cout<<"no balances"<<endl;
	}

	void showIdMethod(int i)
	{
		bool flag=false;
		for(int j=1;j<=user_count;j++)
		{
			if(i==j)
				continue;
			if(balance_sheet[i][j]<0)
			{
				flag=true;
				cout<<user_list[i].name<<" owers "<<user_list[j].name<<" : "<<-1*balance_sheet[i][j]<<endl;
			}
			if(balance_sheet[i][j]>0)
			{
				flag=true;
				cout<<user_list[j].name<<" owers "<<user_list[i].name<<" : "<<balance_sheet[i][j]<<endl;
			}
		}
		if(flag==false)
			cout<<"no balances"<<endl;
	}

};

int main()
{
	SplitwiseController sc;
	sc.AddUser(1,"user1", "dcvefvef",0);
	sc.AddUser(2,"user2", "dcvefvef",0);
	sc.AddUser(3,"user3", "dcvefvef",0);
	sc.AddUser(4,"user4", "dcvefvef",0);
	sc.AddUser(4,"user5", "dcvefvef",0);
	int n;
	cin>>n;
	sc.FunctionCall(n);	
	return 0;
}

