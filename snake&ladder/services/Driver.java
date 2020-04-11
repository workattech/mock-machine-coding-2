package services;

import java.util.Scanner;

public class Driver {

  public static void main(String[] args) {
    Scanner s=new Scanner(System.in);
    /*
     * First Take all inputs
     */
    int noOfLadders=0, noOfSnakes = 0, noOfPlayers=0;
    int ladders[][];
    int snakes[][];
    String playerNames[];

    noOfLadders = s.nextInt();
    ladders = new int[noOfLadders][noOfLadders>0?2:0];
    for(int idx=0;idx<noOfLadders;idx++)
    {
      ladders[idx][0]=s.nextInt();
      ladders[idx][1]=s.nextInt();
    }

    noOfSnakes = s.nextInt();
    snakes = new int[noOfSnakes][noOfSnakes>0?2:0];
    for(int idx=0;idx<noOfSnakes;idx++)
    {
      snakes[idx][0]=s.nextInt();
      snakes[idx][1]=s.nextInt();
    }

    noOfPlayers = s.nextInt();
    playerNames = new String[noOfPlayers];
    for(int idx=0;idx<noOfPlayers;idx++) {
      playerNames[idx]=s.next();
    }

    /*
     * Setup the board game with the players
     */

    InitialiseBoard setupGame = new InitialiseBoard(10, 10, ladders, snakes);
    setupGame.setPlayers(playerNames);

    GameController controller = new GameController(setupGame.board);

    controller.startGame();

  }
}
