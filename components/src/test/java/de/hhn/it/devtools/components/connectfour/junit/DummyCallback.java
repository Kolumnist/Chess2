package de.hhn.it.devtools.components.connectfour.junit;

import de.hhn.it.devtools.apis.connectfour.interfaces.ConnectFourListenerInterface;

public class DummyCallback implements ConnectFourListenerInterface {
  private String description;
  private int column;
  private int row;
  private String color;
  private boolean isLocked;
  private boolean isSaved;

  @Override
  public void updateDescription(String description) {
    this.description = description;
  }

  @Override
  public void updateTile(int column, int row, String color) {
    this.column = column;
    this.row = row;
    this.color = color;
  }

  @Override
  public void lock() {
    isLocked = true;
  }

  @Override
  public void unlock() {
    isLocked = false;
  }

  @Override
  public void save() {
    isSaved = true;
  }

  public String getDescription() {
    return description;
  }

  public int getColumn() {
    return column;
  }

  public int getRow() {
    return row;
  }

  public String getColor() {
    return color;
  }

  public boolean isLocked() {
    return isLocked;
  }

  public boolean isSaved() {
    return isSaved;
  }
}
