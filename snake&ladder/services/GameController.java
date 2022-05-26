package services;

import models.Board;
import models.Player;

public class GameController {

  Board board;
  boolean winnerFound;
  Player winner;

  public GameController(Board board) {
    this.board = board;
    this.winnerFound=false;
  }

  void startGame()
  {
    DiceRoll diceRoll = new DiceRoll(6);
    MovePlayer movePlayer = new MovePlayer();

    while(!winnerFound)
    {
      for(int i=0;i<board.getNumberOfPlayers();i++)
      {
        int moves = diceRoll.returnMoves();
        Player player = board.getPlayers().get(i);
        System.out.println(player.getName()+" rolled an "+moves);

        int position = movePlayer.movePlayer(player, moves, board);

        checksPositionAndUpdatesGameStatus(position, player);
        if(winnerFound)
        {
          printWinner();
          break;
        }
      }
    }
  }

  private void printWinner() {

    System.out.println(this.winner.getName()+" has Won!");
  }

  private void checksPositionAndUpdatesGameStatus(int position, Player player) {
    if(player.getPositionNumber()==board.getColumns()*board.getRows())
    {
      this.winnerFound = true;
      this.winner = player;
    }
  }



}
