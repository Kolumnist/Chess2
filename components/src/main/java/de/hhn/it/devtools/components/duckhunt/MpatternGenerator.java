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
  private final int sidePadding;
  private final ScreenDimension screenDimension;

  /**
   * Constructor of MpatternGenerator.
   *
   * @param sidePadding padding to screenDimension walls where no movement can be generated
   * @param screenDimension dimension of area where movement can be generated
   */
  public MpatternGenerator(int sidePadding, ScreenDimension screenDimension) {
    this.generatedPaths = new HashMap<>();
    this.sidePadding = sidePadding;
    this.screenDimension = screenDimension;
  }

  /**
   * Generates movement paths for given ducks and saves them.
   *
   * @param ducks for which movement path shall be generated
   */
  public void generatePaths(DuckData[] ducks) throws DuckOrientationTranslationException {
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
  private Stack<DuckOrientation> generateWaypoints() throws DuckOrientationTranslationException {
    LinkedList<DuckOrientation> generatedPath = new LinkedList<>();
    ArrayList<Vector2D> randPointsVector = generatePath();
    ArrayList<Vector2D> points = new ArrayList<>();

    // generate all points for all line segments
    for (int i = 1; i < randPointsVector.size(); i++) {
      points.addAll(digitalDifferentialAnalyzer(
              randPointsVector.get(i - 1), randPointsVector.get(i)));
    }

    // clean list of all points and convert to DuckOrientations
    Vector2D prevPoint = null;
    for (Vector2D point : points) {
      // skip points that are equal
      if (point.equals(prevPoint)) {
        continue;
      }
      // skip first point because first point ist start point of duck
      if (prevPoint == null) {
        continue;
      }

      generatedPath.add(translatePointsToMovement(prevPoint, point));

      prevPoint = point;
    }

    // reverse path and write to stack
    Stack<DuckOrientation> waypoints = new Stack<>();
    while (!generatedPath.isEmpty()) {
      waypoints.add(generatedPath.removeLast());
    }
    return waypoints;
  }

  /**
   * This Method uses the DDA Line Drawing Algorithm to determine all points
   * of a line in a Cartesian coordinate system given the start and end point
   * of the line.
   *
   * @param startPoint start point of the line
   * @param endPoint end point of the line
   * @return ArrayList of Vector2D objects representing all points of the line
   */
  private ArrayList<Vector2D> digitalDifferentialAnalyzer(Vector2D startPoint, Vector2D endPoint) {
    ArrayList<Vector2D> resultPoints = new ArrayList<>();

    // calculate difference dx, dy
    int dx = endPoint.getX() - startPoint.getX();
    int dy = endPoint.getY() - startPoint.getY();

    // calculate steps
    int steps = Math.max(Math.abs(dx), Math.abs(dy));

    // calculate increment in x & y for each steps
    float incX = dx / (float) steps;
    float incY = dy / (float) steps;

    // put waypoints for each steps
    float x = startPoint.getX();
    float y = startPoint.getY();
    for (int j = 0; j <= steps; j++) {
      resultPoints.add(new Vector2D(Math.round(x), Math.round(y)));

      x += incX;
      y += incY;
    }

    return resultPoints;
  }

  /**
   * Ths method generates a movement pattern.
   **/
  private ArrayList<Vector2D> generatePath() {
    RangedRandom randomGen = new RangedRandom();
    ArrayList<Vector2D> generatedPattern = new ArrayList<>();
    int height = screenDimension.getHeight();
    int width = screenDimension.getWidth();
    //Write algorithm here

    //start point
    randomGen.randomInt(sidePadding, width - sidePadding);

    return generatedPattern;


  }

  private DuckOrientation translatePointsToMovement(Vector2D prevPoint, Vector2D point)
          throws DuckOrientationTranslationException {
    if (new Vector2D(prevPoint.getX() + DuckOrientation.NORTH.getX(),
            prevPoint.getY() + DuckOrientation.NORTH.getY()).equals(point)) {
      return DuckOrientation.NORTH;
    } else if (new Vector2D(prevPoint.getX() + DuckOrientation.NORTHEAST.getX(),
            prevPoint.getY() + DuckOrientation.NORTHEAST.getY()).equals(point)) {
      return DuckOrientation.NORTHEAST;
    } else if (new Vector2D(prevPoint.getX() + DuckOrientation.EAST.getX(),
            prevPoint.getY() + DuckOrientation.EAST.getY()).equals(point)) {
      return DuckOrientation.EAST;
    } else if (new Vector2D(prevPoint.getX() + DuckOrientation.SOUTHEAST.getX(),
            prevPoint.getY() + DuckOrientation.SOUTHEAST.getY()).equals(point)) {
      return DuckOrientation.SOUTHEAST;
    } else if (new Vector2D(prevPoint.getX() + DuckOrientation.SOUTH.getX(),
            prevPoint.getY() + DuckOrientation.SOUTH.getY()).equals(point)) {
      return DuckOrientation.SOUTH;
    } else if (new Vector2D(prevPoint.getX() + DuckOrientation.SOUTHWEST.getX(),
            prevPoint.getY() + DuckOrientation.SOUTHWEST.getY()).equals(point)) {
      return DuckOrientation.SOUTHWEST;
    } else if (new Vector2D(prevPoint.getX() + DuckOrientation.WEST.getX(),
            prevPoint.getY() + DuckOrientation.WEST.getY()).equals(point)) {
      return DuckOrientation.WEST;
    } else if (new Vector2D(prevPoint.getX() + DuckOrientation.NORTHWEST.getX(),
            prevPoint.getY() + DuckOrientation.NORTHWEST.getY()).equals(point)) {
      return DuckOrientation.NORTHWEST;
    } else {
      throw new DuckOrientationTranslationException("Error in translation of point to movement");
    }
  }
}
