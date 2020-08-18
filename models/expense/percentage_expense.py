from models.expense.expense import Expense
from models.user_ledger import Ledger
from utils.utils import is_approx


class PercentageExpense(Expense):
    def __init__(self, users, total_amount, paid_by, percentages, **kwargs):
        Expense.__init__(self, total_amount, paid_by, kwargs.get('name'), kwargs.get('note'))
        self.users = users
        self.percentage = percentages
        self.update_ledger()

    def update_ledger(self):
        for i in range(0, len(self.users)):
            if not self.users[i].id == self.paid_by.id:
                amount = self.total_amount * (1 - self.percentage[i] / 100)
                Ledger.update_ledger(self.paid_by, self.users[i], amount)

    @staticmethod
    def validate(total_amount, percentages):
        return is_approx(sum(percentages), 100)
