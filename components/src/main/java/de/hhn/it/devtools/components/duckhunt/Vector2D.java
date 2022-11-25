package de.hhn.it.devtools.components.duckhunt;

import java.util.Objects;

/**
 * Class for 2-dimensional vectors.
 */
public class Vector2D {
  private int x;
  private int y;

  public Vector2D() {
    this.x = 0;
    this.y = 0;
  }

  public Vector2D(int x, int y) {
    this.x = x;
    this.y = y;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Vector2D vector2D = (Vector2D) o;
    return getX() == vector2D.getX() && getY() == vector2D.getY();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getX(), getY());
  }

  @Override
  public String toString() {
    return "Vector2D{" + "x=" + x + ", y=" + y + '}';
  }
}
