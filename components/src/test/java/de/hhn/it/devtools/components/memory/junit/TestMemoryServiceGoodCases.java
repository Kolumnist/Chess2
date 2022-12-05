package de.hhn.it.devtools.components.memory.junit;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.memory.PictureCardDescriptor;
import de.hhn.it.devtools.apis.memory.PictureCardListener;
import de.hhn.it.devtools.components.memory.provider.SfsMemoryService;
import de.hhn.it.devtools.components.memory.provider.SfsPictureCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test the MemoryServiceService with good cases.")
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

    IllegalParameterException exception = assertThrows(IllegalParameterException.class,
        () -> memoryService.removeCallback(card.getId(), listener));
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
}
