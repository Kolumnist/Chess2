package de.hhn.it.devtools.components.duckhunt.junit;

import de.hhn.it.devtools.apis.duckhunt.DuckHuntListener;
import de.hhn.it.devtools.apis.duckhunt.DucksInfo;
import de.hhn.it.devtools.apis.duckhunt.GameInfo;
import de.hhn.it.devtools.apis.duckhunt.IllegalDuckIdException;
import de.hhn.it.devtools.apis.duckhunt.IllegalDuckPositionException;
import de.hhn.it.devtools.apis.duckhunt.IllegalGameInfoException;

class DuckHuntListenerTest implements DuckHuntListener {
  public GameInfo gameInfo;
  public int newStateCallCount = 0;
  public DucksInfo duckPosition;
  public int newDuckPositionCallCount = 0;
  public int duckHitId = -1;

  @Override
  public void newState(GameInfo gameInfo) throws IllegalGameInfoException {
    this.gameInfo = gameInfo;
    newStateCallCount++;
  }

  @Override
  public void newDuckPosition(DucksInfo duckPosition) throws IllegalDuckPositionException {
    this.duckPosition = duckPosition;
    //System.out.println(duckPosition.duckData()[0]);
    newDuckPositionCallCount++;
  }

  @Override
  public void duckHit(int id) throws IllegalDuckIdException {
    System.out.println("Duck " + id + " was hit");
    duckHitId = id;
  }
}
