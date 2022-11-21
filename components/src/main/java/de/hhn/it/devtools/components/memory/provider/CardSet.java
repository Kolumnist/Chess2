package de.hhn.it.devtools.components.memory.provider;


import de.hhn.it.devtools.apis.memory.Difficulty;
import de.hhn.it.devtools.apis.memory.PictureCardDescriptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CardSet {
    Difficulty difficulty;
    List<PictureCardDescriptor> pictureCardDescriptors;
    HashMap<Integer, String> pictureReferences;

    public CardSet(Difficulty difficulty, List<PictureCardDescriptor> pictureCardDescriptors, HashMap<Integer, String> pictureReferences){
        this.difficulty = difficulty;
        this.pictureCardDescriptors = pictureCardDescriptors;
        this.pictureReferences = pictureReferences;
    }

}
