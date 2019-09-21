package com.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;

/**
 * Created by bugkiller on 21/09/19.
 */

public class UserBalance {

  @Getter
  private String userId;
  private Map<String, Double> payTo;
  private Map<String, Double> receiveFrom;

  public UserBalance(String userId) {
    this.userId = userId;
    payTo = new HashMap<>();
    receiveFrom = new HashMap<>();
  }

  public void receiveFrom(String userId, double amount) {
    receiveFrom.merge(userId, amount, (oldValue, newValue) -> oldValue + amount);
    settle(userId);
  }

  public void payTo(String userId, double amount) {
    payTo.merge(userId, amount, (oldValue, newValue) -> oldValue + amount);
    settle(userId);
  }

  private void settle(String externalUserId) {
    Double payToUser = payTo.getOrDefault(externalUserId, (double) 0);
    Double receiveFromUser = receiveFrom.getOrDefault(externalUserId, (double) 0);
    if (payToUser > receiveFromUser) {
      payTo.put(externalUserId, payToUser - receiveFromUser);
      receiveFrom.remove(externalUserId);
    } else if (payToUser < receiveFromUser) {
      receiveFrom.put(externalUserId, receiveFromUser - payToUser);
      payTo.remove(externalUserId);
    } else {
      receiveFrom.remove(externalUserId);
      payTo.remove(externalUserId);
    }
  }

  public List<Borrow> getAllReceiveFrom() {
    List<Borrow> allReceiveFrom = new ArrayList<>();
    payTo.forEach((key, value) -> {
      allReceiveFrom.add(Borrow.builder().lender(userId).borrower(key).amount(value).build());
    });
    return allReceiveFrom;
  }

  public List<Borrow> getAllPayTo() {
    List<Borrow> allPayTo = new ArrayList<>();
    payTo.forEach((key, value) -> {
      allPayTo.add(Borrow.builder().lender(key).borrower(userId).amount(value).build());
    });
    return allPayTo;
  }
}
