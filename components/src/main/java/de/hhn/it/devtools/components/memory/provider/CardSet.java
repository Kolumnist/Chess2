package de.hhn.it.devtools.components.memory.provider;


import de.hhn.it.devtools.apis.memory.Difficulty;
import de.hhn.it.devtools.apis.memory.PictureCardDescriptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CardSet {
    Difficulty difficulty;
    List<PictureCardDescriptor> pictureCardDescriptors = new ArrayList<>();
    HashMap<Integer, String> pictureReferences = new HashMap<Integer, String>();

    /**
     * Fills the CardSet with data from local storage.
     */
    public void fillCardSet() {

    }

}
