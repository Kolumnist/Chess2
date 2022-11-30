package de.hhn.it.devtools.apis.spacefighter;
import de.hhn.it.devtools.apis.spacefighter.Exception.MissTheEnemieException;
import de.hhn.it.devtools.apis.spacefighter.Exception.NotReachedScoreException;


/*
 *  Interface for Game.
 */
public interface SpacefighterInterface {

    /**
     * Initialize a new Game.
     */
    void initializeGame();

    /**
     * Pause the game
     */
    void pauseGame();

    /**
     * Lets the player kill the enemy.
     * @param amount if the enemy gets killed, the player will get some points.
     * @throws MissTheEnemieException if the Player misses the enemy.
     */
    void killEnemies(int amount) throws MissTheEnemieException;

    /**
     * adds an Callback.
     * @param listener adds the Callback.
     */
    void addCallback(SpaceFighterListener listener);

    /**
     * adds can Callback.
     * @param listener remove the Callback.
     */
    void removeCallback(SpaceFighterListener listener);

    /**
     * gives the player the ability to move
     */
    void move(int position);

    /**
     * End the current game or user quiting the game
     */
    void endGame();
}
