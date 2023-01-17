package de.hhn.it.devtools.components.duckhunt;

import java.util.Random;

/**
 * Random generator with range.
 */
public class RangedRandom extends Random {

  /**
   * Creates a random integer between the given min and max values.
   *
   * @param min value (inclusive) //TODO check if inclusive or exclusive
   * @param max value (inclusive) //TODO ^^
   * @return the random integer
   */
  public int randomInt(int min, int max) {
    return nextInt(max - min) + min;
  }
}
