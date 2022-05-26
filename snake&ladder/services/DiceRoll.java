package services;

import models.Dice;

public class DiceRoll {

  Dice dice;
  DiceRoll(int diceSize)
  {
    this.dice = new Dice(diceSize);
  }
  int returnMoves()
  {
    return (int) ((Math.random() * ((this.dice.getLastNumber() - this.dice.getFirstNumber()) + 1)) + this.dice.getFirstNumber());
  }
}
