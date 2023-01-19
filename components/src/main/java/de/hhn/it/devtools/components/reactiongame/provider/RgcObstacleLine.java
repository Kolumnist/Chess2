package de.hhn.it.devtools.components.reactiongame.provider;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/**
 * This class represents an imaginary vertical line on the field which holds the obstacles.
 */
public class RgcObstacleLine {

  private final int xposition;

  private final ArrayList<RgcObstacle> obstacles;

  public RgcObstacleLine(int x) {
    this.xposition = x;
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
    int[] ycoords = generateYcoordsForObstacle();

    obstacle = new RgcObstacle(id, xposition - RgcObstacle.WIDTH / 2, ycoords[0],
        xposition + RgcObstacle.WIDTH / 2, ycoords[1]);

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
   * @return array of coordinates
   */
  public int[] generateYcoordsForObstacle() {
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
    obstacles.sort(Comparator.comparingInt(RgcObstacle::getY1));

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
    obstacles.sort((o1, o2) -> Integer.compare(o2.getY2(), o1.getY2()));

    return obstacles.get(0).getY2() != RgcField.NORMAL_HEIGHT; // Gap on bottom
// no gap is found
  }
}
