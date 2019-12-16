package utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

import model.ExpenseInfo;
import model.ExpenseRegistry;
import model.User;
import model.UserRegistry;

public class ExpenseUtils {

  private static boolean simplify = false;

  public static void splitEqual(User user,
                                long amount,
                                int splitSize,
                                List<User> otherUsers) {
    int size = otherUsers.size();
    if(size != splitSize) {
      System.out.println("Split size mismatch! Cannot split.");
      return;
    }

    long amountToSplit = amount / splitSize;

    for(User otherUser : otherUsers) {
      if(!user.equals(otherUser)) {
        split(user, otherUser, amountToSplit);
      }
    }
  }

  public static void splitExact(User user,
                                int splitSize,
                                List<User> otherUsers,
                                List<Long> amountsToSplit) {
    int userSize = otherUsers.size();
    int amountSize = amountsToSplit.size();
    if (splitSize != userSize || splitSize != amountSize) {
      System.out.println("Split size mismatch! Cannot split.");
      return;
    }

    for(int i = 0; i < splitSize; ++i) {
      split(user, otherUsers.get(i), amountsToSplit.get(i));
    }
  }

  public static void splitPercentage(User user,
                                     long amount,
                                     int splitSize,
                                     List<User> otherUsers,
                                     List<Float> percentages) {
    if(splitSize != otherUsers.size() || splitSize != percentages.size()) {
      System.out.println("Split size mismatch! Cannot split.");
      return;
    }

    for(int i = 0; i < splitSize; ++i) {
      long amountToSplit = (long) (amount * percentages.get(i)/100);
      split(user, otherUsers.get(i), amountToSplit);
    }
  }

  private static void split(User user,
                            User otherUser,
                            long amountToSplit) {
    Map<User, Long> balance = user.getBalance();
    Long balanceValue = balance.get(otherUser);

    if(balanceValue == null) {
      balance.put(otherUser, amountToSplit);
    } else {
      balance.put(otherUser, balanceValue + amountToSplit);
    }

    Map<User, Long> otherUserBalance = otherUser.getBalance();
    balanceValue = otherUserBalance.get(user);
    if(balanceValue == null) {
      otherUserBalance.put(user, -1 * amountToSplit);
    } else {
      otherUserBalance.put(user, balanceValue - amountToSplit);
    }

    addToExpenses(user, otherUser, amountToSplit);
  }

  private static void addToExpenses(User user,
                                    User otherUser,
                                    long amountToSplit) {
    ExpenseRegistry.addExpense(new ExpenseInfo(user, otherUser, amountToSplit));
  }

  public static void switchSimplify() {
    if(simplify) {
      simplify = false;
    } else {
      simplify = true;
    }
  }

  public static void simplifyExpenses() {
    if(simplify) {

    }
  }
}
