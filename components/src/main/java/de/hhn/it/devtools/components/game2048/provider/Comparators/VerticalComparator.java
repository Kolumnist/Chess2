package de.hhn.it.devtools.components.game2048.provider.Comparators;

import de.hhn.it.devtools.apis.game2048.Block;
import de.hhn.it.devtools.apis.game2048.MovingDirection;

import java.util.Comparator;

/**
 * Compares two Blocks and sorts them relative to the positions of both Blocks.
 * direction.left the smallest YPosition is going to be at index 0.
 * direction.right the greatest YPosition is going to be at index 0.
 */
public class VerticalComparator implements Comparator<Block> {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(VerticalComparator.class);

  /**
   * direction = up => upDown = true
   * direction = down => upDown = false
   */
  private boolean upDown;

  public VerticalComparator(MovingDirection direction) {
    switch (direction){
      case up ->{
        upDown = true;
      }
      case down ->{
        upDown = false;
      }
      default -> {
        logger.warn("Wrong Comparator for this direktion: " + direction);
      }
    }
  }

  @Override
  public int compare(Block o1, Block o2) {
    if (o1.getXYPosition().getYPosition() <= o2.getXYPosition().getYPosition()) {
      if (o1.getXYPosition().getYPosition() < o2.getXYPosition().getYPosition()) {
        if (upDown) {
          return 1;
        } else {
          return -1;
        }
      } else {
        logger.warn("compare: o1 = {}, o2 = {}", o1, o2);
        return 0;
      }
    } else {
      if (upDown) {
        return -1;
      } else {
        return 1;
      }
    }
  }
}
