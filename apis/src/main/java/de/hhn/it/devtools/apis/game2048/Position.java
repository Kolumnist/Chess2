package de.hhn.it.devtools.apis.game2048;

public class Position {
  private final int xPosition;
  private final int yPosition;


  public Position(int xPosition, int yPosition) {
    // TODO: 03.01.2023 andere exception verwerfen?
    if (xPosition < 0 || xPosition > 3) throw new RuntimeException("Illigal xPosition Parameter");
    if (yPosition < 0 || yPosition > 3) throw new RuntimeException("Illigal yPosition Parameter");
    this.xPosition = xPosition;
    this.yPosition = yPosition;
  }

  public int getXPosition() {
    return xPosition;
  }

  public int getYPosition() {
    return yPosition;
  }

  public Position changeXPosition(int xPosition) {
    if (xPosition < 0 || xPosition > 3) throw new RuntimeException("Illigal xPosition Parameter");
    return new Position(xPosition, this.yPosition);
  }

  public Position changeYPosition(int yPosition) {
    if (yPosition < 0 || yPosition > 3) throw new RuntimeException("Illigal yPosition Parameter");
    return new Position(this.xPosition, yPosition);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Position position = (Position) o;
    return xPosition == position.xPosition && yPosition == position.yPosition;
  }

  @Override
  public String toString() {
    return "(" + xPosition + "/" + yPosition + ")";
  }
}
