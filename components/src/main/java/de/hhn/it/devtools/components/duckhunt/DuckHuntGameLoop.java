package de.hhn.it.devtools.components.duckhunt;

import de.hhn.it.devtools.apis.duckhunt.GameState;
import de.hhn.it.devtools.apis.duckhunt.IllegalDuckPositionException;
import de.hhn.it.devtools.apis.duckhunt.IllegalGameInfoException;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Handles the GameLoop.
 */
public class DuckHuntGameLoop extends Thread {
  private DuckHunt game;
  private float deltaTime = 0.016f;
  private int tps = 0; // Ticks per Second
  private int ticks = 0;
  private Timer timer;
  private final Semaphore semaphore = new Semaphore(1);
  private boolean isRunning = false;
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(DuckHuntGameLoop.class);

  /**
   * Constructor.
   *
   * @param game that shall be run
   */
  public DuckHuntGameLoop(DuckHunt game) {
    this.game = game;
    this.timer = new Timer();

    // setup scheduled task for calculating tps
    timer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        tps = ticks;
        ticks = 0;
      }
    }, Calendar.getInstance().getTime(), 1000);
  }

  /**
   * Starts the game loop.
   */
  public void startLoop() {
    logger.info("starLoop(): no params");
    if (this.isAlive()) {
      throw new RuntimeException("DuckHuntGameLoop can't be started if already running");
    }
    isRunning = true;
    this.start();
  }

  /**
   * Stops the game loop.
   */
  public void stopLoop() {
    logger.info("stopLoop(): no params");
    if (!this.isAlive()) {
      throw new RuntimeException("DuckHuntGameLoop can't be stopped if not running");
    }
    isRunning = false;
    semaphore.release();
    timer.cancel();
  }

  /**
   * Continues the game loop.
   */
  public void continueLoop() {
    logger.info("continueLoop(): no params");
    if (game.getGameInfo().getState() != GameState.PAUSED) {
      throw new RuntimeException("DuckHuntGameLoop can't be continued if not paused");
    }
    semaphore.release();
  }

  /**
   * Pauses the game loop.
   */
  public void pauseLoop() {
    logger.info("pauseLoop(): no params");
    if (game.getGameInfo().getState() != GameState.RUNNING) {
      throw new RuntimeException("DuckHuntGameLoop can't be paused if not running");
    }
    try {
      semaphore.tryAcquire(10, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public float getDeltaTime() {
    return deltaTime;
  }

  public int getTps() {
    return tps;
  }

  @Override
  public void run() {
    while (isRunning) {
      try {
        // waits if game is paused
        semaphore.tryAcquire(Integer.MAX_VALUE, TimeUnit.DAYS);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      final long startTime = System.currentTimeMillis();

      game.updateDucks();

      if (game.checkRoundComplete()) {
        if (game.checkGameOver()) {
          game.stopGame();
        } else {
          game.newRound();
        }
      }

      game.getListeners().forEach(
          listener -> {
            try {
              listener.newState(game.getGameInfo());
              listener.newDuckPosition(game.getDucksInfo());
            } catch (IllegalGameInfoException e) {
              throw new RuntimeException(e);
            } catch (IllegalDuckPositionException e) {
              throw new RuntimeException(e);
            }
          });

      semaphore.release();
      deltaTime = (System.currentTimeMillis() - startTime) / 1000.0f;
      deltaTime += 0.02f;
      ticks++;
    }
  }
}
