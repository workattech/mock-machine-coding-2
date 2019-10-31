#include<iostream>
#include<vector>
#include<string>
#include "User.h"
#include "Splitwise.h"
#define no_of_user 4
using namespace std;

vector<string> generateTokens(string s)
{
    vector<string> tokens;
    int n = s.length();
    for(int i=0;i<n;i++)
    {
        string temp;
        while(i<n && s[i]!=' ')
        {
            temp.push_back(s[i]);i++;
        }
        tokens.push_back(temp);
    }
    return tokens;
}

int main()
{
    User u1("u1","user1","",0),u2("u2","user2","",0),u3("u3","user3","",0),u4("u4","user4","",0);
    vector<User> users;
    users.push_back(u1);
    users.push_back(u2);
    users.push_back(u3);
    users.push_back(u4);
    Splitwise sw(users);
    //sw.showAll();
    int n;
    cin>>n;
    //cout<<n<<endl;
    while(n)
    {
        string s;
        //cout<<"Enter string : ";
        getline(cin,s);
        //cout<<s<<endl;
        //cout<<"input string is : "<<s<<endl;
        vector<string> tokens = generateTokens(s);
        sw.Call(tokens);
        n--;
    }
    return 0;
}
