package de.hhn.it.devtools.components.game2048.provider.Comparators;

import de.hhn.it.devtools.apis.game2048.Block;

import java.util.Comparator;

/**
 * Compares two Blocks and sorts them relative two the positions of both Blocks.
 * The smallest YPosition is going to be at index 0.
 */
public class DownComparator implements Comparator<Block> {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(DownComparator.class);

  @Override
  public int compare(Block o1, Block o2) {
    if (o1.getXYPosition().getYPosition() <= o2.getXYPosition().getYPosition()) {
      if (o1.getXYPosition().getYPosition() < o2.getXYPosition().getYPosition()) {
        return -1;
      } else {
        logger.warn("compare: o1 = {}, o2 = {}", o1, o2);
        return 0;
      }
    } else {
      return 1;
    }
  }
}
