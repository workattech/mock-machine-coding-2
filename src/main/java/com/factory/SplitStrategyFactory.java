package com.factory;

import com.split.SplitStrategy;
import com.split.impl.EqualSplitStrategy;
import com.split.impl.ExactSplitStrategy;
import com.split.impl.PercentSplitStrategy;

/**
 * Created by bugkiller on 21/09/19.
 */
public class SplitStrategyFactory {
  private static final SplitStrategy EQUAL_SPLIT_STRATEGY;
  private static final  SplitStrategy PERCENT_SPLIT_STRATEGY;
  private static final  SplitStrategy EXACT_SPLIT_STRATEGY;

  static {
    EQUAL_SPLIT_STRATEGY = new EqualSplitStrategy();
    EXACT_SPLIT_STRATEGY = new ExactSplitStrategy();
    PERCENT_SPLIT_STRATEGY = new PercentSplitStrategy();
  }

  public static SplitStrategy provide(String inputLine) {
    if (inputLine.contains("EXACT")) {
      return EXACT_SPLIT_STRATEGY;
    } else if (inputLine.contains("EQUAL")) {
      return EQUAL_SPLIT_STRATEGY;
    } else if (inputLine.contains("PERCENT")) {
      return PERCENT_SPLIT_STRATEGY;
    }
    throw new IllegalArgumentException("Invalid Input");
  }
}
