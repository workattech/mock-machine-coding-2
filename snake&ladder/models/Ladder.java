package models;

public class Ladder {

  int id;
  int ladderFootPosition;
  int ladderTopPosition;
  Location ladderFoot;
  Location ladderTop;

  public Ladder(int startPositionNumber, int endPositionNumber,
      int boardRows, int boardColumns)
  {
    this.ladderFootPosition = startPositionNumber;
    this.ladderTopPosition = endPositionNumber;
    ladderFoot = new Location(startPositionNumber, boardRows, boardColumns);
    ladderTop = new Location(endPositionNumber, boardRows, boardColumns);
  }

  public Location getLadderFoot() {
    return ladderFoot;
  }

  public void setLadderFoot(Location ladderFoot) {
    this.ladderFoot = ladderFoot;
  }

  public Location getLadderHead() {
    return ladderTop;
  }

  public void setLadderHead(Location ladderHead) {
    this.ladderTop = ladderHead;
  }

  public int getLadderFootPosition() {
    return ladderFootPosition;
  }

  public void setLadderFootPosition(int ladderFootPosition) {
    this.ladderFootPosition = ladderFootPosition;
  }

  public int getLadderTopPosition() {
    return ladderTopPosition;
  }

  public void setLadderTopPosition(int ladderTopPosition) {
    this.ladderTopPosition = ladderTopPosition;
  }


}
