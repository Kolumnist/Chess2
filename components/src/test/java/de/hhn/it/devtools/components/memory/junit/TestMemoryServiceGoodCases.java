package de.hhn.it.devtools.components.memory.junit;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.memory.*;
import de.hhn.it.devtools.components.memory.provider.CardSet;
import de.hhn.it.devtools.components.memory.provider.SfsMemoryService;
import de.hhn.it.devtools.components.memory.provider.SfsPictureCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test the MemoryService with good cases.")
@ExtendWith(PictureCardParameterResolver.class)
public class TestMemoryServiceGoodCases {

  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(TestMemoryServiceGoodCases.class);

  SfsMemoryService memoryService;
  ArrayList<PictureCardDescriptor> descriptors;
  PictureCardDescriptor[] descriptorsArray;

  @BeforeEach
  void setup(List<PictureCardDescriptor> descriptors) {
    SfsPictureCard.resetIdCounter();
    memoryService = new SfsMemoryService();
    this.descriptors = new ArrayList<>();
    this.descriptors.addAll(descriptors);
    descriptorsArray = new PictureCardDescriptor[this.descriptors.size()];
    this.descriptors.toArray(descriptorsArray);
  }

  @Test
  @DisplayName("add and remove callback to known pictureCard")
  void addAndRemoveCallbacksToKnownPictureCard() throws IllegalParameterException {
    memoryService.fetchCards(descriptorsArray);
    List<PictureCardDescriptor> cards = memoryService.getPictureCards();
    PictureCardDescriptor card = cards.get(0);
    PictureCardListener listener = new DummyCallbackPictureCard();
    memoryService.addCallback(card.getId(), listener);
    memoryService.removeCallback(card.getId(), listener);

    assertThrows(IllegalParameterException.class,
            () -> memoryService.removeCallback(card.getId(), listener));
  }

  @Test
  @DisplayName("Add and remove callback to a timer")
  void addAndRemoveCallbackToTimer() throws IllegalParameterException {
    TimerListener listener = new DummyCallbackTimer();
    memoryService.addCallback(listener);
    memoryService.removeCallback(listener);

    assertThrows(IllegalParameterException.class,
            () -> memoryService.removeCallback(listener));
  }

  @Test
  @DisplayName("start, stop and reset timer")
  void startStopResetTimer() throws InterruptedException, IllegalParameterException {
    ArrayList<Integer> times = new ArrayList<>();
    memoryService.addCallback(new TimerListener() {
      @Override
      public void currentTime(int time) {
        times.add(time);
      }
    });
    memoryService.startTimer();
    Thread.sleep(3000);
    memoryService.stopTimer();
    assertTrue(times.size() > 0);
  }

  @Test
  @DisplayName("Add and remove callback deck listener")
  void addAndRemoveCallbackDeckListener() throws IllegalParameterException {
    DeckListener listener = new DummyCallbackDeck();
    memoryService.addCallback(listener);
    memoryService.removeCallback(listener);

    assertThrows(IllegalParameterException.class,
            () -> memoryService.removeCallback(listener));
  }


  @Test
  @DisplayName("A new service has no PictureCards")
  void aNewServiceHasNoPictureCards() {
    List<PictureCardDescriptor> descriptors = memoryService.getPictureCards();
    assertNotNull(descriptors);
    assertEquals(0, descriptors.size(), "The list should be empty.");
  }


  @Test
  @DisplayName("Add multiple pictureCards and check the result")
  public void addMultiplePictureCards() throws IllegalParameterException {

    memoryService.fetchCards(descriptorsArray);

    List<PictureCardDescriptor> cards = memoryService.getPictureCards();
    assertAll(
            () -> assertEquals(3, cards.size(), "Now we should " +
                    "have three pictureCards."),
            () -> assertNotEquals(cards.get(0).getId(), cards.get(1).getId()),
            () -> assertNotEquals(cards.get(1).getId(), cards.get(2).getId()),
            () -> assertNotEquals(cards.get(0).getId(), cards.get(2).getId())
    );
  }

  @Test
  @DisplayName("Add a cardSet to memoryService")
  public void addCardSetToMemoryService(List<PictureCardDescriptor> descriptors) throws IllegalParameterException {
    memoryService = new SfsMemoryService();
    HashMap<Integer, String> pictureReferences = new HashMap<>();
    pictureReferences.put(1, "Mario");
    CardSet cardSet = new CardSet(Difficulty.EASY, descriptors, pictureReferences);
    memoryService.addCardSet(cardSet);

    assertTrue(memoryService.getCardSetStorage().size() > 0);
  }
}
