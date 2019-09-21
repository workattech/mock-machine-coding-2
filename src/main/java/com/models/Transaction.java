package com.models;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

/**
 * Created by bugkiller on 21/09/19.
 */
@Builder
@Getter
public class Transaction {

  private String spentBy;
  private List<Borrow> borrowList;

  public String getSpentBy() {
    return spentBy;
  }
}
