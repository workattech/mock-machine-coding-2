#include<vector>
#include "user.h"

class SplitService
{
   /*** this map will contain all the user details with user id as key **/
   unordered_map <string, User *> user_table;

   /*** this table will contain all the expense details between users ***/
   float **expense_table;

   /*** this is a table of user ids with user seq no as index  **********/
   vector<string> users;

   int num_of_user;

   public:
         SplitService(int no)
         {
             num_of_user = no;
             expense_table = new float*[num_of_user];
             for (int i = 0; i<num_of_user; i++)
             {
                 expense_table[i] = new float[num_of_user];
             }
             for (int i = 0; i<num_of_user; i++)
              {
                  for (int j = 0; j < num_of_user; j++)
                      expense_table[i][j] = 0.0;
              }        
         }
         int SHOW(string u, int all);
         void SHOW_ALL();
         void set_user_table(string user_id, User * t);
         void EXPENSE_EQUAL(int total, string payer, vector <string> table);
         void EXPENSE_EXACT(int total, string payer, vector <string> table, vector<int> amount);
         void EXPENSE_PERCENT(int total, string payer, vector <string> table, vector<float> amount);
};


