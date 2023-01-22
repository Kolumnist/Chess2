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
import de.hhn.it.devtools.apis.duckhunt.IllegalGameInfoException;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;

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
  private final int gunSpread = 60;  // TODO configure gun spread by screenDimension
  private MpatternGenerator pathGenerator;
  private float deltaTime = 0.016f;
  private final float duckSpeed = 600f;
  private final float velocityToOvercome = 1f;
  DuckHuntGameLoop gameLoop = new DuckHuntGameLoop(this);

  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(DuckHunt.class);

  /**
   * Default constructor with standard game settings.
   */
  public DuckHunt() {
    logger.info("DuckHunt: no params");
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
    logger.info("DuckHunt: gameSettings, screenDimension", gameSettings, screenDimension);
    this.gameSettings = gameSettings;
    this.screenDimension = screenDimension;
    init();
  }

  private void init() {
    this.gameInfo = new GameInfo(0, gameSettings.getAmmoAmount(), 0, 15);
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
   * Notifies all listeners.
   *
   * @param consumer to be run
   */
  private void notifyListeners(Consumer<DuckHuntListener> consumer) {
    for (DuckHuntListener listener : listeners) {
      consumer.accept(listener);
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
    logger.info("shoot: x = {}, y = {}", x, y);
    // TODO ERROR Handling shoot (rework if needed)
    if (x < 0 || x > screenDimension.getWidth() || y < 0 || y > screenDimension.getHeight()) {
      throw new RuntimeException();
    }
    // Junit Test for these Exceptions is inside DuckHuntTest shoot() Test
    if (gameInfo.getState() != GameState.RUNNING) {
      throw new RuntimeException();
    }
    if (getGameInfo().getAmmo() <= 0) {
      return;
    }

    for (DuckData duck : ducks) {
      if (duck.getStatus() == DuckState.DEAD
          || duck.getStatus() == DuckState.FLYAWAY
          || duck.getStatus() == DuckState.SCARRED
          || duck.getStatus() == DuckState.ESCAPED
          || duck.getStatus() == DuckState.FALLING) {
        continue;
      }
      // if amount of the vector between duck and shoot <= gunSpread
      int duckCenterAdjustment = 50;
      if (Math.sqrt(Math.pow(
          duck.getX() - (x + duckCenterAdjustment), 2)
          + Math.pow(duck.getY() - (y + duckCenterAdjustment), 2)) <= gunSpread) {
        duck.setStatus(DuckState.SCARRED);
        gameInfo.setPlayerScore(gameInfo.getPlayerScore() + 1);
        notifyListeners((l) -> {
          try {
            l.duckHit(duck.getId());
          } catch (IllegalDuckIdException e) {
            throw new RuntimeException(e);
          }
        });
      }
    }
    ammoCount--;
    gameInfo.setAmmo(ammoCount);

    notifyListeners((l) -> {
      try {
        l.newState(gameInfo);
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

    notifyListeners((l) -> {
      try {
        l.newState(gameInfo);
      } catch (IllegalGameInfoException e) {
        throw new RuntimeException(e);
      }
    });
  }

  @Override
  public void reload() {
    logger.info("reload: no params");
    ammoCount = gameSettings.getAmmoAmount();
    gameInfo.setAmmo(ammoCount);

    notifyListeners((l) -> {
      try {
        l.newState(gameInfo);
      } catch (IllegalGameInfoException e) {
        throw new RuntimeException(e);
      }
    });
  }

  @Override
  public void startGame() {
    logger.info("startGame: no params");
    newRound();
    gameLoop.startLoop();
    gameInfo.setState(GameState.RUNNING);
    notifyListeners((l) -> {
      try {
        l.newState(gameInfo);
      } catch (IllegalGameInfoException e) {
        throw new RuntimeException(e);
      }
    });
  }

  @Override
  public void stopGame() {
    logger.info("stopGame: no params");
    gameLoop.stopLoop();
    gameInfo.setState(GameState.GAMEOVER);
    notifyListeners((l) -> {
      try {
        l.newState(gameInfo);
      } catch (IllegalGameInfoException e) {
        throw new RuntimeException(e);
      }
    });
  }

  @Override
  public void pauseGame() {
    logger.info("pauseGame: no params");
    gameLoop.pauseLoop();
    gameInfo.setState(GameState.PAUSED);
    notifyListeners((l) -> {
      try {
        l.newState(gameInfo);
      } catch (IllegalGameInfoException e) {
        throw new RuntimeException(e);
      }
    });
  }

  @Override
  public void continueGame() {
    logger.info("continueGame: no params");
    gameLoop.continueLoop();
    gameInfo.setState(GameState.RUNNING);
    notifyListeners((l) -> {
      try {
        l.newState(gameInfo);
      } catch (IllegalGameInfoException e) {
        throw new RuntimeException(e);
      }
    });
  }

  @Override
  public void changeGameSettings(GameSettingsDescriptor gameSettings)
      throws IllegalParameterException {
    logger.info("changeGameSettings: gameSettings", gameSettings);
    if (gameSettings == null) {
      throw new IllegalParameterException();
    }
    this.gameSettings = gameSettings;
  }

  @Override
  public void addCallback(DuckHuntListener listener) throws IllegalParameterException {
    logger.info("addCallback: listener", listener);
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
    logger.info("removeCallback: listener", listener);
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
    logger.trace("updateDucks: no params");
    for (DuckData duck : ducks) {
      if (duck.getStatus() == DuckState.FLYING
          || duck.getStatus() == DuckState.FALLING
          || duck.getStatus() == DuckState.FLYAWAY) {
        // velocity = resolutionCoefficient * speed * deltaTime
        float newVelocity = 0.3f * duckSpeed * deltaTime;
        duck.setVelocity(duck.getVelocity() + newVelocity);
      }
      if (duck.getStatus() == DuckState.FALLING && duck.getY() > screenDimension.getHeight()) {
        duck.setStatus(DuckState.DEAD);
      }
      if (duck.getStatus() == DuckState.FLYAWAY && duck.getY() < 0) {
        duck.setStatus(DuckState.ESCAPED);
      }
      switch (duck.getStatus()) {
        case FLYING -> moveDuck(duck);
        case SCARRED -> new Timer().schedule(new TimerTask() {
          @Override
          public void run() {
            duck.setStatus(DuckState.FALLING);
          }
        }, 1000);
        case FALLING -> dropDuck(duck);
        case FLYAWAY -> ascendDuck(duck);
        case DEAD -> { /*dead is not used in this method*/ }
        case ESCAPED -> { /*escaped is not used in this method*/ }
        default -> throw new IllegalStateException("Unexpected value: " + duck.getStatus());
      }
    }
    ducksInfo = new DucksInfo(ducks);
  }

  /**
   * Drops the duck after it has been shot.
   *
   * @param duck that shall be dropped
   */
  private void dropDuck(DuckData duck) {
    logger.trace("dropDuck: no params");
    float velocity = duck.getVelocity();
    if (velocity > velocityToOvercome) { // if true duck can be moved to next position
      //TODO anpassen der Pixel beim Droppen
      duck.setY(duck.getY() + 1);
      duck.setVelocity(velocity - velocityToOvercome);
    }
  }

  /**
   * Ascends the duck after it is flyaway.
   *
   * @param duck that shall ascend
   */
  private void ascendDuck(DuckData duck) {
    logger.trace("ascendDuck: no params");
    float velocity = duck.getVelocity();
    if (velocity > velocityToOvercome) { // if true duck can be moved to next position
      //TODO anpassen der Pixel beim Wegfliegen (analog drop)
      duck.setY(duck.getY() - screenDimension.getHeight() /  30);
      duck.setVelocity(velocity - velocityToOvercome);
    }
  }

  private void moveDuck(DuckData duck) {
    logger.trace("moveDuck: no params");
    float velocity = duck.getVelocity();
    if (velocity > velocityToOvercome) { // if true duck can be moved to next position
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
      duck.setVelocity(velocity - velocityToOvercome);
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
    logger.trace("checkRoundComplete: no params");
    int completeCount = 0;
    for (DuckData duck : ducks) {
      if (duck.getStatus() == DuckState.DEAD || duck.getStatus() == DuckState.ESCAPED) {
        completeCount++;
      }
    }
    return completeCount == ducks.length;
  }

  /**
   * Resets the ducks, ammo and increments round.
   */
  public void newRound() {
    logger.trace("newRound: no params");
    gameInfo.setRound(gameInfo.getRound() + 1);
    reload();
    calculateNewDuckPaths();
    for (DuckData duck : ducks) {
      duck.setStatus(DuckState.FLYING);
      duck.setX(pathGenerator.getStartingPos(duck.getId()).getX());
      duck.setY(pathGenerator.getStartingPos(duck.getId()).getY());
    }
  }

  /**
   * Checks if GameOver requirements are meet.
   *
   * @return true if game is over
   */
  public boolean checkGameOver() {
    for (DuckData duck : ducks) {
      if (duck.getStatus() == DuckState.ESCAPED) {
        gameInfo.setMissedCount(gameInfo.getMissedCount() - 1);
      }
    }
    return gameInfo.getMissedCount() <= 0;
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
