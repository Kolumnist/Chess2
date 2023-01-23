package de.hhn.it.devtools.components.game2048.junit;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.game2048.MovingDirection;
import de.hhn.it.devtools.components.game2048.provider.comparators.HorizontalComparator;
import de.hhn.it.devtools.components.game2048.provider.comparators.VerticalComparator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Test for bad cases of the VerticalComparator and the HorizontalComparator. ")
public class ComparatorTestBadCases {

  @Test
  @DisplayName("Test if exception is thrown for vertical comparator with wrong assessments. ")
  public void testCompareVertical() {
    assertThrows(IllegalParameterException.class,() -> new VerticalComparator(MovingDirection.right));
  }

  @Test
  @DisplayName("Test if exception is thrown for horizontal comparator with wrong assessments. ")
  public void testCompareHorizontal() {
    assertThrows(IllegalParameterException.class,() -> new HorizontalComparator(MovingDirection.up));
  }
}