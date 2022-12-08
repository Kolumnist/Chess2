package de.hhn.it.devtools.components.memory.junit;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.memory.DeckListener;
import de.hhn.it.devtools.apis.memory.Difficulty;
import de.hhn.it.devtools.apis.memory.PictureCardDescriptor;
import de.hhn.it.devtools.apis.memory.State;
import de.hhn.it.devtools.components.memory.provider.CardSet;
import de.hhn.it.devtools.components.memory.provider.SfsMemoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
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
    DeckListener listener = new SimpleDeckListener();
    memoryService.addCallback(listener);
    HashMap<Integer, String> pictureReferences = new HashMap<>();
    pictureReferences.put(1, "Mario");
    CardSet cardSet = new CardSet(Difficulty.EASY, descriptors,pictureReferences);
    memoryService.addCardSet(cardSet);
  }


  @Test
  @DisplayName("newGame is initialized successfully")
  void newGameIsInitializedSuccessfully() throws IllegalParameterException {
    memoryService.newGame(Difficulty.EASY);
    assertTrue(memoryService.getDeck().length > 0);
  }



  @Test
  @DisplayName("newGame is initialized with null")
  void newGameIsInitializedWithNull() {
    IllegalParameterException illegalParameterException = assertThrows(
        IllegalParameterException.class, () -> memoryService.newGame(null)
    );
  }


  @Test
  @DisplayName("close game successfully")
  void closeGameSuccessfully() throws IllegalParameterException {
    memoryService.newGame(Difficulty.EASY);
    memoryService.closeGame();
    assertAll(
            () -> assertNull(memoryService.getDeck()),
            () -> assertTrue(memoryService.getPictureReferences().size() == 0),
            () -> assertTrue(memoryService.getPictureCards().size() == 0)
    );
  }

  @Test
  @DisplayName("change difficulty successfully")
  void changeDifficultySuccessfully() throws IllegalParameterException {
    List<PictureCardDescriptor> newList = new ArrayList<>();
    newList.add(new PictureCardDescriptor(-1, 2, "Mario"));
    newList.add(new PictureCardDescriptor(-1, 3, "Peach"));
    HashMap<Integer, String> pictureReferences = new HashMap<>();
    pictureReferences.put(1, "Mario");
    memoryService.addCardSet(new CardSet(Difficulty.MEDIUM, newList,pictureReferences));
    memoryService.newGame(Difficulty.MEDIUM);
    assertTrue(memoryService.getPictureCards().size() == 2);
  }

  @Test
  @DisplayName("turnCard for one card")
  void turnCardForOneCard() throws IllegalParameterException {
    memoryService.newGame(Difficulty.EASY);
    memoryService.turnCard(1);
    assertAll(
            () -> assertTrue(memoryService.getPictureCard(1).getState() == State.VISIBLE)
    );
  }


  @Test
  @DisplayName("turning card for visible card")
  void turnCardForVisibleCard() throws IllegalParameterException {
      memoryService.newGame(Difficulty.EASY);
      memoryService.turnCard(1);
      IllegalParameterException illegalParameterException = assertThrows(
              IllegalParameterException.class, () -> memoryService.turnCard(1)
      );
  }

  @Test
  @DisplayName("turnCard for two cards of the same type")
  void turnCardForTheSameType() throws IllegalParameterException {
    memoryService.newGame(Difficulty.EASY);
    memoryService.turnCard(2);
    memoryService.turnCard(3);
    assertAll(
            () -> assertTrue(memoryService.getPictureCard(2).getState() == State.HIDDEN),
            () -> assertTrue(memoryService.getPictureCard(3).getState() == State.HIDDEN)
    );
  }

  @Test
  @DisplayName("turnCard for two cards that match in HashMap")
  void turnCardsForTwoCardsThatMatch() throws IllegalParameterException {
    memoryService.newGame(Difficulty.EASY);
    memoryService.turnCard(1);
    memoryService.turnCard(2);
    assertAll(
            () -> assertTrue(memoryService.getPictureCard(1).getState() == State.MATCHED),
            () -> assertTrue(memoryService.getPictureCard(2).getState() == State.MATCHED)
    );
  }

  //if c is pictureCard
    //if



  class SimpleDeckListener implements DeckListener {
    ArrayList<PictureCardDescriptor[]> decks;

    public SimpleDeckListener() { decks = new ArrayList<>();}

    @Override
    public void currentDeck(PictureCardDescriptor[] deck) {
      decks.add(deck);
    }


  }

}
