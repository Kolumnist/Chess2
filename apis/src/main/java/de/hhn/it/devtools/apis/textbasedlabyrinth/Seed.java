package de.hhn.it.devtools.apis.textbasedlabyrinth;

import java.util.ArrayList;

/**
 * This is a unique number String.
 */
public class Seed {

  private final int Seed;

  public ArrayList<Integer> floorLength;
  public ArrayList<Boolean> floorDir;


  public Seed(int seedNumber) {
    this.Seed = seedNumber;
    floorLength = new ArrayList<>();
    floorDir = new ArrayList<>();

    if (seedNumber == 1){
      floorLength.add(1);
      floorLength.add(2);
      floorLength.add(3);
      floorDir.add(true);
      floorDir.add(false);
      floorDir.add(false);
    }
    else if (seedNumber == 2){
      floorLength.add(2);
      floorLength.add(3);
      floorLength.add(1);
      floorDir.add(false);
      floorDir.add(true);
      floorDir.add(false);
    }
    else if (seedNumber == 3){
      floorLength.add(3);
      floorLength.add(1);
      floorLength.add(2);
      floorDir.add(false);
      floorDir.add(false);
      floorDir.add(true);
    }
  }

  public ArrayList<Integer> getFloorLength() { return floorLength; }

  public ArrayList<Boolean> getFloorDirections() { return floorDir; }

  /**
   * Javadoc.
   *
   * @return return
   *
   */
  public int getSeed() { return Seed; }
}
