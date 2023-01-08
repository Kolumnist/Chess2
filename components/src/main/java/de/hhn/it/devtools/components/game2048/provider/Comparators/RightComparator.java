package de.hhn.it.devtools.components.game2048.provider.Comparators;

import de.hhn.it.devtools.apis.game2048.Block;

import java.util.Comparator;

/**
 * Compares two Blocks and sorts them relative two the positions of both Blocks.
 * The greatest XPosition is going to be at index 0.
 */
public class RightComparator implements Comparator<Block> {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(RightComparator.class);

  @Override
  public int compare(Block o1, Block o2) {
    if (o1.getXYPosition().getXPosition() <= o2.getXYPosition().getXPosition()) {
      if (o1.getXYPosition().getXPosition() < o2.getXYPosition().getXPosition()) {
        return 1;
      } else {
        logger.warn("compare: o1 = {}, o2 = {}", o1, o2);
        return 0;
      }
    } else {
      return -1;
    }
  }
}
