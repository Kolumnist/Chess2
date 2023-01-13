package de.hhn.it.devtools.components.reactiongame.test;

import de.hhn.it.devtools.components.reactiongame.provider.RgcField;
import de.hhn.it.devtools.components.reactiongame.provider.RgcObstacle;
import de.hhn.it.devtools.components.reactiongame.provider.RgcObstacleLine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RgcObstacleLineTest {

  private RgcObstacleLine line1;
  private RgcObstacle obstacle1;
  private RgcObstacle obstacle2;
  private RgcObstacle obstacle3;

  /**
   * Sets up RgcObstacleLine objects as well as objects of some other classes to test on
   */
  @BeforeEach
  public void setup() {
    line1 = new RgcObstacleLine(200);
    obstacle1 = new RgcObstacle(1,165,100,235,500);
    obstacle2 = new RgcObstacle(2,100,250,300,720);
    obstacle3 = new RgcObstacle(3,165,0,235,500);
  }

  /**
   * Tests whether generated coordinates are in a valid range (between 0 and field height)
   */
  @Test
  public void generateYCoordinatesTest() {
    int[] coords = line1.generateYCoordsForObstacle();

    assertTrue((coords[0] <= (RgcField.NORMAL_HEIGHT - 100)) &&
        (coords[0] >= 0));

    assertTrue((coords[1] > coords[0]) && (coords[1] <= RgcField.NORMAL_HEIGHT));
  }

  /**
   * Tests whether checkLinePassable() filters out obstacles that would completely fill out a line
   */
  @Test
  public void checkLinePassableTest() {
    assertTrue(line1.checkLinePassable());

    line1.getObstacles().add(obstacle1);
    assertTrue(line1.checkLinePassable());

    line1.getObstacles().add(obstacle2);
    assertTrue(line1.checkLinePassable());

    line1.getObstacles().add(obstacle3);
    assertFalse(line1.checkLinePassable());
  }

  /**
   * Tests whether addRandomObstacle() actually adds an obstacle
   */
  @Test
  public void addRandomObstacleTest() {
    line1.addRandomObstacle(2);
    assertNotEquals(line1.getObstacles().toArray()[0], null);

    line1.addRandomObstacle(10);
    assertNotEquals(line1.getObstacles().toArray()[1], null);
  }
}
