package de.hhn.it.devtools.components.reactiongame.test;

import de.hhn.it.devtools.components.reactiongame.provider.RgcObstacle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the methods of RgcObstacle
 */
public class RgcObstacleTest {

  private RgcObstacle obstacle1;
  private RgcObstacle obstacle2;
  private RgcObstacle obstacle3;

  /**
   * Sets up RgcObstacle objects to test with
   */
  @BeforeEach
  public void setup() {
    obstacle1 = new RgcObstacle(1,165,200,235,500);
    obstacle2 = new RgcObstacle(2,100,250,300,500);
    obstacle3 = new RgcObstacle(3,165,150,235,500);
  }

  /**
   * Tests constructor and getters
   */
  @Test
  public void constructorAndGetterTest() {
    assertEquals(obstacle1.getId(),1);
    assertEquals(obstacle1.getX1(),165);
    assertEquals(obstacle2.getY1(),250);
    assertEquals(obstacle2.getX2(),300);
    assertEquals(obstacle2.getY2(),500);
  }

  /**
   * Tests whether toString() returns correct String
   */
  @Test
  public void toStringTest() {
    assertEquals(obstacle1.toString(), "Obstacle{"
        + "Point 1: ("
        + 165
        + "|"
        + 200
        + "), Point 2: ("
        + 235
        + "|"
        + 500
        + ")");
  }

  /**
   * Tests comparator
   */
  @Test
  public void compareToTest() {
    assertEquals(obstacle1.compareTo(obstacle1), 0);
    assertEquals(obstacle1.compareTo(obstacle2), -1);
    assertEquals(obstacle1.compareTo(obstacle3), 1);
  }
}
