package de.hhn.it.devtools.components.connectfour.junit;

import de.hhn.it.devtools.apis.connectfour.exceptions.IllegalOperationException;
import de.hhn.it.devtools.apis.connectfour.helper.Profile;
import de.hhn.it.devtools.components.connectfour.provider.helper.SingleplayerGame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test singleplayer game.")
public class TestSingleplayerGame {
  SingleplayerGame singleplayerGame;
  Profile alice;

  @BeforeEach
  void setup() {
    alice = new Profile("Alice");
    singleplayerGame = new SingleplayerGame(alice, true);
    singleplayerGame.setListener(new DummyCallback());
    singleplayerGame.start();
  }

  @Test
  @DisplayName("Test initial player statistics.")
  void testInitialPlayerStatistics() {
    if (alice.getMultiplayerLoose() != 0 || alice.getMultiplayerWin() != 0 ||
        alice.getMultiplayerDraw() != 0) {
      // Invalid player statistics.
      Assertions.fail();
    }
  }

  @Test
  @DisplayName("Test lost.")
  void testLost() {
    try {
      singleplayerGame.placeDiscInColumn(0); // RED
      singleplayerGame.placeDiscInColumn(1); // GREEN
      singleplayerGame.placeDiscInColumn(2); // RED
      singleplayerGame.placeDiscInColumn(1); // GREEN
      singleplayerGame.placeDiscInColumn(3); // RED
      singleplayerGame.placeDiscInColumn(1); // GREEN
      singleplayerGame.placeDiscInColumn(0); // RED
      singleplayerGame.placeDiscInColumn(1); // GREEN
      // Computer won, Alice lost.
    } catch (IllegalOperationException e) {
      Assertions.fail();
    }
    if (alice.getSingleplayerLoose() != 1 || alice.getSingleplayerWin() != 0 ||
        alice.getSingleplayerDraw() != 0) {
      // Invalid player statistics.
      Assertions.fail();
    }
  }

  @Test
  @DisplayName("Test won.")
  void testWon() {
    try {
      singleplayerGame.placeDiscInColumn(0); // RED
      singleplayerGame.placeDiscInColumn(1); // GREEN
      singleplayerGame.placeDiscInColumn(0); // RED
      singleplayerGame.placeDiscInColumn(1); // GREEN
      singleplayerGame.placeDiscInColumn(0); // RED
      singleplayerGame.placeDiscInColumn(1); // GREEN
      singleplayerGame.placeDiscInColumn(0); // RED
      // Alice won, Computer lost.
    } catch (IllegalOperationException e) {
      Assertions.fail();
    }
    if (alice.getSingleplayerLoose() != 0 || alice.getSingleplayerWin() != 1 ||
        alice.getSingleplayerDraw() != 0) {
      // Invalid player statistics.
      Assertions.fail();
    }
  }

  @Test
  @DisplayName("Test draw.")
  void testDraw() {
    try {
      for (int row = 0; row < 6; row++) {
        for (int column = 0; column < 3; column++) {
          singleplayerGame.placeDiscInColumn(column);
        }
      }
      /*
      o x o
      x o x
      o x o
      x o x
      o x o
      x o x
       */
      for (int row = 0; row < 6; row++) {
        for (int column = 3; column < 6; column++) {
          singleplayerGame.placeDiscInColumn(column);
        }
      }
      /*
      o x o o x o
      x o x x o x
      o x o o x o
      x o x x o x
      o x o o x o
      x o x x o x
       */
      for (int row = 0; row < 6; row++) {
        singleplayerGame.placeDiscInColumn(6);
      }
      /*
      o x o o x o o
      x o x x o x x
      o x o o x o o
      x o x x o x x
      o x o o x o o
      x o x x o x x
       */
    } catch (IllegalOperationException e) {
      Assertions.fail();
    }
    if (alice.getSingleplayerLoose() != 0 || alice.getSingleplayerWin() != 0 ||
        alice.getSingleplayerDraw() != 1) {
      // Invalid player statistics.
      Assertions.fail();
    }
  }
}
