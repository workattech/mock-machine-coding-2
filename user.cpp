class user{
    int userid;
    string name, email;
    long int mobile;
    public:
        user(int userid=0,string name="",string email="",int mobile=0)
        {
            this->userid = userid;
            this->name = name;
            this->email = email;
            this->mobile = mobile;
        }
};

