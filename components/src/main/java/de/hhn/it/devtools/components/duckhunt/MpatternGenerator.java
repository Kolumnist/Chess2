package de.hhn.it.devtools.components.duckhunt;

import de.hhn.it.devtools.apis.duckhunt.DuckData;
import de.hhn.it.devtools.apis.duckhunt.DuckOrientation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Allows random movement-pattern generation.
 */
public final class MpatternGenerator {

  private HashMap<Integer, Stack<DuckOrientation>> generatedPaths;
  private int sidePadding;
  private ScreenDimension screenDimension;

  public MpatternGenerator(int sidePadding, ScreenDimension screenDimension) {
    this.generatedPaths = new HashMap<>();
    this.sidePadding = sidePadding;
    this.screenDimension = screenDimension;
  }

  public void generatePaths(DuckData[] ducks) {
    for (DuckData duck : ducks) {
      generatedPaths.put(duck.getId(), generateWaypoints());
    }
  }

  public void clearPaths() {
    generatedPaths.clear();
  }

  public DuckOrientation getNextMove(int duckId) {
    return generatedPaths.remove(duckId).pop();
  }

  /**
   * Ths method generates a movement pattern.
   **/
  public Stack<DuckOrientation> generateWaypoints() {
    LinkedList<DuckOrientation> generatedPath = new LinkedList<>();

    //DDA Line Drawing Algorithm
    // TODO implement DDA
    // generatedPath()

    // reverse path and write to stack
    Stack<DuckOrientation> waypoints = new Stack<>();
    while (!generatedPath.isEmpty()) {
      waypoints.add(generatedPath.removeLast());
    }
    return waypoints;
  }

  //private Random randomGen;

  /**
   * Ths method generates a movement pattern.
   *
   * @param screen screensize
   **/
  public ArrayList<Vector2D> generatePath(ScreenDimension screen, int sidePadding) {
    RangedRandom randomGen = new RangedRandom();
    ArrayList<Vector2D> generatedPattern = new ArrayList<>();
    int height = screen.getHeight();
    int width = screen.getWidth();
    //Write algorithm here

    //start point
    randomGen.randomInt(sidePadding, width - sidePadding);

    return generatedPattern;


  }
}
