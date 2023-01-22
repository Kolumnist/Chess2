package de.hhn.it.devtools.components.game2048.junit;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.game2048.Block;
import de.hhn.it.devtools.apis.game2048.MovingDirection;
import de.hhn.it.devtools.apis.game2048.Position;
import de.hhn.it.devtools.components.game2048.provider.comparators.VerticalComparator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test for good cases of the VerticalComparator. ")
public class VerticalComparatorTestGoodCases {

  @Test
  @DisplayName("Test if vertical comparator compares the different blocks correctly" +
      " for up. ")
  public void testCompareUp() {
    VerticalComparator comparator = null;
    try {
      comparator = new VerticalComparator(MovingDirection.up);
    } catch (IllegalParameterException e) {
      e.printStackTrace();
    }

    Block block1 = new Block(new Position(1,1), 2);
    Block block2 = new Block(new Position(1,2), 2);
    Block block3 = new Block(new Position(1,3), 4);

    assert comparator != null;
    VerticalComparator finalComparator = comparator;
    assertAll(() -> assertEquals(1, finalComparator.compare(block1, block2)),
            () -> assertEquals(0, finalComparator.compare(block2, block2)),
            () -> assertEquals(-1, finalComparator.compare(block3, block2))
    );
  }

  @Test
  @DisplayName("Test if vertical comparator compares the different blocks correctly" +
      "for down. ")
  public void testCompareDown() {
    VerticalComparator comparator = null;
    try {
      comparator = new VerticalComparator(MovingDirection.down);
    } catch (IllegalParameterException e) {
      e.printStackTrace();
    }

    Block block1 = new Block(new Position(1,1), 2);
    Block block2 = new Block(new Position(1,2), 2);
    Block block3 = new Block(new Position(1,3), 4);

    assert comparator != null;
    VerticalComparator finalComparator = comparator;
    assertAll(() -> assertEquals(-1, finalComparator.compare(block1, block2)),
            () -> assertEquals(0, finalComparator.compare(block2, block2)),
            () -> assertEquals(1, finalComparator.compare(block3, block2))
    );
  }
}