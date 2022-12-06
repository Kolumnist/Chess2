package de.hhn.it.devtools.components.memory.junit;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.memory.Difficulty;
import de.hhn.it.devtools.apis.memory.PictureCardDescriptor;
import de.hhn.it.devtools.components.memory.provider.CardSet;
import de.hhn.it.devtools.components.memory.provider.SfsMemoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test the MemoryService game interactions.")
@ExtendWith(PictureCardParameterResolver.class)
public class TestMemoryServiceGameInteractions {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(TestMemoryServiceGameInteractions.class);

  SfsMemoryService memoryService;

  @BeforeEach
  void setup(List<PictureCardDescriptor> descriptors) throws IllegalParameterException {
    memoryService = new SfsMemoryService();
    HashMap<Integer, String> pictureReferences = new HashMap<>();
    pictureReferences.put(1, "Mario");
    CardSet cardSet = new CardSet(Difficulty.EASY, descriptors,pictureReferences);
    memoryService.addCardSet(cardSet);
  }

  /*
  @Test
  @DisplayName("newGame is initialized successfully")
  void newGameIsInitializedSuccessfully() throws IllegalParameterException {
    memoryService.newGame(Difficulty.EASY);
    assertTrue(memoryService.getDeck().length > 0);
  }
  */


  @Test
  @DisplayName("newGame is initialized with null")
  void newGameIsInitializedWithNull() {
    IllegalParameterException illegalParameterException = assertThrows(
        IllegalParameterException.class, () -> memoryService.newGame(null)
    );
  }

  /*
  @Test
  @DisplayName("close game successfully")
  void closeGameSuccessfully() throws IllegalParameterException {
    memoryService.newGame(Difficulty.EASY);
    memoryService.closeGame();
    assertTrue(memoryService.getDeck().equals(null));
    assertTrue(memoryService.getPictureReferences().size() == 0);
  }
   */
}
