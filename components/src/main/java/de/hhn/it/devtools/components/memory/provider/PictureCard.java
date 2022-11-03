package de.hhn.it.devtools.components.memory.provider;

import de.hhn.it.devtools.apis.memory.PictureCardDescriptor;
import de.hhn.it.devtools.apis.memory.PictureCardListener;

/**
 * Interface to a PictureCard.
 */
public interface PictureCard {

    void turnCard();

    void addCallback(PictureCardListener listener);

    void removeCallback(PictureCardListener listener);

    PictureCardDescriptor getPictureCard();

}
