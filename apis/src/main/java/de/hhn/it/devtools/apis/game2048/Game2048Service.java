package de.hhn.it.devtools.apis.game2048;


import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

/**
 * This Game2048Service is an interface for the Backend of this application.
 * The Methods residing in the JavaFx directory can call these methods.
 */
public interface Game2048Service {

  /**
   * The methode will be called to start a new game.
   * This happens when the Application starts to run or when the reset Button in the Frontend gets
   * pressed. This Button will be used after a game is won or if an unfinished game should be restarted.
   * <p>
   * It checks if there are Blocks on the Game board, they will be deleted.
   * Afterwards 2 Blocks will be placed on the Game board.
   */
  void initialisation();

  /**
   * Methode will be called after one of the arrow keys got pressed.
   * The Frontend will be notified through a listener and calls this methode in
   * the Backend.
   * This Methode moves a Block in the given direction, until it collides with either a border
   * or a Block.
   *
   * @param direction direction in witch the Block moves
   * @throws IllegalParameterException if parameter is null
   */
  void moveAllBlocks(MovingDirection direction) throws IllegalParameterException;


  /**
   * Adds a listener to updates on the state of the game 2048.
   * When the Application gets started, a Listener will be added
   * too ensure that the Frontend (Package javafx) gets informed, how the new
   * Data (game-board, current Score and high Score) look like.
   * Also notifies GameListener so that a new State can be acquired immediately after
   * adding a new Game2048Service
   *
   * @param listener object implementing the listener interface.
   * @throws IllegalParameterException if the listener is a null reference or
   *                                   if there is already another Listener registered
   */
  void addCallback(Game2048Listener listener) throws IllegalParameterException;

  /**
   * Removes a listener that updates on the state of the game 2048.
   *
   * @param listener listener to be removed.
   * @throws IllegalParameterException if the listener is a null reference.
   */
  void removeCallback(Game2048Listener listener) throws IllegalParameterException;
}