package de.hhn.it.devtools.apis.spacefighter;
import de.hhn.it.devtools.apis.spacefighter.Exception.NoMoreRoomException;
import de.hhn.it.devtools.apis.spacefighter.Exception.IllegalParameterExeption;
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
     * Lets the player move the figure.
     * @param x is the position of the figure.
     * @throws NoMoreRoomException if the border on the right or on the left side is reached.
     */
    void moveCharacter(int x) throws NoMoreRoomException;

    /**
     * Lets the player start or close the game.
     * @param start Lets the player start the game.
     * @param close Lets the player close the game.
     * @param options Lets the player change specific options.
     */
    void mainMenu(String start, String close, String options);

    /**
     * Lets the player pause the game.
     * @param resume Lets the player resume the game.
     * @param quit Lets the player quit the game.
     */
    void ingameMenu(String resume, String quit);

    /**
     * Lets the player kill the enemy.
     * @param amount if the enemy gets killed, the player will get some points.
     * @throws MissTheEnemieException if the Player misses the enemy.
     */
    void killEnemies(int amount) throws MissTheEnemieException;

    /**
     * Calculates the amount of killed enemies.
     * @param amount The amount of killed enemies.
     * @throws MissTheEnemieException if the player misses an enemy.
     */
    void score(int amount) throws MissTheEnemieException;

    /**
     * Spawns the enemy based on the current high score.
     * @param score the result from the summed enemy.
     * @throws NotReachedScoreException if the given score was not reached.
     */
    void spwaningEnemies(int score) throws NotReachedScoreException;
}
