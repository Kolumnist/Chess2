package de.hhn.it.devtools.apis.reactiongame;



public interface ReactiongameListener {

  /**
   * Adds an aim target to the ui.
   * @param aimtarget aim target
   */
  void addAimTarget(AimTargetDescriptor aimtarget);

  /**
   * Removes the aim target with the given id.
   * @param aimTargetId identifier
   */
  void removeAimTarget(int aimTargetId);

  /**
   * Adds an obstacle to the ui.
   * @param obstacle obstacle
   */
  void addObstacle(ObstacleDescriptor obstacle);

  /**
   * Removes obstacle with the given id.
   * @param obstacleId identifier
   */
  void removeObstacle(int obstacleId);

  /**
   * Player hits obstacle.
   * @param obstacleId identifier of obstacle which is hit
   */
  void hitObstacle(int obstacleId);

  /**
   * Player loses life.
   */
  void looseLife();

  /**
   * Player hits a target.
   * @param keyPressed key pressed by player
   * @param aimTargetId hit target
   */
  void aimTargetHit(char keyPressed, int aimTargetId);
}
