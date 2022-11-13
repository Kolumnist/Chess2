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
   * Player hits obstacle.
   *
   * @param obstacleId identifier of obstacle which is hit
   * @throws IllegalStateException if the game is running
   */
  void hitObstacle(int obstacleId) throws IllegalStateException;

  /**
   * Player loses life.
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
  void gameStateChanged(GameState state) throws IllegalStateException;

}
