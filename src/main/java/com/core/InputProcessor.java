package com.core;

import com.dao.ExpenseDao;
import com.dao.impl.InMemoryExpenseDao;
import com.factory.SplitStrategyFactory;
import com.models.Transaction;
import com.split.SplitStrategy;
import java.util.function.Consumer;

/**
 * Created by bugkiller on 21/09/19.
 */
public class InputProcessor implements Consumer<String> {

  private ExpenseDao expenseDao;

  public InputProcessor() {
    expenseDao = InMemoryExpenseDao.getInstance();
  }

  @Override
  public void accept(String inputLine) {
    if (!inputLine.contains("SHOW")) {
      SplitStrategy splitStrategy = SplitStrategyFactory.provide(inputLine);
      Transaction transaction = splitStrategy.split(inputLine);
      expenseDao.addTransaction(transaction);
    } else {
      String[] showForUser = inputLine.split("\\s");
      System.out.println(expenseDao.showUserBalance(showForUser[1]));
    }
  }
}
