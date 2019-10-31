#include<iostream>
#include<vector>
#include<string>
#define no_of_user 4
using namespace std;
class Splitwise
{
    //Balance sheet which keeps the record of which user owes whom and how much
    vector<vector<int>> balanceSheet;
    vector<User> users;
    public:


        Splitwise(vector<User> users)
        {
            for(int i=0;i<users.size();i++)
                this->users.push_back(users[i]);
            balanceSheet.resize(no_of_user,vector<int>(no_of_user));
        }
        void showAll()
        {
            bool flag = false;
            for(int i=0;i<no_of_user;i++)
            {
                for(int j=0;j<no_of_user;j++)
                {
                    if(balanceSheet[i][j]!=0 && i!=j)
                    {
                        cout<<users[j].getId()<<" owes "<<users[i].getId()<<":"<<balanceSheet[i][j]<<endl;
                        flag = true;
                    }
                }
            }
            if(!flag)
                cout<<"No Balances"<<endl;
        }
        void showId(string uid)
        {
            int i = findUser(uid);
            bool flag = false;
            for(int j=0;j<no_of_user;j++)
            {
                if(balanceSheet[i][j]!=0 && i!=j)
                {
                    cout<<users[j].getId()<<" owes "<<users[i].getId()<<":"<<balanceSheet[i][j]<<endl;
                    flag = true;
                }
                if(balanceSheet[j][i]!=0 && i!=j)
                {
                    cout<<users[i].getId()<<" owes "<<users[j].getId()<<":"<<balanceSheet[j][i]<<endl;
                    flag = true;
                }
            }
            if(!flag)
                cout<<"No Balances"<<endl;

        }
        void Call(vector<string> token)
        {
            if(token.size()==0)
                return;
            if(token[0].compare("SHOW")==0)
            {
                if(token.size()==2)
                {
                    showId(token[1]);
                }
                else
                    showAll();
            }
            else //EXPENSE
            {
                 int i = findUser(token[1]);
                 double amount = stoi(token[2]);
                 int number = stoi(token[3]);
                 if(token[4+number].compare("EQUAL")==0)
                 {
                     //cout<<"EQUAL "<<endl;
                     splitEqual(token,number,i,amount);
                 }
                 else if(token[4+number].compare("PERCENT")==0)
                 {
                     //cout<<"PERCENT"<<endl;
                    splitPercent(token,number,i,amount);
                 }
                 else
                 {
                     //cout<<"EXACT"<<endl;
                    splitExact(token,number,i,amount);
                 }
            }
        }
        void splitEqual(vector<string> token,int number,int id,double amount)
        {
            //return;
            for(int i=4;i<number+4;i++)
            {
                balanceSheet[id][findUser(token[i])] += amount/number;
            }
        }
        void splitPercent(vector<string> token,int number,int id,double amount)
        {

            for(int i=4;i<4+number;i++)
            {
                int percent = stoi(token[i+1+number]);
                //cout<<id<<" "<<findUser(token[i])<<endl;
                balanceSheet[id][findUser(token[i])] += ((amount*percent)/100);
            }
        }
        void splitExact(vector<string> token,int number,int id,double amount)
        {
            for(int i=4;i<4+number;i++)
            {
                int amt = stoi(token[i+1+number]);
                balanceSheet[id][findUser(token[i])] += amt;
            }
        }
        int findUser(string uid)
        {
            int i=0;
            for(;i<no_of_user;i++)
            {
                if(uid.compare(users[i].getId())==0)
                    break;
            }
            return i;
        }
};
