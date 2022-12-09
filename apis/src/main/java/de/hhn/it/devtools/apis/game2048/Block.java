package de.hhn.it.devtools.apis.game2048;

/**
 * This class represent the blocks with their position and their values.
 */
public class Block {
  private int xposition;
  private int yposition;
  private int value;

  /**
   * This is a Constructor.
   *
   * @param xposition x-Coordinate of the block on the game Board.
   * @param yposition y-Coordinate of the block on the game Board.
   * @param value Current Value of the block
   */
  public Block(int xposition, int yposition, int value) {
    this.xposition = xposition;
    this.yposition = yposition;
    this.value = value;
  }

  public int getXposition() {
    return xposition;
  }

  public void setXposition(int xposition) {
    this.xposition = xposition;
  }

  public int getYposition() {
    return yposition;
  }

  public void setYposition(int yposition) {
    this.yposition = yposition;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }
}