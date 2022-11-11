package de.hhn.it.devtools.apis.reactiongame;



public interface ReactiongameListener {
  void addAimTarget(AimTargetDescriptor aimtarget);

  void removeAimTarget(int aimTargetId);

  void addObstacle(ObstacleDescriptor obstacle);

  void removeObstacle(int obstacleId);

  void hitObstacle();

  void looseLife();

  void aimTargetHit();
}
