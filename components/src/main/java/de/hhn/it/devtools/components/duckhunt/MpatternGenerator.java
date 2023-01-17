package de.hhn.it.devtools.components.duckhunt;

import de.hhn.it.devtools.apis.duckhunt.DuckData;
import de.hhn.it.devtools.apis.duckhunt.DuckOrientation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Allows random movement-pattern generation.
 */
public final class MpatternGenerator {

  private HashMap<Integer, Stack<DuckOrientation>> generatedPaths;
  private HashMap<Integer, Vector2D> duckStartingPos;
  private final int sidePadding;
  private final ScreenDimension screenDimension;
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(MpatternGenerator.class);

  /**
   * Constructor of MpatternGenerator.
   *
   * @param sidePadding padding to screenDimension walls where no movement can be generated
   * @param screenDimension dimension of area where movement can be generated
   */
  public MpatternGenerator(int sidePadding, ScreenDimension screenDimension) {
    logger.info("MpatternGenerator: sidePadding, screenDimension", sidePadding, screenDimension);
    this.generatedPaths = new HashMap<>();
    this.duckStartingPos = new HashMap<>();
    this.sidePadding = sidePadding;
    this.screenDimension = screenDimension;
  }

  /**
   * Generates movement paths for given ducks and saves them.
   *
   * @param ducks for which movement path shall be generated
   */
  public void generatePaths(DuckData[] ducks) throws DuckOrientationTranslationException {
    logger.debug("generatePaths: ducks", ducks);
    for (DuckData duck : ducks) {
      duckStartingPos.put(duck.getId(), new Vector2D());
      generatedPaths.put(duck.getId(), generateWaypoints(duck.getId()));
      System.out.println("Generated path for "
          + duck.getId() + Arrays.toString(generatedPaths.get(duck.getId()).toArray()));
    }
  }

  public void clearPaths() {
    generatedPaths.clear();
    duckStartingPos.clear();
  }

  public DuckOrientation getNextMove(int duckId) throws EmptyStackException {
    return generatedPaths.get(duckId).pop();
  }

  public Vector2D getStartingPos(int duckId) {
    return duckStartingPos.get(duckId);
  }

  /**
   * Ths method generates a movement pattern.
   **/
  private Stack<DuckOrientation> generateWaypoints(int duckId)
      throws DuckOrientationTranslationException {
    LinkedList<DuckOrientation> generatedPath = new LinkedList<>();
    ArrayList<Vector2D> randPointsVector = generatePath();
    ArrayList<Vector2D> points = new ArrayList<>();

    // generate all points for all line segments
    for (int i = 1; i < randPointsVector.size(); i++) {
      points.addAll(digitalDifferentialAnalyzer(
              randPointsVector.get(i - 1), randPointsVector.get(i)));
    }

    // set starting point for duck
    duckStartingPos.get(duckId).setX(points.get(0).getX());
    duckStartingPos.get(duckId).setY(points.get(0).getY());

    // clean list of all points and convert to DuckOrientations
    Vector2D prevPoint = null;
    for (Vector2D point : points) {
      // skip first point because first point ist start point of duck
      if (prevPoint == null) {
        prevPoint = point;
        continue;
      }
      // skip points that are equal
      if (point.equals(prevPoint)) {
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
  ArrayList<Vector2D> digitalDifferentialAnalyzer(Vector2D startPoint, Vector2D endPoint) {
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

  private ArrayList<Vector2D> generatePath() {
    return generatePath(new RangedRandom().nextLong(), 6);
  }

  private ArrayList<Vector2D> generatePath(long seed, int pointAmount) {
    ArrayList<Vector2D> generatedPattern = new ArrayList<>();
    RangedRandom random = new RangedRandom();
    random.setSeed(seed);

    // list defines probability for directions to be taken each step
    DuckOrientation[] orientations = {
        DuckOrientation.NORTH,
        DuckOrientation.NORTH,
        DuckOrientation.NORTH,
        DuckOrientation.NORTHEAST,
        DuckOrientation.NORTHEAST,
        DuckOrientation.NORTHWEST,
        DuckOrientation.NORTHWEST,
        DuckOrientation.EAST,
        DuckOrientation.EAST,
        DuckOrientation.WEST,
        DuckOrientation.WEST,
        DuckOrientation.SOUTHEAST,
        DuckOrientation.SOUTHWEST,
        DuckOrientation.SOUTH
    };

    // add first point
    generatedPattern.add(new Vector2D(
            random.randomInt(sidePadding, screenDimension.getWidth() - sidePadding),
            screenDimension.getHeight() // always on the bottom of screen
    ));

    for (int i = 0; i < pointAmount - 1; i++) {
      // get orientation for vector between previous and new point
      DuckOrientation selectedOrientation
          = orientations[random.randomInt(0, orientations.length - 1)];
      // magnitude of the vector between previous and new point, relative to screen size
      int vectorLength = random.randomInt(
              (int) Math.floor(screenDimension.getHeight() * 0.1),
              (int) Math.floor((screenDimension.getHeight() * 0.15) * 2)
      );
      // get previous point
      Vector2D prevPoint;
      try {
        prevPoint = (Vector2D) generatedPattern.get(generatedPattern.size() - 1).clone();
      } catch (CloneNotSupportedException e) {
        throw new RuntimeException(e);
      }
      // calculate new point with vector
      Vector2D newPoint = new Vector2D(
              prevPoint.getX() + (selectedOrientation.getX() * vectorLength),
              prevPoint.getY() + (selectedOrientation.getY() * vectorLength)
      );

      // if point not in Dimension + padding boundary try new point
      if (newPoint.getX() < sidePadding
          || newPoint.getX() > screenDimension.getWidth() - sidePadding
          || newPoint.getY() < sidePadding
          || newPoint.getY() > screenDimension.getHeight() - sidePadding) {
        i--;
        continue;
      }

      generatedPattern.add(newPoint);
    }

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
