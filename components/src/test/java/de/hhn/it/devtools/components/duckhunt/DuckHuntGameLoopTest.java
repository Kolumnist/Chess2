package de.hhn.it.devtools.components.duckhunt;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    gameLoop.stopLoop();
  }

  @Test
  void startLoop() {
  }

  @Test
  void stopLoop() {
  }

  @Test
  void continueLoop() {
  }

  @Test
  void pauseLoop() {
  }

  @Test
  void getDeltaTime() {
  }

  @Test
  void getTps() {
  }
}