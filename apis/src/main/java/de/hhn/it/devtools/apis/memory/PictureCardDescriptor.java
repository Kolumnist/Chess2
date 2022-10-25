package de.hhn.it.devtools.apis.memory;


/**
 * Describes a card in a memory game, its numeric reference to a
 * corresponding picture, name, ID and current state.
 * If it is an image card, it has a specific picture reference.
 * If it is a name card, it has a specific name.
 */

public class PictureCardDescriptor {

  private int pictureRef;
  private int id;
  private String name;
  private State state;

  /**
   * Constructor stating a picture reference, ID, name of the card and its default state hidden.
   *
   * @param pictureRef numeric reference to the corresponding picture of the card
   * @param id         id of the card
   * @param name       name of the card
   */
  public PictureCardDescriptor(int pictureRef, int id, String name) {
    this.pictureRef = pictureRef;
    this.id = id;
    this.name = name;
    this.state = State.HIDDEN;
  }

  /**
   * Returns the state of the card.
   *
   * @return current state of the card
   */
  public State getState() {
    return state;
  }

  /**
   * Sets the current state of the card.
   *
   * @param state current state
   */
  public void setState(State state) {
    this.state = state;
  }

  /**
   * Returns the name of the card.
   *
   * @return name of the card
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the reference for the picture of the card.
   *
   * @return reference to the picture
   */
  public int getPictureRef() {
    return pictureRef;
  }

  /**
   * Returns the ID of the card.
   *
   * @return id of the card
   */
  public int getId() {
    return id;
  }
}
