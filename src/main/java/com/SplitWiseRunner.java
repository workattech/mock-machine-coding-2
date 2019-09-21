package com;

import com.core.InputProcessor;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by bugkiller on 21/09/19.
 */
public class SplitWiseRunner {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("input.txt"));
    br.lines().forEach(new InputProcessor());
  }
}
