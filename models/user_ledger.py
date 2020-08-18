class Ledger:
    def __init__(self):
        pass
    '''
    {u1 :{u2:-50}
    {u2 :{u1:50}
    '''
    transaction_entry = {}

    @staticmethod
    def crate_ledger_entry(user, users):
        Ledger.transaction_entry[user.id] = {}
        for u in users:
            Ledger.transaction_entry[user.id][u.id] = 0


    @staticmethod
    def update_ledger(paid_by, user, amount):
        if paid_by.id in Ledger.transaction_entry.keys():
            Ledger.transaction_entry[paid_by.id][user.id] -= round(amount, 2)
            Ledger.transaction_entry[user.id][paid_by.id] += round(amount, 2)
        else:
            print("No Account for this user")

    @staticmethod
    def get_user_balance(user_id):
        flag=0
        if Ledger.transaction_entry.get(user_id):
            ledger = Ledger.transaction_entry[user_id]
            for id in ledger:
                if ledger[id] > 0:
                    print "User ", user_id, " owes ", ledger[id], "rupees to ", user_id
                    flag=1
        if flag==0:
            print("No Balance")

    @staticmethod
    def get_all_balances():
        flag = 0
        if Ledger.transaction_entry:
            for user in Ledger.transaction_entry:
                transactions = Ledger.transaction_entry.get(user)
                if transactions:
                    for key, val in transactions.items():
                        if val<0:
                            print "User ", key, " owes ", abs(val), "rupees to ", user
                            flag = 1
        if flag == 0:
            print("NO BALANCE")
