package de.hhn.it.devtools.apis.memory;


/**
 * Enum to describe the state a card of the memory game can be in.
 */
public enum State {
    /** The content of the card is hidden to the user.  */
    HIDDEN,
    /** The content of the card is visible to the user. */
    VISIBLE,
    /** The card is matched with its partner card. */
    MATCHED
}
