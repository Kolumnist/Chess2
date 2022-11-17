package de.hhn.it.devtools.components.duckhunt;

import java.util.ArrayList;

/**
 * Allows random movement-pattern generation.
 */
public class MpatternGenerator {

  //private Random randomGen;

  /**
   * Ths method generates a movement pattern.
   *
   * @param screen screensize
   **/
  public ArrayList<Vector2d> generatePath(ScreenDimension screen, int sidePadding) {
    RangedRandom randomGen = new RangedRandom();
    ArrayList<Vector2d> generatedPattern = new ArrayList<>();
    int height = screen.getHeight();
    int width = screen.getWidth();
    //Write algorithm here

    //start point
    randomGen.randomInt(sidePadding, width - sidePadding);

    return generatedPattern;


  }
}
