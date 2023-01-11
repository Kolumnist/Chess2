package de.hhn.it.devtools.components.reactiongame.provider;

import de.hhn.it.devtools.apis.reactiongame.AimTargetDescriptor;

import java.util.ArrayList;
import java.util.Random;

/**
 * GameField class which holds player, obstacles and aim targets.
 */
public class RgcField {

  public static int NORMAL_WIDTH = 1280; // in px
  public static int NORMAL_HEIGHT = 720; // in px

  private final int width; // px
  private final int height; // px

  private final ArrayList<RgcObstacleLine> obstacleLines = new ArrayList<>();
  private final ArrayList<RgcObstacle> obstacles = new ArrayList<>();

  private ArrayList<RgcAimTargetZone> aimTargetZones = new ArrayList<>();
  private ArrayList<RgcAimTarget> targets = new ArrayList<>();

  public RgcField() {
    width = NORMAL_WIDTH;
    height = NORMAL_HEIGHT;

    obstacleLines.add(new RgcObstacleLine(370));
    obstacleLines.add(new RgcObstacleLine(640));
    obstacleLines.add(new RgcObstacleLine(910));

    aimTargetZones.add(new RgcAimTargetZone(0, 0, 100, RgcField.NORMAL_HEIGHT));
    aimTargetZones.add(new RgcAimTargetZone(RgcField.NORMAL_WIDTH - 100, 0,
            RgcField.NORMAL_WIDTH, RgcField.NORMAL_HEIGHT));
  }

  public ArrayList<RgcObstacle> getObstacles() {
    return obstacles;
  }

  public ArrayList<RgcAimTarget> getTargets() {
    return targets;
  }


  /**
   * Adds an obstacle to the UI. (Random obstacle line)
   *
   * @param id identifier
   */
  void addRandomObstacle(int id) {
    addRandomObstacle(id, new Random().nextInt(obstacleLines.size()));
  }

  /**
   * Adds an obstacle to the UI.
   *
   * @param id identifier
   * @param obstacleLineId obstacleLine identifier
   */
  void addRandomObstacle(int id, int obstacleLineId) {
    obstacles.add(id,
        obstacleLines.get(obstacleLineId).addRandomObstacle(id));
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
