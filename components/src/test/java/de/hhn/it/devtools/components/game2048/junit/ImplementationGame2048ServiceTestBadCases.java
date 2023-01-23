package de.hhn.it.devtools.components.game2048.junit;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.game2048.Position;
import de.hhn.it.devtools.components.game2048.provider.ImplementationGame2048Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test für schlechte Beispiele des ImplementationGame2048Service ")
public class ImplementationGame2048ServiceTestBadCases {

  private ImplementationGame2048Service correctService = new ImplementationGame2048Service();
  private ImplementationGame2048Service service = new ImplementationGame2048Service();

  @BeforeEach
  void initialiseServices() {
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
    Position firstPosition = new Position(1, 1);
    int value = 2;
    assertAll(() -> assertDoesNotThrow(() -> service.addBlock(firstPosition, value)),
            () -> assertThrows(IllegalParameterException.class, () -> service.addBlock(firstPosition, value))
    );
  }

  @Test
  @DisplayName("Tests if null references are caught for blocks. ")
  void testMoveBlocksNullReferenz() {
    service.initialisation();
    assertThrows(IllegalParameterException.class, () -> service.moveAllBlocks(null));
  }

  @Test
  @DisplayName("Tests if null references are caught for callbacks. ")
  void testCallbackNullReferenz() {
    assertAll(() -> assertThrows(IllegalParameterException.class, () -> service.addCallback(null)),
            () -> assertThrows(IllegalParameterException.class, () -> service.removeCallback(null))
    );
  }
}