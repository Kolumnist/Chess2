package de.hhn.it.devtools.components.memory.junit;

import de.hhn.it.devtools.apis.memory.*;

public class DummyCallbackDeck implements DeckListener{
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(DummyCallbackDeck.class);

    @Override
    public void currentDeck(PictureCardDescriptor[] deck) {
        logger.info("Callback called with deck " + deck);
    }
}
