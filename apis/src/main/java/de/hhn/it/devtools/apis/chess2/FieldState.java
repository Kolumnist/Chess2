package de.hhn.it.devtools.apis.chess2;

/**
 * here are all states a Field can have
 *
 * @author Collin, Lara, Michel
 * @version 0.1.1
 */

public enum FieldState {

    JAIL_KING, JAIL_QUEEN, //JAIL, we dont really need it if we make the jail a possible move
    HAS_CURRENT_PIECE, HAS_OTHER_PIECE,
    FREE_FIELD

}
