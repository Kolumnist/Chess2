package de.hhn.it.devtools.apis.memory;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

import java.io.FileNotFoundException;

/**
 * Describes a card in a memory game, its image, name and current state.
 */
public class PictureCardDescriptor {

    private int pictureRef;
    private String name;
    private State state;

    /**
     * Constructor stating the image and name of the Card.
     * @param pictureRef numeric reference to the corresponding picture of the card
     * @param name name of the Card
     * @throws IllegalParameterException if the pictureRef does not exist
     */
    public PictureCardDescriptor(int pictureRef, String name) throws IllegalParameterException {
        this.pictureRef = pictureRef;
        this.name = name;
    }

    /**
     * Returns the state of the Card.
     * @return current state of the Card
     */
    public State getState() {
        return state;
    }

    /**
     * Sets the current state of the Card.
     * @param state current state
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Returns the name of the Card.
     * @return name of the Card
     */
    public String getName() {
        return name;
    }
}
