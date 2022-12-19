package de.hhn.it.devtools.components.memory.junit;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.memory.*;
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
  List<PictureCardDescriptor> descriptors;
  PictureCardDescriptor[] descriptorsArray;
  SimpleDeckListener listener;

  @BeforeEach
  void setup(List<PictureCardDescriptor> descriptors) throws IllegalParameterException {
    memoryService = new SfsMemoryService();
    this.listener = new SimpleDeckListener();
    memoryService.addCallback(listener);
    HashMap<Integer, String> pictureReferences = new HashMap<>();
    pictureReferences.put(1, "Mario");
    this.descriptors = new ArrayList<>();
    this.descriptors.addAll(descriptors);
    descriptorsArray = new PictureCardDescriptor[this.descriptors.size()];
    this.descriptors.toArray(descriptorsArray);
    CardSetDescriptor cardSetDescriptor = new CardSetDescriptor(Difficulty.EASY, this.descriptorsArray, pictureReferences);
    memoryService.addCardSet(cardSetDescriptor);
  }


  @Test
  @DisplayName("newGame is initialized successfully")
  void newGameIsInitializedSuccessfully() throws IllegalParameterException {
    memoryService.newGame(Difficulty.EASY);

    assertAll(

        () -> assertTrue(memoryService.getCurrentCardSet().getDescriptor().getPictureCardDescriptors().length > 0),
        () -> assertEquals(memoryService.getCurrentCardSet().getDescriptor().getDifficulty(), Difficulty.EASY),
        () -> assertEquals(memoryService.getPictureCardDescriptors(), descriptors),
        () -> assertTrue(listener.decks.size() > 0)
    );
  }


  @Test
  @DisplayName("newGame is initialized with null")
  void newGameIsInitializedWithNull() {
    assertThrows(IllegalParameterException.class,
            () -> memoryService.newGame(null)
    );
  }


  @Test
  @DisplayName("close game successfully")
  void closeGameSuccessfully() throws IllegalParameterException {
    memoryService.newGame(Difficulty.EASY);
    memoryService.closeGame();
    assertAll(
            () -> assertNull(memoryService.getCurrentCardSet())
    );
  }

  @Test
  @DisplayName("change difficulty successfully")
  void changeDifficultySuccessfully() throws IllegalParameterException {
    PictureCardDescriptor[] newList = {new PictureCardDescriptor(-1, "Mario"),
        new PictureCardDescriptor(-1, "Peach")};
    HashMap<Integer, String> pictureReferences = new HashMap<>();
    pictureReferences.put(1, "Mario");
    memoryService.addCardSet(new CardSetDescriptor(Difficulty.MEDIUM, newList, pictureReferences));
    memoryService.newGame(Difficulty.MEDIUM);
    assertTrue(memoryService.getPictureCardDescriptors().size() == 2);
  }

  @Test
  @DisplayName("turnCard for one card")
  void turnCardForOneCard() throws IllegalParameterException {
    memoryService.newGame(Difficulty.EASY);
    memoryService.turnCard(1);
    assertAll(
            () -> assertTrue(memoryService.getPictureCardDescriptor(1).getState() == State.VISIBLE)
    );
  }


  @Test
  @DisplayName("turning card for visible card")
  void turnCardForVisibleCard() throws IllegalParameterException {
    memoryService.newGame(Difficulty.EASY);
    memoryService.turnCard(1);
    assertThrows(IllegalParameterException.class,
            () -> memoryService.turnCard(1)
    );
  }

  @Test
  @DisplayName("turnCard for two cards of the same type")
  void turnCardForTheSameType() throws IllegalParameterException {
    memoryService.newGame(Difficulty.EASY);
    memoryService.turnCard(1);
    memoryService.turnCard(2);
    assertAll(
            () -> assertTrue(memoryService.getPictureCardDescriptor(1).getState() == State.HIDDEN),
            () -> assertTrue(memoryService.getPictureCardDescriptor(2).getState() == State.HIDDEN)
    );
  }

  @Test
  @DisplayName("turnCard for two cards that match in HashMap")
  void turnCardsForTwoCardsThatMatch() throws IllegalParameterException {
    memoryService.newGame(Difficulty.EASY);
    memoryService.turnCard(0);
    memoryService.turnCard(1);
    assertAll(
            () -> assertTrue(memoryService.getPictureCardDescriptor(0).getState() == State.MATCHED),
            () -> assertTrue(memoryService.getPictureCardDescriptor(1).getState() == State.MATCHED)
    );
  }

  @Test
  @DisplayName("turnCard for two cards that match in HashMap second case")
  void turnCardsForTwoCardsThatMatchSecondCase() throws IllegalParameterException {
    memoryService.newGame(Difficulty.EASY);
    memoryService.turnCard(1);
    memoryService.turnCard(0);
    assertAll(
            () -> assertTrue(memoryService.getPictureCardDescriptor(1).getState() == State.MATCHED),
            () -> assertTrue(memoryService.getPictureCardDescriptor(0).getState() == State.MATCHED)
    );
  }

  @Test
  @DisplayName("turnCard for two cards that dont match in HashMap")
  void turnCardsForTwoCardsThatDontMatch() throws IllegalParameterException {
    memoryService.newGame(Difficulty.EASY);
    memoryService.turnCard(0);
    memoryService.turnCard(2);
    assertAll(
            () -> assertTrue(memoryService.getPictureCardDescriptor(0).getState() == State.HIDDEN),
            () -> assertTrue(memoryService.getPictureCardDescriptor(2).getState() == State.HIDDEN)
    );
  }


  @Test
  @DisplayName("turnCard for two cards that dont match in HashMap second case")
  void turnCardsForTwoCardsThatDontMatchSecondCase() throws IllegalParameterException {
    memoryService.newGame(Difficulty.EASY);
    memoryService.turnCard(2);
    memoryService.turnCard(0);
    assertAll(
            () -> assertTrue(memoryService.getPictureCardDescriptor(0).getState() == State.HIDDEN),
            () -> assertTrue(memoryService.getPictureCardDescriptor(2).getState() == State.HIDDEN)
    );
  }



  class SimpleDeckListener implements DeckListener {
    ArrayList<PictureCardDescriptor[]> decks;

    public SimpleDeckListener() {
      decks = new ArrayList<>();
    }

    @Override
    public void currentDeck(PictureCardDescriptor[] deck) {
      decks.add(deck);
    }

  }

}
