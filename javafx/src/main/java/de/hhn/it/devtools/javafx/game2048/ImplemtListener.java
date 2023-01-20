package de.hhn.it.devtools.javafx.game2048;

import de.hhn.it.devtools.apis.game2048.Game2048Listener;
import de.hhn.it.devtools.apis.game2048.State;
import de.hhn.it.devtools.javafx.controllers.game2048.Game2048ViewController;

public class ImplemtListener implements Game2048Listener{
  Game2048ViewController controller;

  public ImplemtListener( Game2048ViewController controller){
    this.controller = controller;
  }

  @Override
  public void newState(State state) {
    controller.setState(state);
  }
}