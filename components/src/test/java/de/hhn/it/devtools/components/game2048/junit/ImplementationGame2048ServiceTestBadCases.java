package de.hhn.it.devtools.components.game2048.junit;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.game2048.Position;
import de.hhn.it.devtools.components.game2048.provider.ImplementationGame2048Service;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Test für schlechte Beispiele des ImplementationGame2048Service. ")
public class ImplementationGame2048ServiceTestBadCases {

  @DisplayName("Tests the game board")
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
  @DisplayName("Tests if exception is thrown if two blocks get added on the same position. ")
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
}