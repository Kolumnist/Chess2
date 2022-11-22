package de.hhn.it.devtools.apis.candycrush;

public interface CandyCrush {
    /**
     * create a new user profile
     * @param name name of the Profile
     */
    void createProfile(String name);

    /**
     * set the name of the new profile
     * @param ID        ID of the profile
     * @param name      name of the profile
     */
    void setProfileName(int ID, String name);

    /**
     * delete the selected user profile
     * @param id ID of the profile
     */
    void deleteProfile(int id)throws ProfileNotFoundException;

    /**
     * choose the profile you want to play with
     * @param a profile
     */
    void chooseProfile(Profile a);

    /**
     * build field with all Blocks, place Blocks on the field, use selected GameMode
     */
    void startNewGame() throws NoProfileSelectedException;

    /**
     * pause the game (freeze time & no moves allowed)
     */
    void pause();

    /**
     * create a window and shows the Highscors of the profiles
     */
    void showStats();

    /**
     * create a window and displays the rules as text
     */
    void openRules();

    /**
     * swap the two selected blocks
     * @param row1  row of the first block
     * @param col1  collum of the first block
     * @param row2  row of the second block
     * @param col2  collum of the second block
     */
    void swapBlocks(int row1, int col1, int row2, int col2);

    /**
     * mark the selected block
     * @param row   row of the block
     * @param col1  collum of the block
     */
    void markBlock(int row, int col1);

    /**
     * cxhange the Gamemode
     * @param mode param
     */
    void setMode(GameMode mode);
}
