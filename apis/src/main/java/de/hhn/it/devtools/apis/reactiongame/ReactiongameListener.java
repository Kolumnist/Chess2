package de.hhn.it.devtools.apis.reactiongame;


public interface ReactiongameListener {
  void addAimTarget(AimTarget aimtarget);

  void removeAimTarget(int aimtargetId);

  void addObstacle(Obstacle obstacle);

  void removeObstacle(int obstacleId);

  void hitObstacle();

  void looseLife();

  void aimTargetHit();
}
