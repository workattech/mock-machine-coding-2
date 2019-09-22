package com.split.impl;

import com.models.Borrow;
import com.models.Transaction;
import com.split.SplitStrategy;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bugkiller on 21/09/19.
 */
public class PercentSplitStrategy implements SplitStrategy {

  private static DecimalFormat decimalFormat = new DecimalFormat(".##");

  public PercentSplitStrategy() {
    decimalFormat.setRoundingMode(RoundingMode.UP);
  }

  @Override
  public Transaction split(String inputLine) {
    String split[] = inputLine.split("PERCENT");
    String spenderUsersAndAmount[] = split[0].split("\\s");
    String shareList[] = split[1].split("\\s");
    validateShareList(shareList);
    String spender = spenderUsersAndAmount[1];
    double amount = Double.parseDouble(spenderUsersAndAmount[2]);
    List<Borrow> borrowList = new ArrayList<>();
    for (int i = 4, j = 1; i < spenderUsersAndAmount.length; i++) {
      double share = Double.parseDouble(shareList[j]);
      double borrowAmount = amount * (share / 100);
      borrowList.add(Borrow.builder()
                           .lender(spender)
                           .borrower(spenderUsersAndAmount[i])
                           .amount(borrowAmount)
                           .build());
    }
    return Transaction.builder()
                      .borrowList(borrowList)
                      .spentBy(spender)
                      .build();
  }

  private void validateShareList(String[] shareList) {
    double percentSum = 0;
    for (int i = 1; i < shareList.length; i++) {
      String percent = shareList[i];
      percentSum += Double.parseDouble(percent);
    }
    if (percentSum != 100) {
      throw new IllegalArgumentException("Percent don't add up to 100");
    }
  }
}
