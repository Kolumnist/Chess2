package de.hhn.it.devtools.components.memory.junit;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.memory.MemoryService;
import de.hhn.it.devtools.components.memory.provider.CardSet;
import de.hhn.it.devtools.components.memory.provider.SfsMemoryService;
import de.hhn.it.devtools.components.memory.provider.SfsTimer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertThrows;


@DisplayName("Test the MemoryService with bad cases.")
@ExtendWith(PictureCardParameterResolver.class)
public class TestMemoryServiceBadCases {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(TestMemoryServiceBadCases.class);

    private SfsMemoryService memoryService;

    @BeforeEach
    void setup(){
        memoryService = new SfsMemoryService();
    }


    @Test
    @DisplayName("add callback for a non existing pictureCard")
    void testExceptionWhenAddingCallbackToNonExistentPictureCard() {
        IllegalParameterException illegalParameterException = assertThrows(
                IllegalParameterException.class,
                () -> memoryService.addCallback(123456, new DummyCallbackPictureCard()));
    }

    @Test
    @DisplayName("remove callback for a non existing pictureCard")
    void testExceptionWhenRemovingCallbackToNonExistentPictureCard() {
        IllegalParameterException illegalParameterException = assertThrows(
                IllegalParameterException.class,
                () -> memoryService.removeCallback(123456, new DummyCallbackPictureCard()));
    }

    /*
    @Test
    @DisplayName("add callback for a non existing timer")
    void testExceptionWhenAddingCallbackToNonExistentTimer() {
        IllegalParameterException illegalParameterException = assertThrows(
                IllegalParameterException.class,
                () -> memoryService.addCallback(new DummyCallbackTimer())); //geht noch
    }

    @Test
    @DisplayName("remove callback for a non existing Timer")
    void testExceptionWhenRemovingCallbackToNonExistentTimer() {
        IllegalParameterException illegalParameterException = assertThrows(
                IllegalParameterException.class,
                () -> memoryService.removeCallback(new DummyCallbackTimer())); //geht noch
    }

    @Test
    @DisplayName("add callback for a non existing Deck")
    void testExceptionWhenAddingCallbackToNonExistentDeck() {
        IllegalParameterException illegalParameterException = assertThrows(
                IllegalParameterException.class,
                () -> memoryService.addCallback(new DummyCallbackDeck())); //geht noch
    }

    @Test
    @DisplayName("remove callback for a non existing Deck")
    void testExceptionWhenRemovingCallbackToNonExistentDeck() {
        IllegalParameterException illegalParameterException = assertThrows(
                IllegalParameterException.class,
                () -> memoryService.removeCallback(new DummyCallbackDeck())); //geht noch
    }

     */

    /*
    @Test
    @DisplayName("ask for a non existent pictureCard")
    void testExceptionWhenRequestingNonExistentPictureCardById(){
        IllegalParameterException illegalParameterException = assertThrows(
                IllegalParameterException.class, () -> memoryService
        );
    }
     */

    @Test
    @DisplayName("ask for a non existing pictureCardDescriptor")
    void testExceptionWhenRequestingNonExistentPictureCard() {
        IllegalParameterException illegalParameterException = assertThrows(
                IllegalParameterException.class, () -> memoryService.getPictureCard(12345)
        );
    }

    @Test
    @DisplayName("turnCard for non existing pictureCard")
    void testExceptionWhenTurningNonExistentPictureCard() {
        IllegalParameterException illegalParameterException = assertThrows(
                IllegalParameterException.class, () -> memoryService.turnCard(12345)
        );
    }

    @Test
    @DisplayName("addCardset for non existing set")
    void testExceptionWhenAddingNonExistingCardSet() {
        IllegalParameterException illegalParameterException = assertThrows(
                IllegalParameterException.class, () -> memoryService.addCardSet(null)
        );
    }

    @Test
    @DisplayName("fetchCards for non existing cardDescriptors")
    void testExceptionWhenFetchingNonExistingDescriptors(){
        IllegalParameterException illegalParameterException = assertThrows(
                IllegalParameterException.class, () -> memoryService.fetchCards(null)
        );
    }

    @Test
    @DisplayName("fetching cards for non existing pictureReferences")
    void testExceptionWhenFetchingNonExistingPictureReferences(){
        IllegalParameterException illegalParameterException = assertThrows(
                IllegalParameterException.class, () -> memoryService.fetchPicReferences(null)
        );
    }

    @Test
    @DisplayName("matching Cards for non exiting card")
    void testExceptionWhenMatchingNonExistingCard(){
        IllegalParameterException illegalParameterException = assertThrows(
                IllegalParameterException.class, () -> memoryService.matchCards(null, null)
        );
    }
}
