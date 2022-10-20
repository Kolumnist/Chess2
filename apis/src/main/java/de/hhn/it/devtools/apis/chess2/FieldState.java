package de.hhn.it.devtools.apis.chess2;

/**
 * Enum to describe the different fields and if there is something on it or not
 *
 * @author Collin, Lara, Michel
 * @version 0.1.1
 */

public enum FieldState {

    /** Is a Jail Field and has a King on it */
    JAIL_KING,
    /** Is a Jail Field and has a Queen on it */
    JAIL_QUEEN, //JAIL, we dont really need it if we make the jail a possible move
    /** (NO JAIL) A Piece of the current Player is on the Field */
    HAS_CURRENT_PIECE,
    /** (NO JAIL) Any other Piece is on the Field */
    HAS_OTHER_PIECE,
    /** Any other Field */
    FREE_FIELD

}
