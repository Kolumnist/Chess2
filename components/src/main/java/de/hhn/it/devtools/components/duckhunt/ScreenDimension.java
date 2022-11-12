package de.hhn.it.devtools.components.duckhunt;

/**
 * Represents the dimension of the screen (height and width).
 */
public class ScreenDimension {
  private int width;
  private int height;

  public ScreenDimension(int width, int height) {
    this.width = width;
    this.height = height;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }
}
