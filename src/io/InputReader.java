package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class InputReader {
  private BufferedReader reader;
  private StringTokenizer tokenizer;

  public InputReader() {
    tokenizer = null;
    reader = new BufferedReader(new InputStreamReader(System.in), 32768);
  }

  public String next() {
    while(tokenizer == null || !tokenizer.hasMoreTokens()) {
      try {
        tokenizer = new StringTokenizer(reader.readLine(), "\n");
      } catch(IOException e) {
        throw new RuntimeException(e);
      }
    }
    return tokenizer.nextToken();
  }

  public int nextInt() { return Integer.parseInt(next()); }

  public long nextLong() { return Long.parseLong(next()); }

  public float nextFloat() { return Float.parseFloat(next()); }

  public void close() {
    try {
      reader.close();
    } catch(IOException e) {
      throw new RuntimeException(e);
    }
  }
}
