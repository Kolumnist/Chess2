package de.hhn.it.devtools.apis.candycrush;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

public interface CandyCrushService {
  /**
   * create a new user profile
   *
   * @param profile name of the Profile
   */
  void createProfile(Profile profile, GameMode gameMode);

  /**
   * set the name of the new profile
   *
   * @param ID   ID of the profile
   * @param name name of the profile
   */
  void setProfileName(int ID, String name);

  /**
   * delete the selected user profile
   *
   * @param profile of the profile
   */
  void deleteProfile(Profile profile) throws ProfileNotFoundException;

  /**
   * choose the profile you want to play with
   *
   * @param a profile
   */
  void chooseProfile(Profile a);
  /**
   * Adds a listener to get updates on the state of the game.
   *
   * @param listener object implementing the listener interface
   * @throws IllegalParameterException if the listener is null
   */
  void addCallBack(CandyCrushListener listener) throws IllegalParameterException;

  /**
   * Removes a listener.
   *
   * @param listener listener to be removed
   * @throws IllegalParameterException if  the listener is null
   */
  void removeCallback(CandyCrushListener listener) throws IllegalParameterException;


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
   * @throws IllegalParameterException when no Highscore is found.
   * @param listener listener returns Highscore.
   */
  void showStats(CandyCrushListener listener) throws IllegalParameterException;

  /**
   * swap the two selected blocks
   *
   * @param row1 row of the first block
   * @param col1 collum of the first block
   * @param row2 row of the second block
   * @param col2 collum of the second block
   */
  void swapBlocks(int row1, int col1, int row2, int col2);

  /**
   * mark the selected block
   *
   * @param row  row of the block
   * @param col1 collum of the block
   */
  void markBlock(int row, int col1);

  /**
   * cxhange the Gamemode
   *
   * @param mode param
   */
  void setMode(GameMode mode);


  /**
   * Creates a new game of the chosen difficulty.
   * Stops any running games, to create a new one.
   *
   * @param gameMode the difficulty of the new game
   */
  GameBoard createGame(Profile profile, GameMode gameMode);

  void addCallBack();
}
