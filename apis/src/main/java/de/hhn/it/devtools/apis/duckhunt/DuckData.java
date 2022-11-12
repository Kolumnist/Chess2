package de.hhn.it.devtools.apis.duckhunt;

/**
 * This Class saves information about the duck, such as an id
 * to identify the duck, a x- and y-coordinate and
 * it's current state.
 */
public class DuckData {
  private int id;
  private int x;
  private int y;
  private DuckState status;

  /**
   * Constructor to create a GameInfo-Class.
   *
   * @param id identification-number for the duck
   * @param x X position of the duck
   * @param y Y position of the duck
   * @param status status of the duck
   */
  public DuckData(int id, int x, int y, DuckState status) {
    this.id = id;
    this.x = x;
    this.y = y;
    this.status  = status;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public DuckState getStatus() {
    return status;
  }

  public void setStatus(DuckState status) {
    this.status = status;
  }
}

