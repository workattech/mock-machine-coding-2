from splitwise import Splitwise
from user import User
from constants import *


u1 = User("gopal", "gopal@mail.com", "9687121229")
u2 = User("yadav", "yadav@mail.com", "5656556661")
u3 = User("karan", "karan@mail.com", "5455121229")
users_list = [ u1, u2, u3]

splitwise = Splitwise(users_list)
splitwise.add_users(users_list)

users_list = splitwise.get_users()

while True:
    print("Input your query.....Type EXIT to exit...")
    expense = input()
    expense = expense.split(' ')
    if expense[0] == EXPENSE:
        splitwise.add_expense(expense)
    elif expense[0] == SHOW and len(expense)==1:
        splitwise.show_all_expense(expense)
    elif expense[0] == SHOW and len(expense)==2:
        splitwise.show_expense_of(expense)
    elif expense == EXIT:
        print("Bye..")
        break
    else:
        print("Please give valid input....")
