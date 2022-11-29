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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPictureCard {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(TestPictureCard.class);

    private SfsPictureCard pictureCard;

    @BeforeEach
    void setup() {
        pictureCard = new SfsPictureCard(new PictureCardDescriptor(-1,1,"Mario"));
    }

    @Test
    @DisplayName("Test registration of callback.")
    void CheckSuccessfulRegistrationOfCallback() throws IllegalParameterException,
            InterruptedException {
        SimplePictureCardListener listener = new SimplePictureCardListener();
        pictureCard.addCallback(listener);
        pictureCard.turnCard();                             //changes State to visible
        assertEquals(1, listener.states.size());    //should be one State - visible
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
