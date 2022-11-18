package de.hhn.it.devtools.apis.reactiongame;


/**
 * Listener to give front and backend updates about the user input.
 */
public interface ReactiongameListener {

  /**
   * Adds an aim target to the ui.
   *
   * @param aimTarget aim target
   */
  void addAimTarget(AimTargetDescriptor aimTarget);

  /**
   * Removes the aim target with the given id.
   *
   * @param aimTargetId identifier
   */
  void removeAimTarget(int aimTargetId);

  /**
   * Adds an obstacle to the ui.
   *
   * @param obstacle obstacle
   */
  void addObstacle(ObstacleDescriptor obstacle);

  /**
   * Removes obstacle with the given id.
   *
   * @param obstacleId identifier
   */
  void removeObstacle(int obstacleId);

  /**
   * Player hits an obstacle.
   *
   * @param obstacleId identifier of obstacle which is hit
   */
  void hitObstacle(int obstacleId);

  /**
   * Player loses a life.
   */
  void looseLife();

  /**
   * Player hits a target.
   *
   * @param keyPressed key pressed by player
   * @param aimTargetId hit target
   */
  void aimTargetHit(char keyPressed, int aimTargetId);

  /**
   * Changes the state of the game
   *
   * @param state new state
   */
  void changeGameState(GameState state);

  /**
   * Pauses the run and the timer.
   */
  void pauseRun();

  /**
   * Continues a paused run.
   */
  void continueRun();

  /**
   * When a player lost all lifes, the game is over and the highscore-table is shown.
   */
  void gameOver();
}
