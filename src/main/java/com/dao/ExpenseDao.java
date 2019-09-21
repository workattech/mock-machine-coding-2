package com.dao;

import com.models.Transaction;

public interface ExpenseDao {

  void addTransaction(Transaction transaction);

  String showUserBalance(String userId);
}
