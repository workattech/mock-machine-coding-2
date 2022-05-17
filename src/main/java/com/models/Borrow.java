package com.models;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by bugkiller on 21/09/19.
 */
@Builder
@Getter
public class Borrow {

  private String borrower;
  private String lender;
  private double amount;

  public String formattedOutput() {
    return String.format("%s Owes %s : %f", borrower, lender, amount);
  }
}
