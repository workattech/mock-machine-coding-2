from models.expense.expense import Expense
from models.user_ledger import Ledger
from utils.utils import is_approx


class EqualExpense(Expense):
    def __init__(self, users, total_amount, paid_by, **kwargs):
        Expense.__init__(self, total_amount, paid_by, kwargs.get('name'), kwargs.get('note'))
        self.users = users
        self.amount = total_amount / len(users)
        self.update_ledger()

    def update_ledger(self):
        for i in self.users:
            if i.id != self.paid_by.id:
                Ledger.update_ledger(self.paid_by, i, self.amount)
