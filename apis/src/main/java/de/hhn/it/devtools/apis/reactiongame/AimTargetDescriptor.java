package de.hhn.it.devtools.apis.reactiongame;


public class AimTargetDescriptor {

  private final int x;
  private final int y;
  private final char key;


  public AimTargetDescriptor(int x, int y, char key) {
    this.x = x;
    this.y = y;
    this.key = key;
  }

  @Override
  public String toString() {
    return "AimTargetDescriptor{" +
        "x=" + x +
        ", y=" + y +
        ", key=" + key +
        '}';
  }
}
