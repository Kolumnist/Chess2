package de.hhn.it.devtools.apis.textbasedlabyrinth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * This is a unique number String.
 */
public class Seed {

  private int seed;

  public ArrayList<Integer> floorLength;
  public ArrayList<Boolean> floorDir;

  /**
   * Gets the Seed for the actual Game.
   *
   * @param seedNumber gets the seed number 1,2 or 3
   */
  public Seed(int seedNumber) {
    this.floorLength = new ArrayList<>();
    this.floorDir = new ArrayList<>();
    this.seed = seedNumber;

    if (seed > 3) {
      seed = 3;
    }

    int secondSeed = seed + 1;
    int thirdSeed = secondSeed + 1;

    if (secondSeed > 3) {
      secondSeed = secondSeed - 3;
    }

    if (thirdSeed > 3) {
      thirdSeed = thirdSeed - 3;
    }

    floorLength.add(seed);
    floorLength.add(secondSeed);
    floorLength.add(thirdSeed);
    floorDir.add(true);
    floorDir.add(false);
    floorDir.add(false);

    while (!floorDir.get(seed)) {
      Collections.shuffle(floorDir);
    }
  }

  /**
   * Constructor for Seed without any input.
   */
  public Seed() {
    this.floorLength = new ArrayList<>();
    this.floorDir = new ArrayList<>();
    Random rand = new Random();
    this.seed = rand.nextInt() + 1;

    if (seed > 3) {
      seed = 3;
    }

    int secondSeed = seed + 1;
    int thirdSeed = secondSeed + 1;

    if (secondSeed > 3) {
      secondSeed = secondSeed - 3;
    }

    if (thirdSeed > 3) {
      thirdSeed = thirdSeed - 3;
    }

    floorLength.add(seed);
    floorLength.add(secondSeed);
    floorLength.add(thirdSeed);
    floorDir.add(true);
    floorDir.add(false);
    floorDir.add(false);

    while (!floorDir.get(seed)) {
      Collections.shuffle(floorDir);
    }

  }

  public ArrayList<Integer> getFloorLength() {
    return floorLength;
  }

  public ArrayList<Boolean> getFloorDirections() {
    return floorDir;
  }

  /**
   * Javadoc.
   *
   * @return return
   *
   */
  public int getSeed() {
    return seed;
  }
}
