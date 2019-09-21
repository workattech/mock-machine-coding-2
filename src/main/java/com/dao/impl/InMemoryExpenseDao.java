package com.dao.impl;

import com.dao.ExpenseDao;
import com.models.Borrow;
import com.models.Transaction;
import com.models.UserBalance;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bugkiller on 21/09/19.
 */
public class InMemoryExpenseDao implements ExpenseDao {

  private static final InMemoryExpenseDao IN_MEMORY_EXPENSE_DAO = new InMemoryExpenseDao();

  private Map<String, UserBalance> userBalanceData = new HashMap<>();

  private InMemoryExpenseDao() { }

  public static ExpenseDao getInstance() {
    return IN_MEMORY_EXPENSE_DAO;
  }

  @Override
  public void addTransaction(Transaction transaction) {
    List<Borrow> borrowList = transaction.getBorrowList();
    for (Borrow borrow : borrowList) {
      UserBalance lenderBalanceData = userBalanceData.getOrDefault(borrow.getLender(), new UserBalance(borrow.getLender()));
      UserBalance borrowerBalanceData = userBalanceData.getOrDefault(borrow.getBorrower(), new UserBalance(borrow.getBorrower()));
      lenderBalanceData.receiveFrom(borrow.getBorrower(), borrow.getAmount());
      borrowerBalanceData.payTo(borrow.getLender(), borrow.getAmount());
      userBalanceData.putIfAbsent(lenderBalanceData.getUserId(), lenderBalanceData);
      userBalanceData.putIfAbsent(borrowerBalanceData.getUserId(), borrowerBalanceData);
    }
  }

  @Override
  public String showUserBalance(String userId) {
    UserBalance userBalance = userBalanceData.get(userId);
    List<Borrow> debitList = userBalance.getAllPayTo();
    List<Borrow> creditList = userBalance.getAllReceiveFrom();
    StringBuilder sbr = new StringBuilder();
    debitList.forEach(borrow -> sbr.append(borrow.formattedOutput()).append("\n"));
    creditList.forEach(borrow -> sbr.append(borrow.formattedOutput()).append("\n"));
    return sbr.toString();
  }
}
