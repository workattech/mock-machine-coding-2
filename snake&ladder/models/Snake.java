package models;

public class Snake {

  int id;
  int snakeHeadPosition;
  int snakeTailPosition;
  Location tail;
  Location head;

  public Snake(int headPosition, int tailPosition, int boardRows, int boardColumns)
  {
    this.head = new Location(headPosition, boardRows, boardColumns);
    this.tail = new Location(tailPosition, boardRows, boardColumns);
  }

  public int getSnakeHeadPosition() {
    return snakeHeadPosition;
  }

  public void setSnakeHeadPosition(int snakeHeadPosition) {
    this.snakeHeadPosition = snakeHeadPosition;
  }

  public int getSnakeTailPosition() {
    return snakeTailPosition;
  }

  public void setSnakeTailPosition(int snakeTailPosition) {
    this.snakeTailPosition = snakeTailPosition;
  }


}
