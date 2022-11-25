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
     * End the game if user lost
     */
    void GameOver();

    /**
     * Lets the player kill the enemy.
     * @param amount if the enemy gets killed, the player will get some points.
     * @throws MissTheEnemieException if the Player misses the enemy.
     */
    void killEnemies(int amount) throws MissTheEnemieException;

    /**
     * Calculates the amount of killed enemies.
     */
    void score();

    /**
     * Spawns the enemy based on the current high score.
     * @param score the result from the summed enemy.
     * @throws NotReachedScoreException if the given score was not reached.
     */
    void spwaningEnemies(int score) throws NotReachedScoreException;

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
