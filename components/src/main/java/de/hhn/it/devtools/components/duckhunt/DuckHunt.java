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
public class DuckHunt implements Runnable, DuckHuntService {
  private GameSettingsDescriptor gameSettings;
  private ScreenDimension screenDimension;
  private ArrayList<DuckHuntListener> listeners;
  private GameInfo gameInfo;
  private DuckData[] ducks;
  private int ammoCount;
  private int duckCount;
  private int missedCount = 15;
  private final int gunSpread = 5;
  private final Semaphore semaphore = new Semaphore(1);
  private MpatternGenerator pathGenerator;
  private float deltaTime = 0.016f;
  private final float duckSpeed = 1f;

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
      if (duck.getStatus() == DuckState.DEAD ||
              duck.getStatus() == DuckState.FLYAWAY ||
              duck.getStatus() == DuckState.SCARRED ) {
        continue;
      }
      if (duck.getStatus() == DuckState.FALLING && duck.getY() > screenDimension.getHeight()) {
        duck.setStatus(DuckState.DEAD);
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

  /**
   * Checks the states of the ducks and updates their position accordingly.
   */
  private void updateDucks() {
    for (DuckData duck : ducks) {
      if (duck.getStatus() == DuckState.FLYING || duck.getStatus() == DuckState.FALLING) {
        // velocity = resolutionCoefficient * speed * deltaTime
        float newVelocity = 0.3f * duckSpeed * deltaTime;
        duck.setVelocity(duck.getVelocity() + newVelocity);
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
    calculateNewDuckPaths();
    for (DuckData duck : ducks) {
      duck.setStatus(DuckState.FLYING);
      duck.setX(pathGenerator.getStartingPos(duck.getId()).getX());
      duck.setY(pathGenerator.getStartingPos(duck.getId()).getY());
    }
  }

  @Override
  public void startGame() {
    logger.info("startGame(): no params");
    gameInfo.setState(GameState.RUNNING);
    new Thread(this).start();
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
    if (gameInfo.getState() == GameState.PAUSED) {
      semaphore.release();
    }
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
    try {
      semaphore.tryAcquire(10, TimeUnit.SECONDS);
      gameInfo.setState(GameState.PAUSED);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
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
    gameInfo.setState(GameState.RUNNING);
    semaphore.release();
    listeners.forEach(
            listener -> {
              try {
                listener.newState(gameInfo);
              } catch (IllegalGameInfoException e) {
                throw new RuntimeException(e);
              }
            });
  }

  private boolean checkGameOver() {
    for (DuckData duck : ducks) {
      if (duck.getStatus() == DuckState.FLYAWAY) {
        missedCount--;
      }
    }
    return missedCount <= 0;
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

  @Override
  public void run() {
    logger.info("run(): no params");
    float sec = 0f;
    int fps = 0;
    newRound();
    // main game loop
    while (gameInfo.getState() == GameState.RUNNING || gameInfo.getState() == GameState.PAUSED) {
      try {
        // waits if game is paused
        semaphore.tryAcquire(Integer.MAX_VALUE, TimeUnit.DAYS);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      final long startTime = System.currentTimeMillis();

      fps++;
      sec += deltaTime;
      if (sec >= 1) {
        //System.out.println("FPS: " + fps);
        fps = 0;
        sec = 0;
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
        if (checkGameOver()) {
          stopGame();
        } else {
          newRound();
          try {
            // timeout between rounds
            Thread.sleep(4000);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        }
      }

      semaphore.release();
      deltaTime = (System.currentTimeMillis() - startTime) / 1000.0f;
      deltaTime += 0.02f;
    }
  }
}
