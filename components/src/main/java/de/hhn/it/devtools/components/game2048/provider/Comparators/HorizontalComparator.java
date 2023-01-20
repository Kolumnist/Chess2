package de.hhn.it.devtools.components.game2048.provider.Comparators;

import de.hhn.it.devtools.apis.game2048.Block;
import de.hhn.it.devtools.apis.game2048.MovingDirection;

import java.util.Comparator;

/**
 * Compares two Blocks and sorts them relative to the positions of both Blocks.
 * direction.down the smallest YPosition is going to be at index 0.
 * direction.up the greatest YPosition is going to be at index 0.
 */
public class HorizontalComparator implements Comparator<Block> {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(HorizontalComparator.class);
  /**
   * direction = right => leftRight = true
   * direction = left => leftRight = false
   */
  private boolean leftRight;

  public HorizontalComparator(MovingDirection direction) {
    switch (direction) {
      case right -> {
        leftRight = true;
      }
      case left -> {
        leftRight = false;
      }
      default -> {
        logger.warn("Wrong Comparator for this direktion: " + direction);
      }
    }
  }

  @Override
  public int compare(Block o1, Block o2) {
    if (o1.getXYPosition().getXPosition() <= o2.getXYPosition().getXPosition()) {
      if (o1.getXYPosition().getXPosition() < o2.getXYPosition().getXPosition()) {
        if (leftRight) {
          return 1;
        } else {
          return -1;
        }
      } else {
        logger.warn("compare: o1 = {}, o2 = {}", o1, o2);
        return 0;
      }
    } else {
      if (leftRight) {
        return -1;
      } else {
        return 1;
      }
    }
  }
}
