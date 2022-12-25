package de.hhn.it.devtools.components.reactiongame.provider;

import de.hhn.it.devtools.apis.reactiongame.AimTargetDescriptor;
import de.hhn.it.devtools.apis.reactiongame.ObstacleDescriptor;

import java.util.ArrayList;

/**
 * GameField class which holds player, obstacles and aim targets.
 */
public class RelGameField {

  private int width; // px
  private int height; // px
  private ArrayList<RelObstacle> obstacles = new ArrayList<>();
  private ArrayList<RelAimTarget> targets = new ArrayList<>();

  public RelGameField() {
    width = 1600;
    height = 900;
  }

  public ArrayList<RelObstacle> getObstacles() {
    return obstacles;
  }

  public void setObstacles(ArrayList<RelObstacle> obstacles) {
    this.obstacles = obstacles;
  }

  public ArrayList<RelAimTarget> getTargets() {
    return targets;
  }

  public void setTargets(ArrayList<RelAimTarget> targets) {
    this.targets = targets;
  }

  /**
   * Adds an obstacle to the ui.
   *
   * @param obstacle obstacle
   */
  void addObstacle(ObstacleDescriptor obstacle) {

  }

  /**
   * Removes obstacle with the given id.
   *
   * @param obstacleId identifier
   */
  void removeObstacle(int obstacleId) {

  }

  /**
   * Adds an aim target to the ui.
   *
   * @param aimTarget aim target
   */
  void addAimTarget(AimTargetDescriptor aimTarget) {

  }

  /**
   * Removes the aim target with the given id.
   *
   * @param aimTargetId identifier
   */
  void removeAimTarget(int aimTargetId) {

  }

  /**
   * Player hits an obstacle.
   *
   * @param obstacleId identifier of obstacle which is hit
   */
  void hitObstacle(int obstacleId) {

  }

  /**
   * Player hits a target.
   *
   * @param keyPressed  key pressed by player
   * @param aimTargetId hit target
   */
  void aimTargetHit(char keyPressed, int aimTargetId) {

  }

  /**
   * Player pressed a key.
   *
   * @param key key
   * @throws IllegalStateException if state equals the current state
   */
  void keyPressed(char key) throws IllegalStateException {

  }

}
