package de.hhn.it.devtools.apis.duckhunt;

/**
 * This Record saves information about the current games state.
 */
public record GameState(int playerScore, int ammo, int round) {}
