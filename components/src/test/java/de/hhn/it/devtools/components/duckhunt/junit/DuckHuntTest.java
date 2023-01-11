package de.hhn.it.devtools.components.duckhunt.junit;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.hhn.it.devtools.apis.duckhunt.GameSettingsDescriptor;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.components.duckhunt.DuckHunt;
import de.hhn.it.devtools.components.duckhunt.ScreenDimension;
import java.lang.reflect.Field;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DuckHuntTest {
  DuckHunt game;
  DuckHuntListenerTest testListener;

  @BeforeEach
  void setUp() {
    game = new DuckHunt();
    testListener = new DuckHuntListenerTest();
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void addCallbackTest() {
    assertDoesNotThrow(() -> game.addCallback(testListener));

    assertThrowsExactly(IllegalParameterException.class, () -> game.addCallback(null));
  }

  @Test
  void removeCallbackTest() throws IllegalParameterException {
    game.addCallback(testListener);

    assertDoesNotThrow(() -> game.removeCallback(testListener));

    assertThrowsExactly(IllegalParameterException.class, () -> game.removeCallback(null));
  }

  @Test
  void shootTest()
      throws IllegalParameterException, NoSuchFieldException,
      IllegalAccessException, InterruptedException {
    /*game.addCallback(testListener);
    game.startGame();
    Field ducksField = game.getClass().getDeclaredField("ducks");
    ducksField.setAccessible(true);

    DuckData[] ducks = (DuckData[]) ducksField.get(game);
    game.shoot(ducks[0].getX(), ducks[0].getY());

    Field ammoField = game.getClass().getDeclaredField("ammoCount");
    ammoField.setAccessible(true);
    game.pauseGame();

    assertAll("shoot (hitting) Tests",
        () -> assertEquals(ducks[0].getStatus(), DuckState.SCARRED),
        () -> assertEquals(new GameSettingsDescriptor().getAmmoAmount() - 1, ammoField.get(game))
    );*/
    game.addCallback(testListener);
    game.startGame();
    Thread.sleep(100);
    game.pauseGame();
    Thread.sleep(1000);
    final int ammoCount = testListener.gameInfo.getAmmo();
    assertEquals(-1, testListener.duckHitId);
    game.shoot(testListener.duckPosition.duckData()[0].getX(),
        testListener.duckPosition.duckData()[0].getY());
    Thread.sleep(1000);
    assertEquals(0, testListener.duckHitId);
    assertEquals(ammoCount - 1, testListener.gameInfo.getAmmo());
  }

  @Test
  void shootObstacleTest()
      throws IllegalParameterException, NoSuchFieldException, IllegalAccessException {
    game.addCallback(testListener);
    game.startGame();
    game.shootObstacle();
    Field ammoField = game.getClass().getDeclaredField("ammoCount");
    ammoField.setAccessible(true);
    game.pauseGame();
    assertEquals(new GameSettingsDescriptor().getAmmoAmount() - 1, ammoField.get(game));
  }

  @Test
  void reloadTest() throws IllegalParameterException, InterruptedException {
    game.addCallback(testListener);
    game.shootObstacle();
    game.reload();
    Thread.sleep(1000);
    assertEquals(3, testListener.gameInfo.getAmmo());
  }

  @Test
  void startGameTest() throws IllegalParameterException, InterruptedException {
    game.addCallback(testListener);
    game.startGame();
    final int callCount = testListener.newStateCallCount;
    Thread.sleep(1000);
    assertNotNull(testListener.gameInfo);
    assertNotNull(testListener.duckPosition);
    assertEquals(1, testListener.duckPosition.duckData().length);
    assertTrue(callCount < testListener.newStateCallCount);
  }

  @Test
  void stopGameTest() throws IllegalParameterException, InterruptedException {
    game.addCallback(testListener);
    game.startGame();
    Thread.sleep(1000);
    game.stopGame();
    Thread.sleep(1000);
    int callCount = testListener.newStateCallCount;
    Thread.sleep(1000);
    assertEquals(callCount, testListener.newStateCallCount);
  }

  @Test
  void pauseGameTest() throws IllegalParameterException, InterruptedException {
    game.addCallback(testListener);
    game.startGame();
    Thread.sleep(1000);
    game.pauseGame();
    Thread.sleep(1000);
    int callCount = testListener.newStateCallCount;
    Thread.sleep(1000);
    assertEquals(callCount, testListener.newStateCallCount);
    game.continueGame();
    Thread.sleep(1000);
    game.pauseGame();
    Thread.sleep(1000);
    callCount = testListener.newStateCallCount;
    Thread.sleep(1000);
    assertEquals(callCount, testListener.newStateCallCount);
  }

  @Test
  void continueGameTest() throws IllegalParameterException, InterruptedException {
    game.addCallback(testListener);
    game.startGame();
    Thread.sleep(1000);
    game.pauseGame();
    Thread.sleep(1000);
    int callCount = testListener.newStateCallCount;
    Thread.sleep(1000);
    assertEquals(callCount, testListener.newStateCallCount);
    game.continueGame();
    Thread.sleep(1000);
    game.pauseGame();
    Thread.sleep(1000);
    assertTrue(callCount < testListener.newStateCallCount);
  }

  @Test
  void changeGameSettingsTest()
      throws IllegalParameterException, NoSuchFieldException, IllegalAccessException {
    GameSettingsDescriptor newSettingsDescriptor = new GameSettingsDescriptor(1, 3);
    DuckHunt testGame = new DuckHunt(
        new GameSettingsDescriptor(2, 5),
        new ScreenDimension(500, 500)
    );
    Field f = testGame.getClass().getDeclaredField("gameSettings");
    f.setAccessible(true);
    testGame.changeGameSettings(newSettingsDescriptor);
    assertEquals(newSettingsDescriptor, f.get(testGame));

    assertThrows(IllegalParameterException.class, () -> testGame.changeGameSettings(null));
  }
}