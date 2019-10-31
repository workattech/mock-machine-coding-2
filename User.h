#include<iostream>
#include<vector>
#include<string>
using namespace std;
class User{
    string userid;
    string name, email;
    long int mobile;
    public:
        User(string userid="",string name="",string email="",long int mobile=0)
        {
            this->userid = userid;
            this->name = name;
            this->email = email;
            this->mobile = mobile;
        }
        string getName()
        {
            return name;
        }
        string getId()
        {
            return userid;
        }
        string getEmail()
        {
            return email;
        }

};
