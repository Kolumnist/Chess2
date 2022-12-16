package de.hhn.it.devtools.components.memory.junit;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.memory.CardSetDescriptor;
import de.hhn.it.devtools.apis.memory.DeckListener;
import de.hhn.it.devtools.apis.memory.Difficulty;
import de.hhn.it.devtools.apis.memory.PictureCardDescriptor;
import de.hhn.it.devtools.components.memory.provider.SfsMemoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.HashMap;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DisplayName("Test the MemoryService with bad cases.")
@ExtendWith(PictureCardParameterResolver.class)
public class TestMemoryServiceBadCases {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(TestMemoryServiceBadCases.class);

  private SfsMemoryService memoryService;
  private DummyCallbackTimer dummyCallbackTimer;
  private DummyCallbackDeck dummyCallbackDeck;

  private int failureInteger = 123456;

  @BeforeEach
  void setup() {
    memoryService = new SfsMemoryService();
    dummyCallbackTimer = null;
    dummyCallbackDeck = null;
  }


  @Test
  @DisplayName("add callback for a non existing pictureCard")
  void testExceptionWhenAddingCallbackToNonExistentPictureCard() {
    assertThrows(IllegalParameterException.class,
            () -> memoryService.addCallback(failureInteger, new DummyCallbackPictureCard()));
  }

  @Test
  @DisplayName("remove callback for a non existing pictureCard")
  void testExceptionWhenRemovingCallbackToNonExistentPictureCard() {
    assertThrows(IllegalParameterException.class,
            () -> memoryService.removeCallback(failureInteger, new DummyCallbackPictureCard()));
  }


  @Test
  @DisplayName("add callback for a non existing timer")
  void testExceptionWhenAddingCallbackToNonExistentTimer() {
    assertThrows(IllegalParameterException.class,
            () -> memoryService.addCallback(dummyCallbackTimer));
  }

  @Test
  @DisplayName("remove callback for a non existing Timer")
  void testExceptionWhenRemovingCallbackToNonExistentTimer() {
    assertThrows(IllegalParameterException.class,
            () -> memoryService.removeCallback(dummyCallbackTimer));
  }

  @Test
  @DisplayName("add callback for a non existing Deck")
  void testExceptionWhenAddingCallbackToNonExistentDeck() {
    assertThrows(IllegalParameterException.class,
            () -> memoryService.addCallback(dummyCallbackDeck));
  }

  @Test
  @DisplayName("remove callback for a non existing Deck")
  void testExceptionWhenRemovingCallbackToNonExistentDeck() {
    assertThrows(IllegalParameterException.class,
            () -> memoryService.removeCallback(dummyCallbackDeck));
  }


  @Test
  @DisplayName("ask for a non existent pictureCard")
  void testExceptionWhenRequestingNonExistentPictureCardById() {
    assertThrows(IllegalParameterException.class,
            () -> memoryService.getPictureCardById(failureInteger)
    );
  }


  @Test
  @DisplayName("ask for a non existing pictureCardDescriptor")
  void testExceptionWhenRequestingNonExistentPictureCard() {
    assertThrows(IllegalParameterException.class,
            () -> memoryService.getPictureCardDescriptor(failureInteger)
    );
  }

  @Test
  @DisplayName("ask for a negative pictureCardDescriptor")
  void testExceptionWhenRequestingNegativePictureCard() {
    assertThrows(IllegalParameterException.class,
        () -> memoryService.getPictureCardDescriptor(-24)
    );
  }

  @Test
  @DisplayName("turnCard for non existing pictureCard")
  void testExceptionWhenTurningNonExistentPictureCard() {
    assertThrows(IllegalParameterException.class,
            () -> memoryService.turnCard(failureInteger)
    );
  }

  @Test
  @DisplayName("addCardset for non existing set")
  void testExceptionWhenAddingNonExistingCardSet() {
    assertThrows(IllegalParameterException.class,
            () -> memoryService.addCardSet(null)
    );
  }

  /*
  @Test
  @DisplayName("fetchCards for non existing cardDescriptors")
  void testExceptionWhenFetchingNonExistingDescriptors() {
    assertThrows(IllegalParameterException.class,
            () -> memoryService.fetchCards(null)
    );
  }

   */

 /*
  @Test
  @DisplayName("matching Cards for non existing card")
  void testExceptionWhenMatchingNonExistingCard() {
    assertThrows(IllegalParameterException.class,
            () -> memoryService.checkForMatch(null, null)
    );
  }
  */

  @Test
  @DisplayName("addCardSet for non existing cardSet")
  void addCardSetForNonExistingCardSet() {
    assertThrows(IllegalParameterException.class,
            () -> memoryService.addCardSet(null)
    );
  }

  @Test
  @DisplayName("change difficulty to null")
  void changeDifficultyToNull() {
    assertThrows(IllegalParameterException.class,
            () -> memoryService.changeDifficulty(null)
    );
  }

  /*
  @Test
  @DisplayName("adding the same cardSet twice")
  void addCardSetTwice(PictureCardDescriptor[] descriptors) throws IllegalParameterException {
    HashMap<Integer, String> pictureReferences = new HashMap<>();
    pictureReferences.put(1, "Mario");
    CardSetDescriptor cardSetDescriptor = new CardSetDescriptor(Difficulty.EASY, descriptors, pictureReferences);
    memoryService.addCardSet(cardSetDescriptor);
    assertThrows(IllegalParameterException.class,
            () -> memoryService.addCardSet(cardSetDescriptor)
    );
  }
  */

  @Test
  @DisplayName("Add a deck listener twice")
  void addADeckListenerTwice() throws IllegalParameterException {
    DeckListener listener = new DummyCallbackDeck();
    memoryService.addCallback(listener);
    assertThrows(IllegalParameterException.class,
            () -> memoryService.addCallback(listener)
    );
  }

  @Test
  @DisplayName("remove callback from not registered deck listener")
  void removeCallbackFromNotRegisteredDeckListener() {
    DeckListener listener = new DummyCallbackDeck();
    assertThrows(IllegalParameterException.class,
            () -> memoryService.removeCallback(listener)
    );
  }
}
