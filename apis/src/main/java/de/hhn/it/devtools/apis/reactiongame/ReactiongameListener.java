package de.hhn.it.devtools.apis.reactiongame;

public interface ReactiongameListener {
  void addCircle(Circle circle);

  void removeCircle(Circle circleid);

  void addObstacle(Obstacle obstacle);

  void removeObstacle(Obstacle obstacleid);
}
