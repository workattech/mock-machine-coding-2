from abc import ABCMeta, abstractmethod


class Expense:
    id_count = 0
    user_expense = {}
    settled = False

    def __init__(self, total_amount, paid_by, note, name):
        self.id = Expense.id_count
        Expense.id_count += 1
        self.name = name
        self.paid_by = paid_by
        self.total_amount = total_amount
        self.note = note

    @abstractmethod
    def update_ledger(self):
        pass

    def mark_as_settled(self):
        self.settled = True

    @staticmethod
    @abstractmethod
    def validate(total_amount, split_amount):
        pass
