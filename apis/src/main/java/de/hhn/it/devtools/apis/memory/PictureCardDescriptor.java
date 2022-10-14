package de.hhn.it.devtools.apis.memory;

import java.io.FileNotFoundException;

/**
 * Describes a Card in a memory game, its image, name and current state.
 */
public class PictureCardDescriptor {

    //private Image image;
    private String name;
    private State state;

    /**
     * Constructor stating the image and name of the Card.
     * @param filepath filepath to the image for the Card
     * @param name name of the Card
     * @throws FileNotFoundException if the image for the Card is not found
     */
    public PictureCardDescriptor(String filepath, String name) throws FileNotFoundException {
        //this.image = ---aus filepath---
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
