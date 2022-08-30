from user import User
from constants import *

class Splitwise:
    def __init__(self, users_list):
        self.users_graph = [ [ 0 for _ in range(User.user_count)] for i in range(User.user_count) ]
        self.users = users_list
    
    def get_users_list(self):
        return self.users

    def add_expense(self, expense):
        if EQUAL in expense:
            paid_by = expense[1]
            amt = expense[2]
            tot_users = expense[3]
            owe_users = expense[4:-1]
            self.add_equal_expense(paid_by, amt, tot_users, owe_users)
        elif EXACT in expense:
            #remaining
            self.add_Exact_Expense()
        elif PERCENT in expense:
            #remaining
            self.add_percent_expense()

    def show_all_expense(self, expense):
        pass

    def show_expense_of(self, expense):
        pass

    def add_equal_expense(self,paid_by, amt, tot_users, owe_users):
        amount = round(int(amt)/int(tot_users),2)
        for user in owe_users:
            self.users_graph[int(paid_by[1:])][int(user[1:])] = amount

    def add_Exact_Expense(self):
        pass

    def add_percent_expense(self):
        pass
        