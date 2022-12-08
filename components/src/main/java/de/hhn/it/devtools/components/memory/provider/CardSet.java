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

  /**
   * Returns the difficulty of the card set.
   *
   * @return difficulty of the card set
   */
  public Difficulty getDifficulty() {
    return difficulty;
  }

  /**
   * Returns the picture references stored in the card set.
   *
   * @return HashMap that stores the picture references
   */
  public HashMap<Integer, String> getPictureReferences() {
    return pictureReferences;
  }

  /**
   * Returns the picture cards stored in the card set.
   *
   * @return List of the picture cards
   */
  public List<PictureCardDescriptor> getPictureCardDescriptors() {
    return pictureCardDescriptors;
  }
}
