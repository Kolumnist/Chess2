package de.hhn.it.devtools.components.game2048.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.game2048.Block;
import de.hhn.it.devtools.apis.game2048.MovingDirection;
import de.hhn.it.devtools.apis.game2048.Position;
import de.hhn.it.devtools.components.game2048.provider.Comparators.VerticalComparator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test for bad cases of the VerticalComparator. ")
public class VerticalComparatorTestBadCases {

  @Test
  @DisplayName("Test if vertical comparator compares the different blocks correctly" +
      " for these wrong assessments. ")
  public void testCompareUp() throws IllegalParameterException {
    try {
      VerticalComparator comparator = new VerticalComparator(MovingDirection.right);
      System.out.println("Fail, should have thrown exception");

      Block block1 = new Block(new Position(1,1), 2);
      Block block2 = new Block(new Position(1,2), 2);
      Block block3 = new Block(new Position(1,3), 4);

      assertEquals(-1, comparator.compare(block1, block2));
      assertEquals(0, comparator.compare(block2, block2));
      assertEquals(1, comparator.compare(block3, block2));
    } catch (IllegalParameterException e) {
      assertEquals(e, e);

  }

  }

}