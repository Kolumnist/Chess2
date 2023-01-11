package de.hhn.it.devtools.components.reactiongame.provider;

import de.hhn.it.devtools.apis.reactiongame.AimTargetDescriptor;

import java.util.ArrayList;
import java.util.Random;

/**
 * GameField class which holds player, obstacles and aim targets.
 */
public class RgcField {


  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(RgcField.class);


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

    logger.info("created");
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
   * @param obstacleId identifier
   * @param obstacleLineId obstacleLine identifier
   */
  void addRandomObstacle(int obstacleId, int obstacleLineId) {
    obstacles.add(obstacleId, obstacleLines.get(obstacleLineId).addRandomObstacle(obstacleId));

    logger.info("Obstacle (id = " + obstacleId + ") added to line " + obstacleLineId);
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

    if(!(obstacles.removeIf(o -> o.getId() == obstacleId))) {
      logger.info("Obstacle not found!");
    }


    logger.info("Obstacle (id = " + obstacleId + ") removed");
  }


  /**
   * Adds an aim target.
   *
   * @param aimTargetId aim target identifier
   */
  void addRandomAimTarget(int aimTargetId) {
    addRandomAimTarget(aimTargetId, new Random().nextInt(aimTargetZones.size()));
  }

  /**
   * Adds an aim target.
   *
   * @param aimTargetId aim target identifier
   * @param aimTargetZoneId aim target zone identifier
   */
  void addRandomAimTarget(int aimTargetId, int aimTargetZoneId) {
    targets.add(aimTargetId, aimTargetZones.get(aimTargetZoneId).addRandomAimTarget(aimTargetId));

    logger.info("AimTarget (id = " + aimTargetId + ") added to zone " + aimTargetZoneId);
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

    logger.info("AimTarget (id = " + aimTargetId + ") removed");
  }

}
