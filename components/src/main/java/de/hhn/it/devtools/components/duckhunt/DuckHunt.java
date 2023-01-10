package de.hhn.it.devtools.components.duckhunt;

import de.hhn.it.devtools.apis.duckhunt.DuckData;
import de.hhn.it.devtools.apis.duckhunt.DuckHuntListener;
import de.hhn.it.devtools.apis.duckhunt.DuckHuntService;
import de.hhn.it.devtools.apis.duckhunt.DuckOrientation;
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
import java.util.EmptyStackException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Represents the Duck Hunt Game and contains the main game loop.
 */
public class DuckHunt implements DuckHuntService {
  private GameSettingsDescriptor gameSettings;
  private GameInfo gameInfo;
  private DucksInfo ducksInfo;
  private ScreenDimension screenDimension;
  private ArrayList<DuckHuntListener> listeners;
  private DuckData[] ducks;
  private int ammoCount;
  private int duckCount;
  private int missedCount = 15;
  private final int gunSpread = 5;
  private MpatternGenerator pathGenerator;
  private float deltaTime = 0.016f;
  private final float duckSpeed = 1f;
  DuckHuntGameLoop gameLoop = new DuckHuntGameLoop(this);

  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(DuckHunt.class);

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
    this.gameInfo = new GameInfo(0, gameSettings.getAmmoAmount(), 0);
    this.listeners = new ArrayList<>();
    ammoCount = gameSettings.getAmmoAmount();
    duckCount = gameSettings.getduckAmount();
    // init ducks
    this.ducks = new DuckData[duckCount];
    for (int i = 0; i < duckCount; i++) {
      ducks[i] = (new DuckData(i, 0, 0, DuckState.FLYING));
    }
    this.ducksInfo = new DucksInfo(ducks);
    // init duck paths
    this.pathGenerator = new MpatternGenerator(50, screenDimension);
  }

  /**
   * Shoots the gun at given coordinates, checks if ducks were hit and consumes ammo.
   *
   * @param x x cursor position
   * @param y y cursor position
   */
  @Override
  public void shoot(int x, int y) {
    logger.info("shoot: x = {}, y = {}", x, y);
    // TODO ERROR Handling shoot
    /*if (x < 0 || x > screenDimension.getWidth()) {
      throw new RuntimeException();
    }*/
    /*if (gameInfo.getState() != GameState.RUNNING) throw new Exception();*/
    for (DuckData duck : ducks) {
      if (duck.getStatus() == DuckState.DEAD
          || duck.getStatus() == DuckState.FLYAWAY
          || duck.getStatus() == DuckState.SCARRED) {
        continue;
      }
      // if amount of the vector between duck and shoot <= gunSpread
      if (Math.sqrt(Math.pow(duck.getX() - x, 2) + Math.pow(duck.getY() - y, 2)) <= gunSpread
              && gameSettings.getAmmoAmount() > 0) {
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
    gameInfo.setAmmo(ammoCount);

    listeners.forEach(listener -> {
      try {
        listener.newState(gameInfo);
      } catch (IllegalGameInfoException e) {
        throw new RuntimeException(e);
      }
    });
  }

  @Override
  public void shootObstacle() {
    logger.info("shootObstacle: no params");
    ammoCount--;
    gameInfo.setAmmo(ammoCount);

    listeners.forEach(listener -> {
      try {
        listener.newState(gameInfo);
      } catch (IllegalGameInfoException e) {
        throw new RuntimeException(e);
      }
    });
  }

  @Override
  public void reload() {
    ammoCount = gameSettings.getAmmoAmount();
    gameInfo.setAmmo(ammoCount);

    listeners.forEach(listener -> {
      try {
        listener.newState(gameInfo);
      } catch (IllegalGameInfoException e) {
        throw new RuntimeException(e);
      }
    });
  }

  @Override
  public void startGame() {
    logger.info("startGame(): no params");
    newRound();
    gameLoop.startLoop();
    gameInfo.setState(GameState.RUNNING);
    listeners.forEach(
            listener -> {
              try {
                listener.newState(gameInfo);
              } catch (IllegalGameInfoException e) {
                throw new RuntimeException(e);
              }
            });
  }

  @Override
  public void stopGame() {
    logger.info("stopGame(): no params");
    gameLoop.stopLoop();
    gameInfo.setState(GameState.GAMEOVER);
    listeners.forEach(
            listener -> {
              try {
                listener.newState(gameInfo);
              } catch (IllegalGameInfoException e) {
                throw new RuntimeException(e);
              }
            });
  }

  @Override
  public void pauseGame() {
    logger.info("pauseGame(): no params");
    gameLoop.pauseLoop();
    gameInfo.setState(GameState.PAUSED);
    listeners.forEach(
            listener -> {
              try {
                listener.newState(gameInfo);
              } catch (IllegalGameInfoException e) {
                throw new RuntimeException(e);
              }
            });
  }

  @Override
  public void continueGame() {
    logger.info("continueGame(): no params");
    gameLoop.continueLoop();
    gameInfo.setState(GameState.RUNNING);
    listeners.forEach(
            listener -> {
              try {
                listener.newState(gameInfo);
              } catch (IllegalGameInfoException e) {
                throw new RuntimeException(e);
              }
            });
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
    if (listener == null) {
      throw new IllegalParameterException("Listener cannot be null");
    }
    if (listeners.contains(listener)) {
      throw new IllegalParameterException("The given listener already exist in the class");
    }
    listeners.add(listener);
  }

  @Override
  public void removeCallback(DuckHuntListener listener) throws IllegalParameterException {
    if (listener == null) {
      throw new IllegalParameterException("Listener cannot be null");
    }
    if (!listeners.contains(listener)) {
      throw new IllegalParameterException("The given listener does not exist in the class");
    }
    listeners.remove(listener);
  }

  /**
   * Checks the states of the ducks and updates their position accordingly.
   */
  public void updateDucks() {
    for (DuckData duck : ducks) {
      if (duck.getStatus() == DuckState.FLYING || duck.getStatus() == DuckState.FALLING) {
        // velocity = resolutionCoefficient * speed * deltaTime
        float newVelocity = 0.3f * duckSpeed * deltaTime;
        duck.setVelocity(duck.getVelocity() + newVelocity);
      }
      if (duck.getStatus() == DuckState.FALLING && duck.getY() > screenDimension.getHeight()) {
        duck.setStatus(DuckState.DEAD);
      }
      switch (duck.getStatus()) {
        case FLYING -> moveDuck(duck);
        case SCARRED -> duck.setStatus(DuckState.FALLING);
        case FALLING -> dropDuck(duck);
        case FLYAWAY -> ascendDuck(duck);
        case DEAD -> { /*dead is not used in this method*/ }
        default -> throw new IllegalStateException("Unexpected value: " + duck.getStatus());
      }
    }
    ducksInfo = new DucksInfo(ducks);
  }

  private void dropDuck(DuckData duck) {
    float velocity = duck.getVelocity();
    if (velocity > 1f) { // if true duck can be moved to next position
      duck.setY(duck.getY() + 1);
      duck.setVelocity(velocity - 1f);
    }
  }

  private void ascendDuck(DuckData duck) {
    float velocity = duck.getVelocity();
    if (velocity > 1f) { // if true duck can be moved to next position
      duck.setY(duck.getY() - 1);
      duck.setVelocity(velocity - 1f);
    }
  }

  private void moveDuck(DuckData duck) {
    float velocity = duck.getVelocity();
    if (velocity > 1f) { // if true duck can be moved to next position
      DuckOrientation newOrientation;
      try {
        newOrientation = pathGenerator.getNextMove(duck.getId());
      } catch (EmptyStackException e) {
        logger.debug(duck + " reached end of path and is now flyaway");
        duck.setStatus(DuckState.FLYAWAY);
        return;
      }
      duck.setOrientation(newOrientation);
      duck.setX(duck.getX() + newOrientation.getX());
      duck.setY(duck.getY() + newOrientation.getY());
      duck.setVelocity(velocity - 1f);
    }
  }

  private void calculateNewDuckPaths() {
    pathGenerator.clearPaths();
    try {
      pathGenerator.generatePaths(ducks);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Checks if all ducks are either dead or flyaway.
   *
   * @return boolean if all ducks are either dead or flyaway
   */
  public boolean checkRoundComplete() {
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
  public void newRound() {
    gameInfo.setRound(gameInfo.getRound() + 1);
    reload();
    calculateNewDuckPaths();
    for (DuckData duck : ducks) {
      duck.setStatus(DuckState.FLYING);
      duck.setX(pathGenerator.getStartingPos(duck.getId()).getX());
      duck.setY(pathGenerator.getStartingPos(duck.getId()).getY());
    }
  }

  public boolean checkGameOver() {
    for (DuckData duck : ducks) {
      if (duck.getStatus() == DuckState.FLYAWAY) {
        missedCount--;
      }
    }
    return missedCount <= 0;
  }

  public GameInfo getGameInfo() {
    return gameInfo;
  }

  public DucksInfo getDucksInfo() {
    return ducksInfo;
  }

  public ArrayList<DuckHuntListener> getListeners() {
    return listeners;
  }
}
