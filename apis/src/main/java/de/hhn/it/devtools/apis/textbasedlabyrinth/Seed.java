package de.hhn.it.devtools.apis.textbasedlabyrinth;

import java.util.ArrayList;

/**
 * This is a unique number String.
 */
public class Seed {

  private final int Seed;

  public ArrayList<Integer> floorLength;
  public ArrayList<Boolean> floorDirections;


  public Seed(int seedNumber) {
    this.Seed = seedNumber;
    floorLength = new ArrayList<>();
    floorDirections = new ArrayList<>();

    if (seedNumber == 1){
      floorLength.add(1);
      floorLength.add(2);
      floorLength.add(3);
      floorDirections.add(true);
      floorDirections.add(false);
      floorDirections.add(false);
    }
    else if (seedNumber == 2){
      floorLength.add(2);
      floorLength.add(3);
      floorLength.add(1);
      floorDirections.add(false);
      floorDirections.add(true);
      floorDirections.add(false);
    }
    else if (seedNumber == 3){
      floorLength.add(3);
      floorLength.add(2);
      floorLength.add(1);
      floorDirections.add(false);
      floorDirections.add(false);
      floorDirections.add(true);
    }
  }

  public ArrayList<Integer> getFloorLength() { return floorLength; }

  public ArrayList<Boolean> getFloorDirections() { return floorDirections; }

  /**
   * Javadoc.
   *
   * @return return
   *
   */
  public int getSeed() { return Seed; }
}
