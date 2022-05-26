package models;

public class Location {

  int row;
  int column;

  Location(int positionNumber, int boardRows, int boardColumns)
  {
    this.row = (positionNumber-1)/boardColumns;
    this.column = (positionNumber - 1)%boardColumns;
  }

  public int getRow() {
    return row;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public int getColumn() {
    return column;
  }

  public void setColumn(int column) {
    this.column = column;
  }

}
