package de.hhn.it.devtools.apis.battleship;

/**
 * Trigger if ship prompted to rotate or place while its already placed
 */
public class IllegalShipStateException extends Exception {

    // Contructor
    public IllegalShipStateException(String exceptionMessage) {
    }
}
