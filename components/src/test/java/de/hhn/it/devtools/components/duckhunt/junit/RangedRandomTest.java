package de.hhn.it.devtools.components.duckhunt.junit;

import de.hhn.it.devtools.components.duckhunt.RangedRandom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RangedRandomTest {

  @Test
  @DisplayName("randomInt test")
  void randomIntTest() {
    RangedRandom randTest = new RangedRandom();
    randTest.setSeed(123456789);
    int[] expectedNumbers = {7, 0, 5, 4};

    int[] givenNumbers = new int[4];
    for(int i=0; i<4; i++) {
      givenNumbers[i] = randTest.randomInt(0,9);
    }
    assertArrayEquals(expectedNumbers, givenNumbers);
    randTest = new RangedRandom();
    assertTrue(randTest.randomInt(0,1) == 0);
  }
}
