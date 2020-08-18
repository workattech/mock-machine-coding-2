from models.expense.equal_expense import EqualExpense
from models.expense.exact_expense import ExactExpense
from models.expense.percentage_expense import PercentageExpense
from models.user_ledger import Ledger


class SplitWise:
    def __init__(self):
        self.expenses = []
        self.user_ledgers = {}

    def add_expense(self, paid_by, total_paid_amount, users, split_type, **kwargs):
        if split_type == 'EQUAL':
            expense = EqualExpense(users, total_paid_amount, paid_by)
        elif split_type == 'PERCENT' and PercentageExpense.validate(total_paid_amount, kwargs.get('split_amount')):
            expense = PercentageExpense(users, total_paid_amount, paid_by, kwargs.get('split_amount'))
        elif split_type == 'EXACT' and ExactExpense.validate(total_paid_amount, kwargs.get('split_amount')):
            expense = ExactExpense(users, total_paid_amount, paid_by, kwargs.get('split_amount'))
        else:
            print("Unknown split type.")
            return
        self.expenses.append(expense)
        print("Expense Added successfully!")

    def show_balance_by_userid(self, user_id):
        Ledger.get_user_balance(int(user_id))

    def show_all_balances(self):
        Ledger.get_all_balances()
