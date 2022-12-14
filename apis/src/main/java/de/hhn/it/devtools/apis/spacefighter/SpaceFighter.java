package de.hhn.it.devtools.apis.spacefighter;

import de.hhn.it.devtools.apis.spacefighter.Exception.NoMoreRoomException;
import de.hhn.it.devtools.apis.spacefighter.Exception.NotReachedScoreException;

public class SpaceFighter {
    public int position;
    public int spawningNormalEnemy;
    public int spawningSpecialEnemy;
    public int spwaningPlayer;
    static int beginningScore;

    public void initializeGame () { }

    void ingameMenu(String resume, String quit) {}

    void mainMenu(String start, String close, String options) {}

    void spwaningEnemies(int score) throws NotReachedScoreException {}

    /**
     * Lets the player move the figure.
     *
     * @param position is the position of the figure.
     * @throws NoMoreRoomException if the border on the right or on the left side is reached.
     */
    void moveCharacter(int position) throws NoMoreRoomException {

    }
}
