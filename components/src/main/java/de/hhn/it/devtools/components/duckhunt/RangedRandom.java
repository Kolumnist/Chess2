package de.hhn.it.devtools.components.duckhunt;

import java.util.Random;

/**
 * Random generator with range.
 */
public class RangedRandom extends Random {

  public int randomInt(int min, int max) {
    return nextInt(max - min) + min;
  }
}
