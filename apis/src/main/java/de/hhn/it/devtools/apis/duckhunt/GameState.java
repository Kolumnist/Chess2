package de.hhn.it.devtools.apis.duckhunt;

/**
 * This Record saves information about the current games state.
 *
 * @author Jannik Döring
 * @version 1.0
 */
public record GameState(int playerScore, int score, int level) {}
