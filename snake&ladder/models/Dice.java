package models;

public class Dice {

  int firstNumber=1;
  int lastNumber;

  public Dice(int lastNumber)
  {
    this.lastNumber = lastNumber;
  }

  public int getLastNumber() {
    return lastNumber;
  }

  public void setLastNumber(int lastNumber) {
    this.lastNumber = lastNumber;
  }

  public int getFirstNumber() {
    return firstNumber;
  }




}
