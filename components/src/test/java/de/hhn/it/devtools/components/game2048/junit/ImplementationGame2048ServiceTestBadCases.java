package de.hhn.it.devtools.components.game2048.junit;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.game2048.Position;
import de.hhn.it.devtools.components.game2048.provider.ImplementationGame2048Service;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfSystemProperties;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Test für schlechte Beispiele des ImplementationGame2048Service ")
public class ImplementationGame2048ServiceTestBadCases {

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
  @DisplayName("Tests what happens if two blocks are added on the same position. ")
  void testAddingSamePosition() {
    ImplementationGame2048Service correctService = new ImplementationGame2048Service();
    ImplementationGame2048Service service = new ImplementationGame2048Service();
    setUp(correctService, service);
    Position firstPosition = new Position(1,1);
    int value = 2;
    boolean thrown = false;
    try {
      service.addBlock(firstPosition,value);
      service.addBlock(firstPosition,value);
    } catch (IllegalParameterException e) {
      thrown = true;
    }
    assertTrue(thrown);
  }

  @Test
  @DisplayName("Tests if null references are caught for blocks. ")
  void testMoveBlocksNullReferenz(){
    ImplementationGame2048Service service = new ImplementationGame2048Service();
    service.initialisation();
    boolean thrown = false;
    try {
      service.moveAllBlocks(null);
    } catch (IllegalParameterException e) {
      thrown = true;
    }
    assertTrue(thrown);
  }

  @Test
  @DisplayName("Tests if null references are caught for callbacks. ")
  void testCallbackNullReferenz(){
    ImplementationGame2048Service service = new ImplementationGame2048Service();
    boolean thrown = false;
    try {
      service.addCallback(null);
    } catch (IllegalParameterException e) {
      thrown = true;
    }
    assertTrue(thrown);
    thrown = false;
    try {
      service.removeCallback(null);
    } catch (IllegalParameterException e) {
      thrown = true;
    }
    assertTrue(thrown);
  }
}