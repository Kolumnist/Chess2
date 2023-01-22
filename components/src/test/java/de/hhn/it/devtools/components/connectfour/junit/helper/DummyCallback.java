package de.hhn.it.devtools.components.connectfour.junit.helper;

import de.hhn.it.devtools.apis.connectfour.interfaces.ConnectFourListenerInterface;

public class DummyCallback implements ConnectFourListenerInterface {
  @Override
  public void updateDescription(String description) {

  }

  @Override
  public void updateTile(int column, int row, String color) {

  }

  @Override
  public void lock() {

  }

  @Override
  public void unlock() {

  }

  @Override
  public void save() {

  }
}
