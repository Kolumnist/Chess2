package de.hhn.it.devtools.components.duckhunt;

import de.hhn.it.devtools.apis.duckhunt.DuckData;
import de.hhn.it.devtools.apis.duckhunt.DuckHuntListener;
import de.hhn.it.devtools.apis.duckhunt.DuckHuntService;
import de.hhn.it.devtools.apis.duckhunt.DuckState;
import de.hhn.it.devtools.apis.duckhunt.DucksInfo;
import de.hhn.it.devtools.apis.duckhunt.GameInfo;
import de.hhn.it.devtools.apis.duckhunt.GameSettingsDescriptor;
import de.hhn.it.devtools.apis.duckhunt.GameState;
import de.hhn.it.devtools.apis.duckhunt.IllegalDuckIdException;
import de.hhn.it.devtools.apis.duckhunt.IllegalDuckPositionException;
import de.hhn.it.devtools.apis.duckhunt.IllegalGameInfoException;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Represents the Duck Hunt Game and contains the main game loop.
 */
public class DuckHunt implements Runnable, DuckHuntService {
  private GameSettingsDescriptor gameSettings;
  private ScreenDimension screenDimension;
  private ArrayList<DuckHuntListener> listeners;
  private GameInfo gameInfo;
  private DuckData[] ducks;
  private int ammoCount;
  private int duckCount;
  private final int gunSpread = 5;
  private final Semaphore semaphore = new Semaphore(0);
  private boolean isRunning = false;

  /**
   * Default constructor with standard game settings.
   */
  public DuckHunt() {
    this.gameSettings = new GameSettingsDescriptor();
    this.screenDimension = new ScreenDimension(500, 500);
    init();
  }

  /**
   * Constructor with custom game settings.
   *
   * @param gameSettings represents previously set game settings
   */
  public DuckHunt(GameSettingsDescriptor gameSettings, ScreenDimension screenDimension) {
    this.gameSettings = gameSettings;
    this.screenDimension = screenDimension;
    init();
  }

  private void init() {
    this.gameInfo = new GameInfo(0, gameSettings.getAmmoAmount(), 1);
    this.listeners = new ArrayList<>();
    ammoCount = gameSettings.getAmmoAmount();
    duckCount = gameSettings.getduckAmount();
    this.ducks = new DuckData[duckCount];
    for (int i = 0; i < duckCount; i++) {
      ducks[i] = (new DuckData(i, 0, 0, DuckState.FLYING));
    }
  }

  /**
   * Shoots the gun at given coordinates, checks if ducks were hit and consumes ammo.
   *
   * @param x x cursor position
   * @param y y cursor position
   */
  @Override
  public void shoot(int x, int y) {
    for (DuckData duck : ducks) {
      if (duck.getStatus() == DuckState.DEAD) {
        continue;
      }
      if (duck.getStatus() == DuckState.FALLING && duck.getY() > screenDimension.getHeight()) {
        duck.setStatus(DuckState.DEAD);
        continue;
      }
      // if amount of the vector between duck and shoot <= gunSpread
      if (Math.sqrt(Math.pow(duck.getX() - x, 2) + Math.pow(duck.getY() - y, 2)) <= gunSpread
              && duck.getStatus() == DuckState.FLYING && gameSettings.getAmmoAmount() > 0) {
        duck.setStatus(DuckState.SCARRED);
        listeners.forEach(listener -> {
          try {
            listener.duckHit(duck.getId());
          } catch (IllegalDuckIdException e) {
            throw new RuntimeException(e);
          }
        });
      }
    }
    ammoCount--;
  }

  @Override
  public void reload() {
    ammoCount = gameSettings.getAmmoAmount();
  }

  /**
   * Checks the states of the ducks and updates their position accordingly.
   */
  private void updateDucks() {
    for (DuckData duck : ducks) {
      switch (duck.getStatus()) {
        case FLYING -> moveDuck(duck);
        case SCARRED -> duck.setStatus(DuckState.FALLING);
        case FALLING -> dropDuck(duck);
        case FLYAWAY -> {
          continue;
        }
        default -> throw new IllegalStateException("Unexpected value: " + duck.getStatus());
      }
    }
  }

  private void dropDuck(DuckData duck) {
    duck.setY(duck.getY() + 1);
  }

  private void moveDuck(DuckData duck) {
    // TODO implement duck movement
  }

  /**
   * Checks if all ducks are either dead or flyaway.
   *
   * @return boolean if all ducks are either dead or flyaway
   */
  private boolean checkRoundComplete() {
    int completeCount = 0;
    for (DuckData duck : ducks) {
      if (duck.getStatus() == DuckState.DEAD || duck.getStatus() == DuckState.FLYAWAY) {
        completeCount++;
      }
    }
    return completeCount == ducks.length;
  }

  /**
   * Resets the ducks, ammo and increments round.
   */
  private void newRound() {
    gameInfo.setRound(gameInfo.getRound() + 1);
    reload();
    for (DuckData duck : ducks) {
      duck.setStatus(DuckState.FLYING);
    }
  }

  @Override
  public void startGame() {
    listeners.forEach(
            listener -> {
              try {
                listener.newState(gameInfo);
                listener.newDuckPosition(new DucksInfo(ducks));
              } catch (IllegalGameInfoException e) {
                throw new RuntimeException(e);
              } catch (IllegalDuckPositionException e) {
                throw new RuntimeException(e);
              }
            });
    isRunning = true;
    new Thread(this).start();
  }

  @Override
  public void stopGame() {
    isRunning = false;
  }

  @Override
  public void pauseGame() {
    try {
      semaphore.tryAcquire(10, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void continueGame() {
    semaphore.release();
  }

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
    while (isRunning) {
      try {
        // waits if game is paused
        semaphore.tryAcquire(Integer.MAX_VALUE, TimeUnit.DAYS);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

      updateDucks();

      listeners.forEach(
          listener -> {
            try {
              listener.newState(gameInfo);
              listener.newDuckPosition(new DucksInfo(ducks));
            } catch (IllegalGameInfoException e) {
              throw new RuntimeException(e);
            } catch (IllegalDuckPositionException e) {
              throw new RuntimeException(e);
            }
          });

      if (checkRoundComplete()) {
        newRound();
        try {
          // timeout between rounds
          Thread.sleep(4000);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }

      semaphore.release();
    }
  }
}
