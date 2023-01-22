package de.hhn.it.devtools.components.game2048.junit;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.game2048.MovingDirection;
import de.hhn.it.devtools.apis.game2048.Position;
import de.hhn.it.devtools.components.game2048.provider.ImplementationGame2048Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test für gute Beispiele des ImplementationGame2048Service. ")
public class ImplementationGame2048ServiceTestGoodCases {

  DummyListener listener;
  private ImplementationGame2048Service correctService = new ImplementationGame2048Service();
  private ImplementationGame2048Service service = new ImplementationGame2048Service();

  @BeforeEach
  void initialiseServices() {
    listener = new DummyListener();
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        correctService.getFreelist().add(new Position(i, j));
        service.getFreelist().add(new Position(i, j));
      }
    }
    correctService.getGameBoard().clear();
    service.getGameBoard().clear();

  }

  private Method getPredictableMoveAllBlocks() throws NoSuchMethodException {
    Method predictableMoveAllBlocks = ImplementationGame2048Service.class.getDeclaredMethod("predictableMoveAllBlocks", MovingDirection.class);
    predictableMoveAllBlocks.setAccessible(true);
    return predictableMoveAllBlocks;
  }

  private Field getField(String name) throws NoSuchFieldException {
    Field declaredField = ImplementationGame2048Service.class.getDeclaredField(name);
    declaredField.setAccessible(true);
    return declaredField;
  }

  @Test
  @DisplayName("Tests the movement directions on an empty board. ")
  void testMoveEmptyBoard() {
    assertAll(
            () -> assertDoesNotThrow(() -> Objects.requireNonNull(getPredictableMoveAllBlocks()).invoke(service, MovingDirection.left)),
            () -> assertDoesNotThrow(() -> Objects.requireNonNull(getPredictableMoveAllBlocks()).invoke(service, MovingDirection.right)),
            () -> assertDoesNotThrow(() -> Objects.requireNonNull(getPredictableMoveAllBlocks()).invoke(service, MovingDirection.down)),
            () -> assertDoesNotThrow(() -> Objects.requireNonNull(getPredictableMoveAllBlocks()).invoke(service, MovingDirection.up)),
            () -> assertEquals(correctService.getGameBoard(), service.getGameBoard()));
  }

  @Test
  @DisplayName("Tests the movement directions on a board with two blocks. ")
  void testMoveAllDirections() {
    try {
      service.addBlock(new Position(2, 2), 2);
      correctService.addBlock(new Position(3, 3), 2);
    } catch (IllegalParameterException e) {
      e.printStackTrace();
    }
    try {
      Objects.requireNonNull(getPredictableMoveAllBlocks()).invoke(service, MovingDirection.left);
      Objects.requireNonNull(getPredictableMoveAllBlocks()).invoke(service, MovingDirection.right);
      Objects.requireNonNull(getPredictableMoveAllBlocks()).invoke(service, MovingDirection.down);
      Objects.requireNonNull(getPredictableMoveAllBlocks()).invoke(service, MovingDirection.up);
    } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
      e.printStackTrace();
    }
    assertEquals(correctService.getGameBoard(), service.getGameBoard());
  }

  @Test
  @DisplayName("Tests the movement direction up. ")
  void testMoveUp() {
    assertAll(
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(2, 3), 2)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(2, 2), 2)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(2, 1), 2)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(2, 0), 2)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(1, 2), 2)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(1, 1), 2)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(1, 0), 2)),
            () -> assertDoesNotThrow(() -> correctService.addBlock(new Position(2, 3), 4)),
            () -> assertDoesNotThrow(() -> correctService.addBlock(new Position(2, 2), 4)),
            () -> assertDoesNotThrow(() -> correctService.addBlock(new Position(1, 3), 4)),
            () -> assertDoesNotThrow(() -> correctService.addBlock(new Position(1, 2), 2)),
            () -> assertDoesNotThrow(() -> Objects.requireNonNull(getPredictableMoveAllBlocks()).invoke(service, MovingDirection.up)),
            () -> assertTrue(correctService.getGameBoard().containsAll(service.getGameBoard())),
            () -> assertTrue(service.getGameBoard().containsAll(correctService.getGameBoard())),
            () -> assertTrue(correctService.getFreelist().containsAll(service.getFreelist())),
            () -> assertTrue(service.getFreelist().containsAll(correctService.getFreelist())));
  }

  @Test
  @DisplayName("Tests the movement direction down. ")
  void testMoveDown() {
    assertAll(
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(2, 3), 2)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(2, 2), 2)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(2, 1), 2)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(2, 0), 2)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(1, 3), 2)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(1, 2), 2)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(1, 1), 2)),
            () -> assertDoesNotThrow(() -> correctService.addBlock(new Position(2, 1), 4)),
            () -> assertDoesNotThrow(() -> correctService.addBlock(new Position(2, 0), 4)),
            () -> assertDoesNotThrow(() -> correctService.addBlock(new Position(1, 1), 2)),
            () -> assertDoesNotThrow(() -> correctService.addBlock(new Position(1, 0), 4)),
            () -> assertDoesNotThrow(() -> Objects.requireNonNull(getPredictableMoveAllBlocks()).invoke(service, MovingDirection.down)),
            () -> assertTrue(correctService.getGameBoard().containsAll(service.getGameBoard())),
            () -> assertTrue(service.getGameBoard().containsAll(correctService.getGameBoard())),
            () -> assertTrue(correctService.getFreelist().containsAll(service.getFreelist())),
            () -> assertTrue(service.getFreelist().containsAll(correctService.getFreelist())));
  }

  @Test
  @DisplayName("Tests the movement direction right. ")
  void testMoveRight() {
    assertAll(
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(3, 1), 2)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(2, 1), 2)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(1, 1), 2)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(0, 1), 2)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(2, 2), 2)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(1, 2), 2)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(0, 2), 2)),
            () -> assertDoesNotThrow(() -> correctService.addBlock(new Position(3, 2), 4)),
            () -> assertDoesNotThrow(() -> correctService.addBlock(new Position(2, 2), 4)),
            () -> assertDoesNotThrow(() -> correctService.addBlock(new Position(2, 1), 2)),
            () -> assertDoesNotThrow(() -> correctService.addBlock(new Position(3, 1), 4)),
            () -> assertDoesNotThrow(() -> Objects.requireNonNull(getPredictableMoveAllBlocks()).invoke(service, MovingDirection.right)),
            () -> assertTrue(correctService.getGameBoard().containsAll(service.getGameBoard())),
            () -> assertTrue(service.getGameBoard().containsAll(correctService.getGameBoard())),
            () -> assertTrue(correctService.getFreelist().containsAll(service.getFreelist())),
            () -> assertTrue(service.getFreelist().containsAll(correctService.getFreelist())));
  }

  @Test
  @DisplayName("Tests the movement direction left. ")
  void testMoveLeft() {
    assertAll(
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(3, 1), 2)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(2, 1), 2)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(1, 1), 2)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(0, 1), 2)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(3, 2), 2)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(2, 2), 2)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(1, 2), 2)),
            () -> assertDoesNotThrow(() -> correctService.addBlock(new Position(1, 2), 4)),
            () -> assertDoesNotThrow(() -> correctService.addBlock(new Position(0, 2), 4)),
            () -> assertDoesNotThrow(() -> correctService.addBlock(new Position(1, 1), 2)),
            () -> assertDoesNotThrow(() -> correctService.addBlock(new Position(0, 1), 4)),
            () -> assertDoesNotThrow(() -> Objects.requireNonNull(getPredictableMoveAllBlocks()).invoke(service, MovingDirection.left)),
            () -> assertTrue(correctService.getGameBoard().containsAll(service.getGameBoard())),
            () -> assertTrue(service.getGameBoard().containsAll(correctService.getGameBoard())),
            () -> assertTrue(correctService.getFreelist().containsAll(service.getFreelist())),
            () -> assertTrue(service.getFreelist().containsAll(correctService.getFreelist())));
  }

  @Test
  @DisplayName("Tests if callbacks can be added and if they can be removed")
  void testAddAndRemoveCallback() {

    assertAll(() -> assertDoesNotThrow(() -> service.addCallback(listener)),
            () -> assertThrows(IllegalParameterException.class, () -> service.addCallback(listener)),
            () -> assertDoesNotThrow(() -> service.removeCallback(listener)),
            () -> assertThrows(IllegalParameterException.class, () -> service.removeCallback(listener)));

  }

  @Test
  @DisplayName("Test if Listener get notified after initialisation() and moveAllBlocks(...)")
  void testListener() {
    try {
      service.addCallback(listener);
      service.initialisation();
      service.moveAllBlocks(MovingDirection.up);
      service.moveAllBlocks(MovingDirection.left);
      service.moveAllBlocks(MovingDirection.right);
    } catch (IllegalParameterException e) {
      e.printStackTrace();
    }
    try {
      service.removeCallback(listener);
      service.initialisation();
      service.moveAllBlocks(MovingDirection.up);
      service.moveAllBlocks(MovingDirection.left);
      service.moveAllBlocks(MovingDirection.right);
    } catch (IllegalParameterException e) {
      e.printStackTrace();
    }
    assertEquals(4, listener.getCountCalls());
  }

  @Test
  @DisplayName("Test if win condition triggers correctly")
  void testGameWon() {
    assertAll(
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(1, 1), 2048)),
            () -> assertDoesNotThrow(() -> service.moveAllBlocks(MovingDirection.left)),
            () -> assertTrue((boolean) getField("gameWon").get(service))
    );
  }

  @Test
  @DisplayName("Test if lose condition triggers correctly")
  void testGameLost() {
    assertAll(
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(3, 1), 2)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(2, 1), 4)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(1, 1), 6)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(0, 1), 8)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(3, 2), 10)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(2, 2), 12)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(1, 2), 14)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(0, 2), 16)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(3, 3), 18)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(2, 3), 20)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(1, 3), 22)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(0, 3), 24)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(3, 0), 26)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(2, 0), 28)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(1, 0), 30)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(0, 0), 32)),
            () -> assertDoesNotThrow(() -> service.moveAllBlocks(MovingDirection.left)),
            () -> assertTrue((boolean) getField("gameLost").get(service))
    );
  }


  @Test
  @DisplayName("Test if lose condition triggers correctly and freelist gets correctly" +
               " updated one move before the game is lost")
  void testFreelistAndGameNotLost() {
    assertAll(
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(3, 1), 32)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(2, 1), 4)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(1, 1), 6)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(0, 1), 8)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(3, 2), 10)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(2, 2), 12)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(1, 2), 14)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(0, 2), 16)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(3, 3), 18)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(2, 3), 20)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(1, 3), 22)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(0, 3), 24)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(3, 0), 26)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(2, 0), 28)),
            () -> assertDoesNotThrow(() -> service.addBlock(new Position(1, 0), 30)),
            () -> assertDoesNotThrow(() -> correctService.addBlock(new Position(3, 1), 32)),
            () -> assertDoesNotThrow(() -> correctService.addBlock(new Position(2, 1), 4)),
            () -> assertDoesNotThrow(() -> correctService.addBlock(new Position(1, 1), 6)),
            () -> assertDoesNotThrow(() -> correctService.addBlock(new Position(0, 1), 8)),
            () -> assertDoesNotThrow(() -> correctService.addBlock(new Position(3, 2), 10)),
            () -> assertDoesNotThrow(() -> correctService.addBlock(new Position(2, 2), 12)),
            () -> assertDoesNotThrow(() -> correctService.addBlock(new Position(1, 2), 14)),
            () -> assertDoesNotThrow(() -> correctService.addBlock(new Position(0, 2), 16)),
            () -> assertDoesNotThrow(() -> correctService.addBlock(new Position(3, 3), 18)),
            () -> assertDoesNotThrow(() -> correctService.addBlock(new Position(2, 3), 20)),
            () -> assertDoesNotThrow(() -> correctService.addBlock(new Position(1, 3), 22)),
            () -> assertDoesNotThrow(() -> correctService.addBlock(new Position(0, 3), 24)),
            () -> assertDoesNotThrow(() -> correctService.addBlock(new Position(3, 0), 26)),
            () -> assertDoesNotThrow(() -> correctService.addBlock(new Position(2, 0), 28)),
            () -> assertDoesNotThrow(() -> correctService.addBlock(new Position(1, 0), 30)),
            () -> assertDoesNotThrow(() -> correctService.addBlock(new Position(0, 0), 2)),
            () -> assertDoesNotThrow(() -> service.moveAllBlocks(MovingDirection.left)),
            () -> assertEquals(getField("freelist").get(service), getField("freelist").get(correctService)),
            () -> assertFalse((boolean) getField("gameLost").get(service))
    );
  }

  @Test
  @DisplayName("Test if valueIndex gets reset to value zero ( after reset valueIndex ++) if value of valueIndex >= valueArray.length")
  void testValueIndex() throws NoSuchFieldException, IllegalAccessException {
    int valueIndex1 = (int) getField("valueIndex").get(service);
    int valueIndex2 = 11;
    assertAll(
            () -> assertEquals(0, valueIndex1),
            () -> getField("valueIndex").set(service, valueIndex2),
            () -> assertEquals((int) getField("valueIndex").get(service), valueIndex2),
            () -> assertDoesNotThrow(() -> service.moveAllBlocks(MovingDirection.up)),
            () -> assertEquals(1, (int) getField("valueIndex").get(service)));

  }
}