package de.hhn.it.devtools.apis.game2048;

/**
 * This class represent the blocks with their position and their values.
 * A Block in the bottom left corner has xposition = yposition = 0
 * This class is Immutable
 * Caution Block1.equals(Block2) == true, when position is equal,
 * the value is irrelevant!!
 */
public class Block {
  private final Position xyPosition;
  private final int value;

  /**
   * This is a Constructor.
   *
   * @param xyPosition x- & y-Coordinate of the block on the game Board.
   * @param value      Current Value of the block
   */
  public Block(Position xyPosition, int value) {
    if (value % 2 != 0) throw new RuntimeException("Illegal value Parameter");
    this.xyPosition = xyPosition;
    this.value = value;
  }

  public Position getXYPosition() {
    return xyPosition;
  }

  public Block changePosition(Position xyPosition) {
    return new Block(xyPosition,value);
  }

  public Block changeXPosition(int xPosition){
    Position nextPosition = new Position(xPosition, xyPosition.getYPosition());
    return new Block(nextPosition,value);
  }

  public Block changeYPosition(int yPosition){
    Position nextPosition = new Position(xyPosition.getXPosition(), yPosition);
    return new Block(nextPosition,value);
  }

  public int getValue() {
    return value;
  }

  public Block changeValue(Integer value) {
    return new Block(xyPosition,value);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Block block = (Block) o;
    return xyPosition.equals(block.xyPosition);
  }

  public String toString() {
    return "{ " + xyPosition + " value = " + value +" }";
  }
}