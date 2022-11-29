package de.hhn.it.devtools.components.memory.junit;


import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.memory.PictureCardDescriptor;
import de.hhn.it.devtools.apis.memory.PictureCardListener;
import de.hhn.it.devtools.apis.memory.State;
import de.hhn.it.devtools.components.memory.provider.SfsPictureCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestPictureCard {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(TestPictureCard.class);

    private SfsPictureCard pictureCard;

    @BeforeEach
    void setup() {
        pictureCard = new SfsPictureCard(new PictureCardDescriptor(-1,1,"Mario"));
    }

    @Test
    @DisplayName("Test registration of callback listener.")
    void CheckSuccessfulRegistrationOfCallback() throws IllegalParameterException,
            InterruptedException {
        SimplePictureCardListener listener = new SimplePictureCardListener();
        pictureCard.addCallback(listener);
        pictureCard.turnCard();                             //changes State to Visible
        assertEquals(1, listener.states.size());    //should be one State - Visible

    }

    @Test
    @DisplayName("Test registration of two listeners which both get notified.")
    void CheckRegistrationTwoListeners() throws IllegalParameterException, InterruptedException {
        SimplePictureCardListener listener0 = new SimplePictureCardListener();
        SimplePictureCardListener listener1 = new SimplePictureCardListener();
        pictureCard.addCallback(listener0);
        pictureCard.addCallback(listener1);
        pictureCard.turnCard();
        assertAll(
                () -> assertEquals(1,listener0.states.size()),
                () -> assertEquals(1, listener1.states.size())
        );
    }

    @Test
    @DisplayName("Register one listener, remove the listener, provoke a new state.")
    void registerAndRemoveOneListener() throws IllegalParameterException, InterruptedException {
        SimplePictureCardListener listener = new SimplePictureCardListener();
        pictureCard.addCallback(listener);
        pictureCard.removeCallback(listener);
        pictureCard.turnCard();
        assertEquals(0, listener.states.size()); //should be 0, because removed before new state
    }

    @Test
    @DisplayName("The same listener cannot be registered twice.")
    void tryRegisterSameListenerTwice() throws IllegalParameterException {
        SimplePictureCardListener listener = new SimplePictureCardListener();
        pictureCard.addCallback(listener);
        IllegalParameterException exception = assertThrows(IllegalParameterException.class,
                () -> pictureCard.addCallback(listener));
    }

    @Test
    @DisplayName("Listener which is not registered cannot be removed.")
    void tryRemoveNotRegisteredListener() throws  IllegalParameterException {
        SimplePictureCardListener listener = new SimplePictureCardListener();
        IllegalParameterException exception = assertThrows(IllegalParameterException.class,
                () -> pictureCard.removeCallback(listener));
    }

    @Test
    @DisplayName("Null references cannot be registered as listener.")
    void tryRegisterNullReferencesAsListener() {
        IllegalParameterException exception = assertThrows(IllegalParameterException.class,
                () -> pictureCard.addCallback(null));
    }

    @Test
    @DisplayName("Null references cannot be removed as listener.")
    void tryRemoveNullReferenceAsListener() {
        IllegalParameterException exception = assertThrows(IllegalParameterException.class,
                () -> pictureCard.removeCallback(null));
    }


    /**
     * Inner class as a PictureCardListener.
     */
    class SimplePictureCardListener implements PictureCardListener {

        public List<State> states;

        public SimplePictureCardListener() {
            states = new ArrayList<>();
        }

        @Override
        public void currentState(State state) {
            states.add(state);
        }


    }

}
