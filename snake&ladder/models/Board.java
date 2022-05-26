package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Board {

  private int rows;
  private int columns;
  private int[][] board;
  private final List<Ladder> ladders;
  private final List<Snake> snakes;
  HashMap<Integer, Ladder> ladderFootPositions;
  HashMap<Integer, Snake> snakeHeadPosition;
  List<Player> players;

  public Board(int rows, int columns, int noOfladders, int noOfSnakes)
  {
    this.rows=rows;
    this.columns=columns;
    board = new int[rows][columns];
    ladders = new ArrayList<>();
    snakes = new ArrayList<>();
    players = new ArrayList<>();
    ladderFootPositions = new HashMap<>();
    snakeHeadPosition = new HashMap<>();
  }

  public void addLadder(Ladder ladder)
  {
    ladders.add(ladder);
    ladderFootPositions.put(ladder.getLadderFootPosition(), ladder);
  }

  public void addSnake(Snake snake)
  {
    snakes.add(snake);
    snakeHeadPosition.put(snake.getSnakeHeadPosition(), snake);
  }

  public void addPlayer(Player player)
  {
    players.add(player);
  }

  public int getRows() {
    return rows;
  }

  public int getColumns() {
    return columns;
  }

  public List<Player> getPlayers() {
    return players;
  }

  public void setPlayers(List<Player> players) {
    this.players = players;
  }

  public int getNumberOfPlayers()
  {
    return this.players.size();
  }

  public HashMap<Integer, Ladder> getLadderFootPositions() {
    return ladderFootPositions;
  }

  public void setLadderFootPositions(HashMap<Integer, Ladder> ladderFootPositions) {
    this.ladderFootPositions = ladderFootPositions;
  }

  public HashMap<Integer, Snake> getSnakeHeadPosition() {
    return snakeHeadPosition;
  }

  public void setSnakeHeadPosition(HashMap<Integer, Snake> snakeHeadPosition) {
    this.snakeHeadPosition = snakeHeadPosition;
  }


}
