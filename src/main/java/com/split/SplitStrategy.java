package com.split;

import com.models.Transaction;

public interface SplitStrategy {

  Transaction split(String inputLine);
}
