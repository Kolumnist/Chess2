package de.hhn.it.devtools.components.reactiongame.provider;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents an imaginary vertical line on the field which holds the obstacles.
 */
public class RgcObstacleLine {

  private final int x;

  private final ArrayList<RgcObstacle> obstacles;

  public RgcObstacleLine(int x) {
    this.x = x;
    obstacles = new ArrayList<>();
  }

  public ArrayList<RgcObstacle> getObstacles() {
    return obstacles;
  }

  /**
   * Adds a random obstacle to the line.
   *
   * @param id identifier of the new obstacle
   * @return the new obstacle
   */
  public RgcObstacle addRandomObstacle(int id) {

    RgcObstacle obstacle;
    int[] yCoords = generateYCoordsForObstacle();

    obstacle = new RgcObstacle(id, x - RgcObstacle.WIDTH / 2, yCoords[0],
        x + RgcObstacle.WIDTH / 2, yCoords[1]);

    obstacles.add(obstacle);

    if (!checkLinePassable()) {
      obstacles.remove(obstacle);
      addRandomObstacle(id);
    }

    return obstacle;
  }

  /**
   * Generates a set of y coordinates for an obstacle.
   *
   * @return set of coordinates
   */
  public int[] generateYCoordsForObstacle() {
    int[] out = new int[2];

    out[0] = new Random().nextInt(RgcField.NORMAL_HEIGHT - 100);

    do {
      int temp = new Random().nextInt((RgcField.NORMAL_HEIGHT / 3) + 100) + 100;
      out[1] = temp + out[0];
    } while (out[1] > RgcField.NORMAL_HEIGHT);

    return out;
  }


  /**
   * Checks if the line has gaps between the obstacles.
   *
   * @return true or false
   */
  public boolean checkLinePassable() {

    if (obstacles.size() == 0) {
      return true; // No obstacles
    }

    // Sort obstacles on the basis of the highest y value.
    obstacles.sort((o1, o2) -> {
      if (o1.getY1() == o2.getY1()) {
        return 0;
      }
      if (o1.getY1() > o2.getY1()) {
        return 1;
      } else {
        return -1;
      }
    });

    //check for highest
    if (obstacles.get(0).getY1() != 0) {
      return true; // Gap on top
    }

    for (int i = 0; i < obstacles.toArray().length - 1; i++) {
      if (obstacles.get(i).getY2() < obstacles.get(i + 1).getY1()) {
        return true; // there is a gap between two obstacles
      }
    }

    //check for lowest
    obstacles.sort((o1, o2) -> {
      if (o1.getY2() == o2.getY2()) {
        return 0;
      }
      if (o1.getY2() > o2.getY2()) {
        return -1;
      } else {
        return 1;
      }
    });

    if (obstacles.get(0).getY2() != RgcField.NORMAL_HEIGHT) {
      return true; // Gap on bottom
    }

    return false; // no gap is found
  }
}
