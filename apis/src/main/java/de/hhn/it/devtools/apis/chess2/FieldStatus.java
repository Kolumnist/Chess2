package de.hhn.it.devtools.apis.chess2;

/**
 * here are all status that a Field can have
 *
 * @author Collin, Lara, Michel
 * @version 0.1
 */

public enum FieldStatus {

    FREE_JAIL, QUEEN_FOE_OCCUPIED_JAIL, KING_FOE_OCCUPIED_JAIL,
    QUEEN_PAL_OCCUPIED_JAIL, KING_PAL_OCCUPIED_JAIL,

    //with GRAY I mean not RED(any other colors are fine too)
    FREE_GRAY, FREE_RED, FOE_OCCUPIED_GRAY, FOE_OCCUPIED_RED,
    PAL_OCCUPIED_GRAY, PAL_OCCUPIED_RED,

}
