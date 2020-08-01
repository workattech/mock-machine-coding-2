from models.expense.expense import Expense
from models.user_ledger import Ledger
from utils.utils import is_approx


class ExactExpense(Expense):
    def __init__(self, users, total_amount, paid_by, amount_list, **kwargs):
        Expense.__init__(self, total_amount, paid_by, kwargs.get('name'), kwargs.get('note'))
        self.users = users
        self.amount_list = amount_list
        self.update_ledger()

    def update_ledger(self):
        for i in range(0, len(self.users)):
            if not self.users[i].id == self.paid_by.id:
                Ledger.update_ledger(self.paid_by, self.users[i], self.amount_list[i])

    @staticmethod
    def validate(total_amount, amounts_list):
        sum_amount = 0
        for amount in amounts_list:
            sum_amount += amount
        return is_approx(sum_amount, total_amount)
