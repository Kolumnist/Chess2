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
    pictureReferences.put(2, "Peach");
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
    memoryService.newGame(Difficulty.EASY);
    PictureCardDescriptor[] newList = {new PictureCardDescriptor(-1, "Mario"),
        new PictureCardDescriptor(-1, "Peach")};
    HashMap<Integer, String> pictureReferences = new HashMap<>();
    pictureReferences.put(1, "Mario");
    CardSetDescriptor cardSetDescriptor = new CardSetDescriptor(Difficulty.MEDIUM, newList, pictureReferences);
    memoryService.addCardSet(cardSetDescriptor);
    memoryService.changeDifficulty(Difficulty.MEDIUM);
    assertAll(
        () ->     assertEquals(2, memoryService.getPictureCardDescriptors().size()),
        () ->     assertEquals(memoryService.getCurrentCardSet().getDescriptor(), cardSetDescriptor)
    );
  }

  @Test
  @DisplayName("turnCard for one card")
  void turnCardForOneCard() throws IllegalParameterException {
    memoryService.newGame(Difficulty.EASY);
    memoryService.turnCard(1);
    assertAll(
            () -> assertSame(memoryService.getPictureCardDescriptor(1).getState(), State.VISIBLE)
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
  @DisplayName("turnCard for two cards of the same type name card")
  void turnCardForTheSameTypeNameCard() throws IllegalParameterException {
    memoryService.newGame(Difficulty.EASY);
    memoryService.turnCard(1);
    memoryService.turnCard(2);
    assertAll(
            () -> assertSame(memoryService.getPictureCardDescriptor(1).getState(), State.HIDDEN),
            () -> assertSame(memoryService.getPictureCardDescriptor(2).getState(), State.HIDDEN)
    );
  }

  @Test
  @DisplayName("turnCard for two cards of the same type picture card")
  void turnCardForTheSameTypePictureCard() throws IllegalParameterException {
    memoryService.newGame(Difficulty.EASY);
    memoryService.turnCard(0);
    memoryService.turnCard(3);
    assertAll(
            () -> assertSame(memoryService.getPictureCardDescriptor(0).getState(), State.HIDDEN),
            () -> assertSame(memoryService.getPictureCardDescriptor(3).getState(), State.HIDDEN)
    );
  }

  @Test
  @DisplayName("turnCard for two pairs of cards that matchin different order  in HashMap")
  void turnCardsForTwoPairsThatMatchInDifferentOrder() throws IllegalParameterException {
    memoryService.newGame(Difficulty.EASY);
    memoryService.turnCard(0);
    memoryService.turnCard(1);
    memoryService.turnCard(2);
    memoryService.turnCard(3);
    assertAll(
            () -> assertSame(memoryService.getPictureCardDescriptor(0).getState(), State.MATCHED),
            () -> assertSame(memoryService.getPictureCardDescriptor(1).getState(), State.MATCHED),
            () -> assertSame(memoryService.getPictureCardDescriptor(2).getState(), State.MATCHED),
            () -> assertSame(memoryService.getPictureCardDescriptor(3).getState(), State.MATCHED)
    );
  }



  @Test
  @DisplayName("turnCard for two cards that dont match in HashMap")
  void turnCardsForTwoCardsThatDontMatch() throws IllegalParameterException {
    memoryService.newGame(Difficulty.EASY);
    memoryService.turnCard(0);
    memoryService.turnCard(2);
    assertAll(
            () -> assertSame(memoryService.getPictureCardDescriptor(0).getState(), State.HIDDEN),
            () -> assertSame(memoryService.getPictureCardDescriptor(2).getState(), State.HIDDEN)
    );
  }


  @Test
  @DisplayName("turnCard for two cards that dont match in HashMap second case")
  void turnCardsForTwoCardsThatDontMatchSecondCase() throws IllegalParameterException {
    memoryService.newGame(Difficulty.EASY);
    memoryService.turnCard(2);
    memoryService.turnCard(0);
    assertAll(
            () -> assertSame(memoryService.getPictureCardDescriptor(0).getState(), State.HIDDEN),
            () -> assertSame(memoryService.getPictureCardDescriptor(2).getState(), State.HIDDEN)
    );
  }



  static class SimpleDeckListener implements DeckListener {
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
