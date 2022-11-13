package de.hhn.it.devtools.apis.reactiongame;


/**
 * Listener to give front and backend updates about the user input.
 */
public interface ReactiongameListener {

  /**
   * Adds an aim target to the ui.
   *
   * @param aimTarget aim target
   * @throws IllegalStateException if game is running
   */
  void addAimTarget(AimTargetDescriptor aimTarget) throws IllegalStateException;

  /**
   * Removes the aim target with the given id.
   *
   * @param aimTargetId identifier
   * @throws IllegalStateException if game is running
   */
  void removeAimTarget(int aimTargetId) throws IllegalStateException;

  /**
   * Adds an obstacle to the ui.
   *
   * @param obstacle obstacle
   * @throws IllegalStateException if the game is running
   */
  void addObstacle(ObstacleDescriptor obstacle) throws IllegalStateException;

  /**
   * Removes obstacle with the given id.
   *
   * @param obstacleId identifier
   * @throws IllegalStateException if the game is running
   */
  void removeObstacle(int obstacleId) throws IllegalStateException;

  /**
   * Player hits an obstacle.
   *
   * @param obstacleId identifier of obstacle which is hit
   * @throws IllegalStateException if the game is running
   */
  void hitObstacle(int obstacleId) throws IllegalStateException;

  /**
   * Player loses a life.
   *
   * @throws IllegalStateException if game is running
   */
  void looseLife() throws IllegalStateException;

  /**
   * Player hits a target.
   *
   * @param keyPressed key pressed by player
   * @param aimTargetId hit target
   * @throws IllegalStateException if the game is running
   */
  void aimTargetHit(char keyPressed, int aimTargetId) throws IllegalStateException;

  /**
   * Changes the state of the game
   *
   * @param state new state
   * @throws IllegalStateException if state equals the current state
   */
  void changeGameState(GameState state) throws IllegalStateException;

  /**
   * Changes the x, y values of the courser position
   *
   * @param x position
   * @param y position
   */
  void updateCourserPosition(int x, int y);

  /**
   * Reacts if the player presses a key.
   *
   * @param key key pressed
   */
  void keyPressed(char key);

  /**
   * Ends the run.
   */
  void endRun();

}
