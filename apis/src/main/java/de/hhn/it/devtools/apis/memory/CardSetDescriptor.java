package de.hhn.it.devtools.apis.memory;

import java.util.HashMap;
import java.util.List;

public class CardSetDescriptor {
  private Difficulty difficulty;
  private PictureCardDescriptor[] pictureCardDescriptors;
  private HashMap<Integer, String> pictureReferences;

  public CardSetDescriptor(Difficulty difficulty, PictureCardDescriptor[] pictureCardDescriptors,
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

  public PictureCardDescriptor[] getPictureCardDescriptors() {
    return pictureCardDescriptors;
  }

  public void setDifficulty(Difficulty difficulty) {
    this.difficulty = difficulty;
  }

  public void setPictureCardDescriptors(PictureCardDescriptor[] pictureCardDescriptors) {
    this.pictureCardDescriptors = pictureCardDescriptors;
  }

  public void setPictureReferences(HashMap<Integer, String> pictureReferences) {
    this.pictureReferences = pictureReferences;
  }
}
