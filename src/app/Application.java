package app;

import java.util.ArrayList;
import java.util.List;

import io.InputReader;
import model.ExpenseMode;
import model.User;
import model.UserRegistry;
import utils.DisplayUtils;
import utils.ExpenseUtils;
import utils.FormatUtils;

public class Application {

  public static void main(String[] args) {
    User u1 = new User("u1", "u1@gmail.com", "81924305812");
    User u2 = new User("u2", "u2@gmail.com", "83624265812");
    User u3 = new User("u3", "u3@gmail.com", "91465305812");
    User u4 = new User("u4", "u4@gmail.com", "97924345852");
    UserRegistry.addUser(u1);
    UserRegistry.addUser(u2);
    UserRegistry.addUser(u3);
    UserRegistry.addUser(u4);

    InputReader in = new InputReader();

    while(true) {
      String[] input = in.next().split(" ");

      if(input[0].equalsIgnoreCase("SHOW")) {
        if(input.length == 1) {
          System.out.println(DisplayUtils.getNetBalance());
        } else {
          User user = UserRegistry.fromName(input[1]);
          System.out.println(DisplayUtils.getUserBalance(user));
        }
      } else if(input[0].equalsIgnoreCase("EXPENSE")) {
        int counter = 1;
        User user = UserRegistry.fromName(input[counter]);
        long amount = FormatUtils.getLong(input[++counter]);
        int splitSize = FormatUtils.getInt(input[++counter]);

        List<User> users = new ArrayList<>(splitSize);
        for(int i = 0; i < splitSize; ++i) {
          users.add(UserRegistry.fromName(input[++counter]));
        }

        ExpenseMode expenseMode = ExpenseMode.valueOf(input[++counter]);
        calculateExpense(user, counter, amount, splitSize, input, users, expenseMode);
      } else {
        System.out.println("Invalid input!");
      }
    }
  }

  private static void calculateExpense(User user,
                                       int counter,
                                       long amount,
                                       int splitSize,
                                       String[] input,
                                       List<User> users,
                                       ExpenseMode expenseMode) {
    if(expenseMode == ExpenseMode.EQUAL) {
      ExpenseUtils.splitEqual(user, amount, splitSize, users);

    } else if(expenseMode == ExpenseMode.EXACT) {
      List<Long> amountsToSplit = new ArrayList<>(splitSize);
      for(int i = 0; i < splitSize; ++i) {
        amountsToSplit.add(FormatUtils.getLong(input[++counter]));
      }
      ExpenseUtils.splitExact(user, splitSize, users, amountsToSplit);

    } else if(expenseMode == ExpenseMode.PERCENT) {
      List<Float> percentages = new ArrayList<>(splitSize);
      for(int i = 0; i < splitSize; ++i) {
        percentages.add(FormatUtils.getFloat(input[++counter]));
      }
      ExpenseUtils.splitPercentage(user, amount, splitSize, users, percentages);

    } else {
      System.out.println("Invalid Expense Mode!");
    }
  }
}
