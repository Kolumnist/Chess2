package de.hhn.it.devtools.apis.battleship;

/**
 * to give every battleship its own position on the field
 */
public class Position {

  Integer x;
  Integer y;


  public Position(Integer x, Integer y) {
    this.x = x;
    this.y = y;
  }

  public Position(Integer integer, Object o) {
  }


  /**
   * @return x coordinate of the ship
   */
  public int getX() {
    return this.x;
  }


  /**
   * @return return y coordinate of the ship
   */
  public int getY() {
    return this.y;
  }


}
