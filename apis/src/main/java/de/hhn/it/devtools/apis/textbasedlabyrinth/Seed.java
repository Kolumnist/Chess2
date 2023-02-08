package de.hhn.it.devtools.apis.textbasedlabyrinth;

import java.util.ArrayList;

/**
 * This is a unique number String.
 */
public class Seed {

  private ArrayList<Integer> seed;

  /**
   * The seed should be an array list with integer values of the length 2.
   *
   * @param seedInteger the numbers of the seed, contained in an ArrayList.
   * @throws InvalidSeedException when the list is smaller than one or bigger then the maximum length (2).
   */
  public Seed(ArrayList<Integer> seedInteger) throws InvalidSeedException {
    if (seedInteger.size() < 1) {
      throw new InvalidSeedException("Array is too small.");
    }
    if (seedInteger.size() > 2) {
      throw new InvalidSeedException("Array is longer than permitted.");
    }
    seed = seedInteger;
    if (seed.size() < 2) {
      while (seed.size() < 2) {
        seed.add(seed.size(), 0);
      }
    }
  }

  public ArrayList<Integer> getSeed() {
    return seed;
  }
}




