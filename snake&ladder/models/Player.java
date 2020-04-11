package models;

public class Player {
  String name;
  Location location;
  int positionNumber;

  public Player(String name, int positionNumber, int boardRows, int boardColumns)
  {
    this.name = name;
    this.positionNumber = positionNumber;
    this.location = new Location(positionNumber, boardRows, boardColumns);
  }

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public int getPositionNumber() {
    return positionNumber;
  }

  public void setPositionNumber(int positionNumber) {
    this.positionNumber = positionNumber;
  }


}
