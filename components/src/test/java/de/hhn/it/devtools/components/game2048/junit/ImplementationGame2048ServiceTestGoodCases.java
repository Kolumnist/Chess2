package de.hhn.it.devtools.components.game2048.junit;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.game2048.MovingDirection;
import de.hhn.it.devtools.apis.game2048.Position;
import de.hhn.it.devtools.components.game2048.provider.ImplementationGame2048Service;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test fÃ¼r gute Beispiele des ImplementationGame2048Service ")
public class ImplementationGame2048ServiceTestGoodCases {

  void setUp(ImplementationGame2048Service correctService, ImplementationGame2048Service service) {
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        correctService.getFreelist().add(new Position(i, j));
        service.getFreelist().add(new Position(i, j));
      }
    }
    correctService.getGameBoard().clear();
    service.getGameBoard().clear();
  }

  @Test
  void testMoveEmptyBoard() {
    ImplementationGame2048Service correctService = new ImplementationGame2048Service();
    ImplementationGame2048Service service = new ImplementationGame2048Service();
    setUp(correctService, service);
    try {
      service.predictableMoveAllBlocks(MovingDirection.left);
      service.predictableMoveAllBlocks(MovingDirection.right);
      service.predictableMoveAllBlocks(MovingDirection.down);
      service.predictableMoveAllBlocks(MovingDirection.up);
    } catch (IllegalParameterException e) {
      e.printStackTrace();
    }
    assertEquals(correctService.getGameBoard(), service.getGameBoard());
  }

  @Test
  void testMoveAllDirections() {
    ImplementationGame2048Service correctService = new ImplementationGame2048Service();
    ImplementationGame2048Service service = new ImplementationGame2048Service();
    setUp(correctService, service);
    try {
      service.addBlock(new Position(2, 2), 2);
      correctService.addBlock(new Position(3, 3), 2);
    } catch (IllegalParameterException e) {
      e.printStackTrace();
    }
    try {
      service.predictableMoveAllBlocks(MovingDirection.left);
      service.predictableMoveAllBlocks(MovingDirection.right);
      service.predictableMoveAllBlocks(MovingDirection.down);
      service.predictableMoveAllBlocks(MovingDirection.up);
    } catch (IllegalParameterException e) {
      e.printStackTrace();
    }
    assertEquals(correctService.getGameBoard(), service.getGameBoard());
  }

  @Test
  void testMoveUp() {
    ImplementationGame2048Service correctService = new ImplementationGame2048Service();
    ImplementationGame2048Service service = new ImplementationGame2048Service();
    setUp(correctService, service);
    boolean thrown = false;
    try {
      service.addBlock(new Position(2, 3), 2);
      service.addBlock(new Position(2, 2), 2);
      service.addBlock(new Position(2, 1), 2);
      service.addBlock(new Position(2, 0), 2);
      service.addBlock(new Position(1, 2), 2);
      service.addBlock(new Position(1, 1), 2);
      service.addBlock(new Position(1, 0), 2);
      correctService.addBlock(new Position(2, 3), 4);
      correctService.addBlock(new Position(2, 2), 4);
      correctService.addBlock(new Position(1, 3), 4);
      correctService.addBlock(new Position(1, 2), 2);
      service.predictableMoveAllBlocks(MovingDirection.up);
    } catch (IllegalParameterException e) {
      thrown = true;
    }
    assertFalse(thrown);
    assertTrue(correctService.getGameBoard().containsAll(service.getGameBoard()));
    assertTrue(service.getGameBoard().containsAll(correctService.getGameBoard()));
    assertTrue(correctService.getFreelist().containsAll(service.getFreelist()));
    assertTrue(service.getFreelist().containsAll(correctService.getFreelist()));
  }

  @Test
  void testMoveDown() {
    ImplementationGame2048Service correctService = new ImplementationGame2048Service();
    ImplementationGame2048Service service = new ImplementationGame2048Service();
    setUp(correctService, service);
    boolean thrown = false;
    try {
      service.addBlock(new Position(2, 3), 2);
      service.addBlock(new Position(2, 2), 2);
      service.addBlock(new Position(2, 1), 2);
      service.addBlock(new Position(2, 0), 2);
      service.addBlock(new Position(1, 3), 2);
      service.addBlock(new Position(1, 2), 2);
      service.addBlock(new Position(1, 1), 2);
      correctService.addBlock(new Position(2, 1), 4);
      correctService.addBlock(new Position(2, 0), 4);
      correctService.addBlock(new Position(1, 1), 2);
      correctService.addBlock(new Position(1, 0), 4);
      service.predictableMoveAllBlocks(MovingDirection.down);
    } catch (IllegalParameterException e) {
      thrown = true;
    }
    assertFalse(thrown);
    assertTrue(correctService.getGameBoard().containsAll(service.getGameBoard()));
    assertTrue(service.getGameBoard().containsAll(correctService.getGameBoard()));
    assertTrue(correctService.getFreelist().containsAll(service.getFreelist()));
    assertTrue(service.getFreelist().containsAll(correctService.getFreelist()));
  }

  @Test
  void testMoveRight() {
    ImplementationGame2048Service correctService = new ImplementationGame2048Service();
    ImplementationGame2048Service service = new ImplementationGame2048Service();
    setUp(correctService, service);
    boolean thrown = false;
    try {
      service.addBlock(new Position( 3,1), 2);
      service.addBlock(new Position( 2,1), 2);
      service.addBlock(new Position( 1,1), 2);
      service.addBlock(new Position( 0,1), 2);
      service.addBlock(new Position( 2,2), 2);
      service.addBlock(new Position( 1,2), 2);
      service.addBlock(new Position( 0,2), 2);
      correctService.addBlock(new Position(3,2), 4);
      correctService.addBlock(new Position(2,2), 4);
      correctService.addBlock(new Position(2,1), 2);
      correctService.addBlock(new Position(3,1), 4);
      service.predictableMoveAllBlocks(MovingDirection.right);
    } catch (IllegalParameterException e) {
      thrown = true;
    }
    assertFalse(thrown);
    assertTrue(correctService.getGameBoard().containsAll(service.getGameBoard()));
    assertTrue(service.getGameBoard().containsAll(correctService.getGameBoard()));
    assertTrue(correctService.getFreelist().containsAll(service.getFreelist()));
    assertTrue(service.getFreelist().containsAll(correctService.getFreelist()));
  }
  @Test
  void testMoveLeft() {
    ImplementationGame2048Service correctService = new ImplementationGame2048Service();
    ImplementationGame2048Service service = new ImplementationGame2048Service();
    setUp(correctService, service);
    boolean thrown = false;
    try {
      service.addBlock(new Position( 3,1), 2);
      service.addBlock(new Position( 2,1), 2);
      service.addBlock(new Position( 1,1), 2);
      service.addBlock(new Position( 0,1), 2);
      service.addBlock(new Position( 3,2), 2);
      service.addBlock(new Position( 2,2), 2);
      service.addBlock(new Position( 1,2), 2);
      correctService.addBlock(new Position(1,2), 4);
      correctService.addBlock(new Position(0,2), 4);
      correctService.addBlock(new Position(1,1), 2);
      correctService.addBlock(new Position(0,1), 4);
      service.predictableMoveAllBlocks(MovingDirection.left);
    } catch (IllegalParameterException e) {
      thrown = true;
    }
    assertFalse(thrown);
    assertTrue(correctService.getGameBoard().containsAll(service.getGameBoard()));
    assertTrue(service.getGameBoard().containsAll(correctService.getGameBoard()));
    assertTrue(correctService.getFreelist().containsAll(service.getFreelist()));
    assertTrue(service.getFreelist().containsAll(correctService.getFreelist()));
  }
}