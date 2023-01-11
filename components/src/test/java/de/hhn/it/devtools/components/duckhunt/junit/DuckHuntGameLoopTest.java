package de.hhn.it.devtools.components.duckhunt.junit;

import de.hhn.it.devtools.components.duckhunt.DuckHunt;
import de.hhn.it.devtools.components.duckhunt.DuckHuntGameLoop;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.concurrent.Semaphore;

import static org.junit.jupiter.api.Assertions.*;

class DuckHuntGameLoopTest {
  DuckHunt game;
  DuckHuntGameLoop gameLoop;

  @BeforeEach
  void setUp() {
    game = new DuckHunt();
    gameLoop = new DuckHuntGameLoop(game);
  }

  @AfterEach
  void tearDown() {
    if (gameLoop.isAlive()) {
      gameLoop.stopLoop();
    }
  }

  @Test
  void startLoop() {
    gameLoop.startLoop();
    assertTrue(gameLoop.isAlive());
  }

  @Test
  void stopLoop() {
    game.startGame();
    game.stopGame(); // calls stopLoop
    assertFalse(gameLoop.isAlive());

    assertThrowsExactly(RuntimeException.class, () -> gameLoop.stopLoop());
  }

  @Test
  void continueLoop() {
    game.startGame();
    assertThrowsExactly(RuntimeException.class, () -> gameLoop.continueLoop());
  }

  @Test
  void pauseLoop() {
    game.startGame();
    game.pauseGame();
    assertThrowsExactly(RuntimeException.class, () -> gameLoop.pauseLoop());
  }

  @Test
  void getDeltaTime() {
    assertInstanceOf(Float.class, gameLoop.getDeltaTime());
  }

  @Test
  void getTps() {
    assertInstanceOf(Integer.class, gameLoop.getTps());
  }
}