package de.hhn.it.devtools.components.game2048.junit;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.game2048.MovingDirection;
import de.hhn.it.devtools.components.game2048.provider.Comparators.HorizontalComparator;
import de.hhn.it.devtools.components.game2048.provider.Comparators.VerticalComparator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Test for bad cases of the VerticalComparator. ")
public class ComparatorTestBadCases {

  @Test
  @DisplayName("Test if exception is thrown for vertical comparator with wrong assessments. ")
  public void testCompareVertical() throws IllegalParameterException {
    boolean thrown = false;
    try {
      VerticalComparator comparator = new VerticalComparator(MovingDirection.right);
    } catch (IllegalParameterException e) {
      thrown = true;
    }
    assertTrue(thrown);

  }  @Test
  @DisplayName("Test if exception is thrown for horizontal comparator with wrong assessments. ")
  public void testCompareHorizontal() throws IllegalParameterException {
    boolean thrown = false;
    try {
      HorizontalComparator comparator = new HorizontalComparator(MovingDirection.down);
    } catch (IllegalParameterException e) {
      thrown = true;
    }
    assertTrue(thrown);
  }
}