package de.hhn.it.devtools.components.memory.provider;


import de.hhn.it.devtools.apis.memory.Difficulty;
import de.hhn.it.devtools.apis.memory.PictureCardDescriptor;
import java.util.HashMap;
import java.util.List;

public class CardSet {
  private Difficulty difficulty;
  private List<PictureCardDescriptor> pictureCardDescriptors;
  private HashMap<Integer, String> pictureReferences;

  public CardSet(Difficulty difficulty, List<PictureCardDescriptor> pictureCardDescriptors,
                 HashMap<Integer, String> pictureReferences) {
    this.difficulty = difficulty;
    this.pictureCardDescriptors = pictureCardDescriptors;
    this.pictureReferences = pictureReferences;
  }

  public Difficulty getDifficulty() {
    return difficulty;
  }

  public HashMap<Integer, String> getPictureReferences() {
    return pictureReferences;
  }

  public List<PictureCardDescriptor> getPictureCardDescriptors() {
    return pictureCardDescriptors;
  }
}
