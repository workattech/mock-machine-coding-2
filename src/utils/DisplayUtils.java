package utils;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.ExpenseInfo;
import model.ExpenseRegistry;
import model.User;
import model.UserRegistry;

public class DisplayUtils {

  private static Set<String> getBalance(User user) {
    Set<String> balanceInfo = new HashSet<>();
    Map<User, Long> balance = user.getBalance();
    for(Map.Entry<User, Long> entry: balance.entrySet()) {
      User otherUser = entry.getKey();
      long bal = entry.getValue();

      if(bal < 0) {
        balanceInfo.add(user.getName() + " owes " + otherUser.getName() + ": " + fmtMoney(bal));
      } else if(bal > 0) {
        balanceInfo.add(otherUser.getName() + " owes " + user.getName() + ": " + fmtMoney(bal));
      }
    }
    return balanceInfo;
  }

  public static String getUserBalance(User user) {
    Set<String> balance = getBalance(user);
    if(balance.isEmpty()) { return "No balances"; }
    return String.join("\n", balance) + "\n";
  }

  public static String getNetBalance() {
    Set<String> balanceInfo = new HashSet<>();
    Set<User> userRegistry = UserRegistry.getUserRegistry();

    for(User user: userRegistry) {
      balanceInfo.addAll(getBalance(user));
    }

    if(balanceInfo.isEmpty()) { return "No balances"; }
    return String.join("\n", balanceInfo) + "\n";
  }

  private static String fmtMoney(long money) {
    money = (money < 0) ? money * -1 : money;
    return String.valueOf((money / 100) + "." + (money % 100));
  }

  public static String getUserExpenseHistory(User user) {
    List<ExpenseInfo> expenses = ExpenseRegistry.forUser(user);
    Set<String> expenseStrings = new HashSet<>();
    for(ExpenseInfo expenseInfo : expenses) {
      expenseStrings.add(expenseInfo.toString());
    }
    return String.join(",", expenseStrings);
  }
}
