# coding=utf-8
# This is a sample Python script.

# Press ⇧F10 to execute it or replace it with your code.
# Press Double ⇧ to search everywhere for classes, files, tool windows, actions, and settings.
from models.user_ledger import Ledger
from splitwise import SplitWise
from models.user import User


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    splitwise = SplitWise()
    '''
    Creating Users
    '''
    u1 = User('Ram', 'ram@gmail.com', 1234567890)
    u2 = User('Shyam', 'shyam@gmail.com', 9876543211)
    u3 = User('Ghanshyam', 'ghanshyam@gmail.com', 432167890)
    u4 = User('shyamshan', 'sh@gmail.com', 43216781290)

    '''
    Creating ledger entry for each User.
    '''
    Ledger.crate_ledger_entry(u1, [u2, u3, u4])
    Ledger.crate_ledger_entry(u2, [u1, u3, u4])
    Ledger.crate_ledger_entry(u3, [u1, u2, u4])
    Ledger.crate_ledger_entry(u4, [u1, u2, u3])

    '''
    Mapping username with object.
    '''
    user_obj = {"u1": u1, "u2": u2, "u3": u3, "u4": u4}

    '''
    Start Taking input from here.
    '''

    while 1:
        command = str(raw_input("Enter your input"))
        command = command.split(' ')
        users = []
        split_amount = []

        if command[0] == 'EXPENSE':
            paid_by = user_obj[command[1]]
            total_amount = float(command[2])
            total_users = int(command[3])
            for i in range(0, total_users):
                users.append(user_obj[command[i + 4]])
            split_type = command[total_users + 4]

            if split_type in ['PERCENT', 'EXACT']:
                for i in range(0, total_users):
                    split_amount.append(float(command[total_users + 5 + i]))
            splitwise.add_expense(paid_by, total_amount, users, split_type, split_amount=split_amount)

        elif command[0] == "SHOW" and len(command) > 1:
            splitwise.show_balance_by_userid(command[1])
        else:
            splitwise.show_all_balances()
