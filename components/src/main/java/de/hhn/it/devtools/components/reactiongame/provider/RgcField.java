package de.hhn.it.devtools.components.reactiongame.provider;

import java.util.ArrayList;
import java.util.Random;

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

  private ArrayList<RgcAimTargetZone> aimTargetZones = new ArrayList<>();
  private ArrayList<RgcAimTarget> targets = new ArrayList<>();

  /**
   * Creates the basic field. (Res.: 1040x585)
   */
  public RgcField() {
    width = NORMAL_WIDTH;
    height = NORMAL_HEIGHT;

    obstacleLines.add(new RgcObstacleLine(250));
    obstacleLines.add(new RgcObstacleLine(520));
    obstacleLines.add(new RgcObstacleLine(790));

    aimTargetZones.add(new RgcAimTargetZone(25, 25, 100, RgcField.NORMAL_HEIGHT - 25));
    aimTargetZones.add(new RgcAimTargetZone(RgcField.NORMAL_WIDTH - 100, 25,
            RgcField.NORMAL_WIDTH - 25, RgcField.NORMAL_HEIGHT - 25));

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
  }


  /**
   * Adds an aim target.
   *
   * @param aimTargetId aim target identifier
   */
  RgcAimTarget addRandomAimTarget(int aimTargetId) {
    return addRandomAimTarget(aimTargetId,
        aimTargetZones.get(0).getAimTargets().size() > aimTargetZones.get(1).getAimTargets().size()
            ? 1 : 0);
  }

  /**
   * Adds an aim target.
   *
   * @param aimTargetId aim target identifier
   * @param aimTargetZoneId aim target zone identifier
   */
  RgcAimTarget addRandomAimTarget(int aimTargetId, int aimTargetZoneId) {

    RgcAimTarget aimTarget = aimTargetZones.get(aimTargetZoneId).addRandomAimTarget(aimTargetId);

    targets.add(aimTargetId, aimTarget);

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

    targets.removeIf(target -> target.getId() == aimTargetId);

  }

}
