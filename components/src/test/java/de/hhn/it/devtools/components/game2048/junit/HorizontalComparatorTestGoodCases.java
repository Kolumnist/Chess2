package de.hhn.it.devtools.components.game2048.junit;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.game2048.Block;
import de.hhn.it.devtools.apis.game2048.MovingDirection;
import de.hhn.it.devtools.apis.game2048.Position;
import de.hhn.it.devtools.components.game2048.provider.comparators.HorizontalComparator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test for good cases of the HorizontalComparator. ")
public class HorizontalComparatorTestGoodCases {

  @Test
  @DisplayName("Test if horizontal comparator compares the different blocks " +
      "correctly for right. ")
  public void testCompareRight() {
    HorizontalComparator comparator = null;
    try {
      comparator = new HorizontalComparator(MovingDirection.right);
    } catch (IllegalParameterException e) {
      e.printStackTrace();
    }

    Block block1 = new Block(new Position(1,1), 2);
    Block block2 = new Block(new Position(2,1), 2);
    Block block3 = new Block(new Position(3,1), 4);

    assert comparator != null;
    assertEquals(1, comparator.compare(block1, block2));
    assertEquals(0, comparator.compare(block2, block2));
    assertEquals(-1, comparator.compare(block3, block2));
  }

  @Test
  @DisplayName("Test if horizontal comparator compares the different blocks " +
      "correctly for left. ")
  public void testCompareLeft() {
    HorizontalComparator comparator = null;
    try {
      comparator = new HorizontalComparator(MovingDirection.left);
    } catch (IllegalParameterException e) {
      e.printStackTrace();
    }

    Block block1 = new Block(new Position(1,1), 2);
    Block block2 = new Block(new Position(2,1), 2);
    Block block3 = new Block(new Position(3,1), 4);

    assert comparator != null;
    assertEquals(-1, comparator.compare(block1, block2));
    assertEquals(0, comparator.compare(block2, block2));
    assertEquals(1, comparator.compare(block3, block2));

  }
}