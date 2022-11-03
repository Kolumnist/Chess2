package de.hhn.it.devtools.components.memory.provider;

import de.hhn.it.devtools.apis.memory.PictureCardDescriptor;
import de.hhn.it.devtools.apis.memory.PictureCardListener;

import java.util.List;

/**
 * Simple implementation of a PictureCard interface.
 */

public class SfsPictureCard implements PictureCard{

    private List<PictureCardListener> listeners;
    private PictureCardDescriptor descriptor;

    @Override
    public void turnCard() {

    }

    @Override
    public void addCallback(PictureCardListener listener) {

    }

    @Override
    public void removeCallback(PictureCardListener listener) {

    }

    @Override
    public PictureCardDescriptor getPictureCard() {
        return null;
    }
}
