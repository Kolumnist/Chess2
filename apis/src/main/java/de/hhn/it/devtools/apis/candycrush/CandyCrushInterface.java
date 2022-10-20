package de.hhn.it.devtools.apis.candycrush;

public interface CandyCrushInterface {
    /**
     * build field with all Blocks, place Blocks on the field, use selected GameMode
     */
    void startNewGame();

    /**
     * pauses the game (freeze time & no moves allowed)
     */
    void pause();

    /**
     * creates a window and shows the Highscors of the profiles
     */
    void showStats();

    /**
     * creates a window and displays the rules as text
     */
    void openRules();
}
