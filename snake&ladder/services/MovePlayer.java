package services;

import models.Board;
import models.Player;

public class MovePlayer {
  /*
   * Given player & no of steps check if player can move forward
   * and update the position as per game
   *
   */
  int movePlayer(Player player, int moves, Board board){

    if(!checkIfMovePossible(player, moves, board.getRows()*board.getColumns())) {
      return player.getPositionNumber();
    }
    player.setPositionNumber(player.getPositionNumber()+moves);

    /*
     * if there is a snake or ladder there then update finalPos
     */
    return decideFinalPosition(player, board);

  }



  private int decideFinalPosition(Player player, Board board) {

    if(board.getLadderFootPositions().containsKey(player.getPositionNumber())) {
      player.setPositionNumber(board.getLadderFootPositions().get(player.getPositionNumber()).getLadderTopPosition());
      return player.getPositionNumber();
    }

    if(board.getSnakeHeadPosition().containsKey(player.getPositionNumber()))
    {
      player.setPositionNumber(board.getSnakeHeadPosition().get(player.getPositionNumber()).getSnakeTailPosition());
      return player.getPositionNumber();
    }
    return player.getPositionNumber();
  }



  boolean checkIfPlayerHasReachedEnd(Player player, int boardEndPosition)
  {
    return (player.getPositionNumber()==(boardEndPosition))?true:false;
  }

  private boolean checkIfMovePossible(Player player, int moves, int maxPosition) {

    return (player.getPositionNumber()+moves<=maxPosition)? true: false;
  }

}
