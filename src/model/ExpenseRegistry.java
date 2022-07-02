package model;

import java.util.ArrayList;
import java.util.List;

public class ExpenseRegistry {

  private static List<ExpenseInfo> expenseRegistry = new ArrayList<>();

  public static void addExpense(ExpenseInfo expense) {
    expenseRegistry.add(expense);
  }

  public static List<ExpenseInfo> getExpenseRegistry() {
    return expenseRegistry;
  }

  public static List<ExpenseInfo> forUser(User user) {
    List<ExpenseInfo> expenseInfo = new ArrayList<>();
    for(ExpenseInfo expenses: expenseRegistry) {
      if(expenses.getUser1().equals(user) || expenses.getUser2().equals(user)) {
        expenseInfo.add(expenses);
      }
    }
    return expenseInfo;
  }
}
