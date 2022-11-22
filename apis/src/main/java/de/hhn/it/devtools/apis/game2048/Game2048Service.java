package de.hhn.it.devtools.apis.game2048;


import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

/**
 * This Game2048Service is an interface for the Backend of this application.
 * The Methods in the JavaFx directory can call these methods.
 */
public interface Game2048Service {

  /**
   * Adds a block with the number 2 in a random, vacant location.
   *
   * @return boolean true if methode worked correctly
   */
  boolean addBlock();

  /**
   * Deletes a specific Block from the game board.
   *
   * @param selected Block that gets deleted
   * @throws IllegalParameterException if selected is null reference
   */
  void deleteBlock(Block selected) throws IllegalParameterException;

  /**
   * Moves a Block in the given direction, until it collides with either a border or a Block.
   *
   * @param direction direction in witch the Block moves
   * @throws IllegalParameterException if move is invalid, or if one of the parameters is null
   */
  void moveBlocks(MovingDirection direction);

  /**
   * If two Blocks with the same value collide the merge into one.
   * The Block that is closest to the border of the moved direction will double in value.
   * The other Block gets deleted with the deleteBlock() methode.
   *
   * @param surviving Block closer to the border of the moved direction
   * @param dying     Block further to the border of the moved direction
   * @throws IllegalParameterException if two block cannot be merged, when value not identical, or if one of the parameters is null
   */
  void merge(Block surviving, Block dying);

  /**
   * Multiplies the value of the selected Block by 2.
   *
   * @param selected Block that gets the Value multiplied by 2
   * @throws IllegalParameterException if value of block cannot be doubled, or if parameter is null
   */
  void doubleValueOfBlock(Block selected);

  /**
   * Calculates the Sum of all Values of Blocks on the game board.
   * This represents the current score.
   *
   * @return value of current score.
   */
  int calculateSumOfAllBlockValues();

  /**
   * Updates the current score of the game.
   * Calls the calculateSumOfAllBlockValues() methode.
   */
  void updateCurrentScore();

  /**
   * Renders the Blocks immutable through calling the makeBlocksImmovable().
   * Calls updateHighScore() methode.
   * If merging 2 Blocks results in winning the game there will be no Block added.
   */
  void gameWon();

  /**
   * Renders the blocks in the game immovable. Gets used after the game is finished.
   */
  void makeBlocksImmovable();

  /**
   * Updates the high score if the current score is less than the current score.
   * Does not get updatet until game is won.
   */
  void updateHighScore();

  /**
   * Adds a listener to updates on the state of the game 2048.
   *
   * @param listener object implementing the listener interface.
   * @throws IllegalParameterException if the listener is a null reference.
   */
  void addCallback(Game2048Listener listener);

  /**
   * Adds a listener to updates on the state of the game 2048.
   *
   * @param listener listener to be removed.
   * @throws IllegalParameterException if the listener is a null reference.
   */
  void removeCallback(Game2048Listener listener);
}