package de.hhn.it.devtools.components.duckhunt;

import de.hhn.it.devtools.apis.duckhunt.*;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import java.util.ArrayList;

/** */
public class DuckHunt implements Runnable, DuckHuntService {
  private GameSettingsDescriptor gameSettings;
  private ArrayList<DuckHuntListener> listeners;
  private GameState gameState;
  private DuckData[] ducks;

  /** */
  public DuckHunt() {
    this.gameSettings = new GameSettingsDescriptor();
  }

  /**
   * @param gameSettings
   */
  public DuckHunt(GameSettingsDescriptor gameSettings) {
    this.gameSettings = gameSettings;
  }

  private void init() {
    this.gameState = new GameState(0, gameSettings.getAmmoAmount(), 1);
    this.listeners = new ArrayList<>();

    int duckCount = 2;
    this.ducks = new DuckData[duckCount];
    for (int i = 0; i < duckCount; i++) {
      ducks[i] = (new DuckData(i, 0, 0));
    }
  }

  @Override
  public void shoot(int x, int y) {
    for (DuckData duck : ducks) {
      // if amount of the vector between duck and shoot <= 5
      if (Math.sqrt(Math.pow(duck.x() - x, 2) + Math.pow(duck.y() - y, 2)) <= 5) {

      }
    }
  }

  @Override
  public void reload() {}

  @Override
  public void startGame() {}

  @Override
  public void stopGame() {}

  @Override
  public void pauseGame() {}

  @Override
  public void changeGameSettings(GameSettingsDescriptor gameSettings)
      throws IllegalParameterException {
    if (gameSettings == null) {
      throw new IllegalParameterException();
    }
    this.gameSettings = gameSettings;
  }

  @Override
  public void addCallback(DuckHuntListener listener) throws IllegalParameterException {
    listeners.add(listener);
  }

  @Override
  public void removeCallback(DuckHuntListener listener) throws IllegalParameterException {
    listeners.remove(listener);
  }

  @Override
  public void run() {
    // main game loop
    while (true) {

      listeners.forEach(
          listener -> {
            try {
              listener.newState(gameState);
              listener.newDuckPosition(new DucksState(ducks));
            } catch (IllegalGameStateException e) {
              throw new RuntimeException(e);
            } catch (IllegalDuckPositionException e) {
              throw new RuntimeException(e);
            }
          });
    }
  }
}
