#include "split_service.h"

int main()
{
   SplitService *ser = new SplitService(4);

   User *U1 = new User(0, "u1", "Susmita", "sm@com", "11890");
   ser->set_user_table("u1", U1);

   User *U2 = new User(1, "u2", "Ayan", "ay@com", "11890");
   ser->set_user_table("u2", U2);

   User *U3 = new User(2, "u3", "Moumita", "mm@com", "11890");
   ser->set_user_table("u3", U3);

   User *U4 = new User(3, "u4", "Subhayan", "sb@com", "11890");
   ser->set_user_table("u4", U4);

   int cont = 0;
   do{
        cout << "Enter operation " << endl;
        string op;
        cin >> op;
        if (op == "SHOW")
        {
           cout << "for a specific user?" << endl;
           string ans;
           cin >> ans;
           if (ans == "YES" || ans == "Yes" || ans == "yes")
             {
                cout << "Enter user id" << endl;
                string u;
                cin >> u;
                ser->SHOW(u, 0);
             }
           else
             {
                ser->SHOW_ALL();
             }
        }
        else if (op == "EXPENSE")
        {
           int num = 0;
           string payer;
           vector <string> users;
           string type;
           int total;
           cout << "Enter payer " << endl;
           cin >> payer;
           cout << "Enter total " << endl;
           cin >> total;
           cout << "Enter no of users " << endl;
           cin >> num;
           cout << "enter user_ids " << endl;
           for (int i = 0; i<num; i++)
           {
              string u;
              cin >> u;
              users.push_back(u);
           }
           cout << "Enter type of expense, EXACT, EQUAL or PERCENT " << endl;
           cin >> type;
           if (type == "EQUAL")
             ser->EXPENSE_EQUAL(total, payer, users);
           else if (type == "EXACT")
           {
              cout << "Enter amounts " << endl;
              vector <int> amounts;
              for (int i = 0; i<num; i++)
              {
                 int amount;
                 cin >> amount;
                 amounts.push_back(amount);
              }
              ser->EXPENSE_EXACT(total, payer, users, amounts);
           }
           else if (type == "PERCENT")
           {
              cout << "Enter percents " << endl;
              vector <float> percents;
              for (int i = 0; i<num; i++)
              {
                 float percent;
                 cin >> percent;
                 percents.push_back(percent);
              }
              ser->EXPENSE_PERCENT(total, payer, users, percents);
           }
           else 
             cout << "INVALID TYPE" << endl;
        }
        else
          cout << "INVALID OPERTAION " << endl;

        cout << "Press 1 to continue" << endl;
        cin >> cont;
   }while (cont == 1);
   
   return 0;
}
