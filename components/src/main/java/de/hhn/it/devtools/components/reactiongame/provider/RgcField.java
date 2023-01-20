package de.hhn.it.devtools.components.reactiongame.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * GameField class which holds player, obstacles and aim targets.
 */
public class RgcField {

  public static int NORMAL_WIDTH = 1040; // in px
  public static int NORMAL_HEIGHT = 585; // in px

  private final int width; // px
  private final int height; // px

  private final ArrayList<RgcObstacleLine> obstacleLines = new ArrayList<>();
  private final ArrayList<RgcObstacle> obstacles = new ArrayList<>();
  private final Map<Integer, RgcObstacle> obstacleMap = new HashMap<>();

  private ArrayList<RgcAimTargetZone> aimTargetZones = new ArrayList<>();
  private ArrayList<RgcAimTarget> targets = new ArrayList<>();

  private int targetCreationCounter = -1;
  private int obstacleCreationCounter = -1;
  private int targetCount = 0;
  private Map<Integer, RgcAimTarget> targetMap = new HashMap<>();

  /**
   * Creates the basic field. (Res.: 1040x585)
   */
  public RgcField() {
    width = NORMAL_WIDTH;
    height = NORMAL_HEIGHT;

    obstacleLines.add(new RgcObstacleLine(250));
    obstacleLines.add(new RgcObstacleLine(520));
    obstacleLines.add(new RgcObstacleLine(790));

    aimTargetZones.add(new RgcAimTargetZone(RgcAimTarget.RADIUS, 60, 100,
            RgcField.NORMAL_HEIGHT - 40));
    aimTargetZones.add(new RgcAimTargetZone(RgcField.NORMAL_WIDTH - 100, 25,
            RgcField.NORMAL_WIDTH - 50, RgcField.NORMAL_HEIGHT - RgcAimTarget.RADIUS));

  }

  public ArrayList<RgcObstacle> getObstacles() {
    return obstacles;
  }

  public Map<Integer, RgcAimTarget> getTargetMap() {
    return targetMap;
  }

  public Map<Integer, RgcObstacle> getObstacleMap() {
    return obstacleMap;
  }

  public int getTargetCount() {
    return targetCount;
  }

  /**
   * Adds an obstacle to the UI. (Random obstacle line)
   *
   * @param id identifier
   */
  void addRandomObstacle(int id) {
    obstacleCreationCounter++;
    addRandomObstacle(id, obstacleCreationCounter % 3);
  }

  /**
   * Adds an obstacle to the UI.
   *
   * @param obstacleId identifier
   * @param obstacleLineId obstacleLine identifier
   */
  void addRandomObstacle(int obstacleId, int obstacleLineId) {
    RgcObstacle obstacle = obstacleLines.get(obstacleLineId).addRandomObstacle(obstacleId);
    obstacles.add(obstacleId, obstacle);
    obstacleMap.put(obstacleId, obstacle);
  }

  /**
   * Removes obstacle with the given id.
   *
   * @param obstacleId identifier
   */
  void removeObstacle(int obstacleId) {

    for (RgcObstacleLine l :
        obstacleLines) { // go throw every line...
      // ... and every obstacle list to find the right obstacle
      l.getObstacles().removeIf(o -> o.getId() == obstacleId);
    }

    obstacles.removeIf(o -> o.getId() == obstacleId);

    obstacleMap.remove(obstacleId);
  }


  /**
   * Adds an aim target.
   *
   * @param aimTargetId aim target identifier
   */
  RgcAimTarget addRandomAimTarget(int aimTargetId) {
    targetCreationCounter++;
    return addRandomAimTarget(aimTargetId, targetCreationCounter % 2);
  }

  /**
   * Adds an aim target.
   *
   * @param aimTargetId aim target identifier
   * @param aimTargetZoneId aim target zone identifier
   */
  RgcAimTarget addRandomAimTarget(int aimTargetId, int aimTargetZoneId) {

    RgcAimTarget aimTarget = aimTargetZones.get(aimTargetZoneId).addRandomAimTarget(aimTargetId);

    targetMap.put(aimTargetId, aimTarget);
    targetCount++;
    return aimTarget;
  }



  /**
   * Removes the aim target with the given id.
   *
   * @param aimTargetId identifier
   */
  void removeAimTarget(int aimTargetId) {
    for (RgcAimTargetZone z :
        aimTargetZones) {
      z.getAimTargets().removeIf(a -> a.getId() == aimTargetId);
    }

    targetMap.remove(aimTargetId);
    targetCount--;
  }

}
