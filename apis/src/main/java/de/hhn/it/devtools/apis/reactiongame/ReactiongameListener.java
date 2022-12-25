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

  void hitObstacle(int obstacleId);

  /**
   * The current amount of Player lifes.
   *
   * @param numberOfLifes Number of Player lifes.
   */
  void currentLife(int numberOfLifes);

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
   * Changes the score display
   */
  void changeScore(int score);

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
