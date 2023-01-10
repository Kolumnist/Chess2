package de.hhn.it.devtools.components.reactiongame.provider;

import de.hhn.it.devtools.apis.reactiongame.AimTargetDescriptor;
import de.hhn.it.devtools.apis.reactiongame.ObstacleDescriptor;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * GameField class which holds player, obstacles and aim targets.
 */
public class RelGameField {

  public static int NORMAL_WIDTH = 1280; // in px
  public static int NORMAL_HEIGHT = 720; // in px

  private int width; // px
  private int height; // px

  private ArrayList<RelObstacleLine> obstacleLines = new ArrayList<>();
  private ArrayList<RelObstacle> obstacles = new ArrayList<>();

  private ArrayList<RelAimTargetZone> aimTargetZones = new ArrayList<>();
  private ArrayList<RelAimTarget> targets = new ArrayList<>();

  public RelGameField() {
    width = NORMAL_WIDTH;
    height = NORMAL_HEIGHT;

    obstacleLines.add(new RelObstacleLine(370));
    obstacleLines.add(new RelObstacleLine(640));
    obstacleLines.add(new RelObstacleLine(910));

    aimTargetZones.add(new RelAimTargetZone(0, 0, 100, RelGameField.NORMAL_HEIGHT));
    aimTargetZones.add(new RelAimTargetZone(RelGameField.NORMAL_WIDTH - 100, 0,
            RelGameField.NORMAL_WIDTH, RelGameField.NORMAL_HEIGHT));
  }

  public ArrayList<RelObstacle> getObstacles() {
    return obstacles;
  }

    public ArrayList<RelAimTarget> getTargets() {
    return targets;
  }


  /**
   * Adds an obstacle to the ui.
   *
   */
  void addRandomObstacle() {
    
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



}
