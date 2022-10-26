package de.hhn.it.devtools.apis.battleship;

public enum GameState {
    /* Players are placing their ships */
    PLACINGSHIPS,
    /* Players are shooting at each others fields */
    FIRINGSHOTS,
    /* One player has no remaining ships */
    GAMEOVER;
}
