#include "split_service.h"

void SplitService::set_user_table(string user_id, User* t)
{
   user_table[user_id] = t;
   users.push_back(user_id);
}

void SplitService::EXPENSE_EQUAL(int total, string payer, vector <string> table)
{
    int count = table.size();
    int per_share = total/count;
    unordered_map <string, User*>::iterator itr = user_table.find(payer);
    if (itr == user_table.end())
      {
         cout << "no such user exists!! " << endl;
         return;
      }    
    else
      {
        int row = itr->second->get_seq();
        for (int i = 0; i < count; i++)
           {
              unordered_map <string, User*>::iterator itr1 = user_table.find(table[i]);
              if (itr1 != user_table.end())
                {
                   int col = itr1->second->get_seq();
                   expense_table[row][col] = expense_table[row][col] + per_share;
                   expense_table[col][row] = expense_table[col][row] - per_share;
                }
           } 
      }
}

void SplitService::EXPENSE_EXACT(int total, string payer, vector <string> table, vector<int> amount)
{ 
     int count = table.size();
     int total_per = 0;
     for (int i = 0; i < count; i++)
     {
         total_per += amount[i];
     }
     if (total_per != total)
     {
         cout << "invalid_entry!!!" << endl;
         return;
     }
     unordered_map <string, User*>::iterator itr = user_table.find(payer);
     if (itr == user_table.end())
      {
         cout << "no such user exists!! " << endl;
         return;
      }
    else
      {
        int row = itr->second->get_seq();
        for (int i = 0; i < count; i++)
           {
              unordered_map <string, User*>::iterator itr1 = user_table.find(table[i]);
              if (itr1 != user_table.end())
                {
                   int col = itr1->second->get_seq();
                   expense_table[row][col] = expense_table[row][col] + amount[i];
                   expense_table[col][row] = expense_table[col][row] - amount[i];
                }
           }
       }
}

void SplitService::EXPENSE_PERCENT(int total, string payer, vector <string> table, vector<float> percent)
{
     int count = table.size();
     int total_per = 0;
     for (int i = 0; i < count; i++)
     {
         total_per += percent[i];
     }
     if (total_per != 100)
     {
         cout << "invalid_entry!!!" << endl;
         return;
     }
     unordered_map <string, User*>::iterator itr = user_table.find(payer);
     if (itr == user_table.end())
      {
         cout << "no such user exists!! " << endl;
         return;
      }
    else
      {
        int row = itr->second->get_seq();
        for (int i = 0; i < count; i++)
           {
              float amount = float(percent[i]/100);
              unordered_map <string, User*>::iterator itr1 = user_table.find(table[i]);
              if (itr1 != user_table.end())
                {
                   int col = itr1->second->get_seq();
                   expense_table[row][col] = expense_table[row][col] + (float(total) * amount);
                   expense_table[col][row] = expense_table[col][row] - (float(total) * amount);
                }
           }
       }
}
void SplitService::SHOW_ALL()
{
   int balance_exist = 0;
   for (int i = 0; i<num_of_user; i++)
    {
       if (SHOW(users[i], 1) == 1)
         balance_exist = 1;
    }
   if (balance_exist == 0)
      cout << "No balances" << endl;
}

int SplitService::SHOW(string u, int all)
{
    unordered_map <string, User*>::iterator itr = user_table.find(u);
    int balance_exist = 0;
     if (itr == user_table.end())
      {
         cout << "no such user exists!! " << endl;
         return 0;
      }
    int seq_no = itr->second->get_seq();
    for (int i = 0; i< num_of_user; i++)
     {
         if (i != seq_no)
          {
             if (expense_table[seq_no][i] != 0)
               {
                  balance_exist = 1;
                  if (expense_table[seq_no][i] > 0)
                   {
                      cout << users[i] << " owes " << u << ": " << expense_table[seq_no][i] << endl;
                   }
                  else 
                   { 
                      if (all == 0)
                         cout << u << " owes " << users[i] << ": " << 0 - expense_table[seq_no][i] << endl;
                   }
              }
          }
     }
     if (balance_exist == 0 && (all == 0))
       cout << "No balances" << endl;
     return balance_exist;
}

