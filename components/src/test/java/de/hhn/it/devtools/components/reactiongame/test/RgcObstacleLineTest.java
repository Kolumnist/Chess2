package de.hhn.it.devtools.components.reactiongame.test;

import de.hhn.it.devtools.components.reactiongame.provider.RgcField;
import de.hhn.it.devtools.components.reactiongame.provider.RgcObstacleLine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RgcObstacleLineTest {

  private RgcField field;
  private RgcObstacleLine line1;

  /**
   * Sets up RgcObstacleLine objects as well as objects of some other classes to test on
   */
  @BeforeEach
  public void setup() {
    field = new RgcField();
    line1 = new RgcObstacleLine(200);
  }

  /**
   * Tests whether generated coordinates are in a valid range (between 0 and field height)
   */
  @Test
  public void generateYCoordinatesTest() {
    int[] coords = line1.generateYCoordsForObstacle();

    assertTrue((coords[0] <= (field.NORMAL_HEIGHT - 100)) &&
        (coords[0] >= 0));

    assertTrue((coords[1] > coords[0]) && (coords[1] <= field.NORMAL_HEIGHT));
  }
}
