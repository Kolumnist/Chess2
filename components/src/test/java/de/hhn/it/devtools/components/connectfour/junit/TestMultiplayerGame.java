package de.hhn.it.devtools.components.connectfour.junit;

import de.hhn.it.devtools.apis.connectfour.exceptions.IllegalOperationException;
import de.hhn.it.devtools.apis.connectfour.helper.Profile;
import de.hhn.it.devtools.components.connectfour.provider.helper.MultiplayerGame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test multiplayer game.")

public class TestMultiplayerGame {

  MultiplayerGame multiplayerGame;
  Profile alice;
  Profile bob;

  @BeforeEach
  void setup() {
    alice = new Profile("Alice");
    bob = new Profile("Bob");
    multiplayerGame = new MultiplayerGame(alice, bob, true);
    multiplayerGame.setListener(new DummyCallback());
    multiplayerGame.start();
  }

  @Test
  @DisplayName("Test initial player statistics.")
  void testInitialPlayerStatistics() {
    if (alice.getMultiplayerLoose() != 0 || alice.getMultiplayerWin() != 0 ||
        alice.getMultiplayerDraw() != 0) {
      // Invalid player statistics.
      Assertions.fail();
    }
    if (bob.getMultiplayerLoose() != 0 || bob.getMultiplayerWin() != 0 ||
        bob.getMultiplayerDraw() != 0) {
      // Invalid player statistics.
      Assertions.fail();
    }
  }

  @Test
  @DisplayName("Test lost.")
  void testLost() {
    try {
      multiplayerGame.placeDiscInColumn(0); // RED
      multiplayerGame.placeDiscInColumn(1); // GREEN
      multiplayerGame.placeDiscInColumn(2); // RED
      multiplayerGame.placeDiscInColumn(1); // GREEN
      multiplayerGame.placeDiscInColumn(3); // RED
      multiplayerGame.placeDiscInColumn(1); // GREEN
      multiplayerGame.placeDiscInColumn(4); // RED
      multiplayerGame.placeDiscInColumn(1); // GREEN
      // Bow won, Alice lost.
    } catch (IllegalOperationException e) {
      Assertions.fail();
    }
    if (alice.getMultiplayerLoose() != 1 || alice.getMultiplayerWin() != 0 ||
        alice.getMultiplayerDraw() != 0) {
      // Invalid player statistics.
      Assertions.fail();
    }
    if (bob.getMultiplayerLoose() != 0 || bob.getMultiplayerWin() != 1 ||
        bob.getMultiplayerDraw() != 0) {
      // Invalid player statistics.
      Assertions.fail();
    }
  }

  @Test
  @DisplayName("Test won.")
  void testWon() {
    try {
      multiplayerGame.placeDiscInColumn(0); // RED
      multiplayerGame.placeDiscInColumn(1); // GREEN
      multiplayerGame.placeDiscInColumn(0); // RED
      multiplayerGame.placeDiscInColumn(1); // GREEN
      multiplayerGame.placeDiscInColumn(0); // RED
      multiplayerGame.placeDiscInColumn(1); // GREEN
      multiplayerGame.placeDiscInColumn(0); // RED
      // Alice won, Bob lost.
    } catch (IllegalOperationException e) {
      Assertions.fail();
    }
    if (alice.getMultiplayerLoose() != 0 || alice.getMultiplayerWin() != 1 ||
        alice.getMultiplayerDraw() != 0) {
      // Invalid player statistics.
      Assertions.fail();
    }
    if (bob.getMultiplayerLoose() != 1 || bob.getMultiplayerWin() != 0 ||
        bob.getMultiplayerDraw() != 0) {
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
          multiplayerGame.placeDiscInColumn(column);
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
          multiplayerGame.placeDiscInColumn(column);
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
        multiplayerGame.placeDiscInColumn(6);
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
    if (alice.getMultiplayerLoose() != 0 || alice.getMultiplayerWin() != 0 ||
        alice.getMultiplayerDraw() != 1) {
      // Invalid player statistics.
      Assertions.fail();
    }
    if (bob.getMultiplayerLoose() != 0 || bob.getMultiplayerWin() != 0 ||
        bob.getMultiplayerDraw() != 1) {
      // Invalid player statistics.
      Assertions.fail();
    }
  }
}
