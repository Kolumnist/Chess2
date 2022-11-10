package de.hhn.it.devtools.apis.duckhunt;

/**
 * This Class saves information about the duck.
 */
public class DuckData {
  private int id;
  private int x;
  private int y;
  private DuckState status;
  public DuckData(int id, int x, int y, DuckState status){
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

