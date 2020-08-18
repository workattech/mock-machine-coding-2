class User:
    id_count = 0

    def __init__(self, name, email, mobile_no):
        self.id = User.id_count
        User.id_count += 1
        self.name = name
        self.email = email
        self.mobile_no = mobile_no
        self.total_balance = 0
        self.total_expense = 0

    def get_total_balance(self):
        return self.total_balance

    def update_total_balance(self, amount):
        self.total_balance += amount

    def get_total_expense(self):
        return self.total_balance

    def update_total_expense(self, amount):
        self.total_expense += amount