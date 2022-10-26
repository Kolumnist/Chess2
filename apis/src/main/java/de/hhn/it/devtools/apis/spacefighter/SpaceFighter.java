package de.hhn.it.devtools.apis.spacefighter;

import de.hhn.it.devtools.apis.spacefighter.Exception.NotReachedScoreException;

public class SpaceFighter {
    public int spawningNormalEnemy;
    public int spawningSpecialEnemy;
    public int spwaningPlayer;
    static int beginningScore;

    public void initializeGame () { }

    void ingameMenu(String resume, String quit) {}

    void mainMenu(String start, String close, String options) {}

    void spwaningEnemies(int score) throws NotReachedScoreException {}
}
