package de.hhn.it.devtools.components.memory.provider;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.memory.PictureCardDescriptor;
import de.hhn.it.devtools.apis.memory.PictureCardListener;

/**
 * Interface to a PictureCard.
 */
public interface PictureCard {

  void turnCard() throws IllegalStateException;

  void addCallback(PictureCardListener listener) throws IllegalParameterException;

  void removeCallback(PictureCardListener listener) throws IllegalParameterException;

  PictureCardDescriptor getPictureCard();

}
