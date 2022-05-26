package services;

import models.Board;
import models.Ladder;
import models.Player;
import models.Snake;

public class InitialiseBoard {

  Board board;
  public InitialiseBoard(int rows, int columns, int ladders[][], int snakes[][]) {

    board = new Board(rows, columns, ladders.length, snakes.length);
    defineLadders(ladders);
    defineSnakes(snakes);
  }

  void defineLadders(int ladders[][])
  {
    for (int[] ladderPosition : ladders)
    {
      Ladder ladder = new Ladder(ladderPosition[0], ladderPosition[1],
          board.getRows(), board.getColumns());
      board.addLadder(ladder);

    }
  }

  void defineSnakes(int snakes[][])
  {
    for (int[] snakePosition : snakes)
    {
      Snake snake = new Snake(snakePosition[0], snakePosition[1],
          board.getRows(), board.getColumns());
      board.addSnake(snake);

    }
  }

  void setPlayers(String[] playerNames)
  {
    for (String playerName : playerNames) {

      Player player = new Player(playerName, 0,
          board.getRows(), board.getColumns());

      board.addPlayer(player);

    }
  }
}
