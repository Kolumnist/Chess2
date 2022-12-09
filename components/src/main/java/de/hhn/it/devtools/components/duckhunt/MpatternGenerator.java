package de.hhn.it.devtools.components.duckhunt;

import de.hhn.it.devtools.apis.duckhunt.DuckData;
import de.hhn.it.devtools.apis.duckhunt.DuckOrientation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.LinkedList;
import java.time.ZonedDateTime;

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
      // skip first point because first point ist start point of duck
      if (prevPoint == null) {
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

  /**
   * Ths method generates a movement pattern.
   **/
  private ArrayList<Vector2D> generatePath() {
    RangedRandom randomGen = new RangedRandom(ZonedDateTime.now().toInstant().toEpochMilli());
    ArrayList<Vector2D> generatedPattern = new ArrayList<>();
    int height = screenDimension.getHeight() - sidePadding;
    int width = screenDimension.getWidth() - sidePadding;
    final int maxNodes = 6;
    final int nodeOffset = 100; //ToDO: make in relation to screen size and randomise
    final int nodeOffsetDiag = (int) Math.sqrt(100 / 2.0);
    Vector2D lastNode;
    Vector2D node;

    //Algorithm start here
    int i = 0;
    lastNode = new Vector2D(randomGen.randomInt(sidePadding, width), 0);
    generatedPattern.add(lastNode); //starting point
    while (i < maxNodes) {

      node = new Vector2D(); //new Node

      if (randomGen.randomInt(1, 2) == 1) {

        switch (randomGen.randomInt(1, 5)) {
          case 1 -> {
            node.setX(lastNode.getX());
            node.setY(lastNode.getY() + nodeOffset);
          }
          case 2 -> {
            node.setX(lastNode.getX() + nodeOffset);
            node.setY(lastNode.getY());
          }
          case 3 -> {
            node.setX(lastNode.getX() - nodeOffset);
            node.setY(lastNode.getY());
          }
          case 4 -> {
            node.setX(lastNode.getX() + nodeOffsetDiag);
            node.setY(lastNode.getY() + nodeOffsetDiag);
          }
          case 5 -> {
            node.setX(lastNode.getX() - nodeOffsetDiag);
            node.setY(lastNode.getY() + nodeOffsetDiag);
          }
          default -> {
          }
        }

      } else {
        switch (randomGen.randomInt(1, 3)) {
          case 1 -> {
            node.setX(lastNode.getX());
            node.setY(lastNode.getY() - nodeOffset);
          }
          case 2 -> {
            node.setX(lastNode.getX() + nodeOffsetDiag);
            node.setY(lastNode.getY() - nodeOffsetDiag);
          }
          case 3 -> {
            node.setX(lastNode.getX() - nodeOffsetDiag);
            node.setY(lastNode.getY() - nodeOffsetDiag);
          }
          default -> {
          }
        }
      }

      //check and resolve boundary violations
      if (node.getX() > width || node.getX() < sidePadding) {
        if (Math.abs(width - node.getX()) > Math.abs(sidePadding - node.getX())) {
          node.setX(sidePadding + 1);
        } else {
          node.setX(width - 1);
        }
      }
      if (node.getY() > height || node.getY() < sidePadding) {
        if (Math.abs(height - node.getY()) > Math.abs(sidePadding - node.getY())) {
          node.setY(sidePadding + 1);
        } else {
          node.setY(height - 1);
        }
      }

      //add to List and update lastNode
      generatedPattern.add(node);
      lastNode = node;
      i++;
    }
    return generatedPattern;
  }

  /*private ArrayList<Vector2D> generatePath() {
    return generatePath(new Random().nextLong(), 6);
  }*/

  /**
   * Generates random(seeded) points in area defined by screen dimensions and side padding.
   * First Point will always be at the bottom of the screen. Points follow a set probability.
   *
   * @param seed seed for the random functionality
   * @param pointAmount amount of points that shall be generated
   * @return list of generated points
   */
  private ArrayList<Vector2D> generatePath(long seed, int pointAmount) {
    ArrayList<Vector2D> generatedPattern = new ArrayList<>();
    RangedRandom random = new RangedRandom(seed);
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
            random.randomInt(sidePadding, screenDimension.getWidth()-sidePadding),
            screenDimension.getHeight() // always on the bottom of screen
    ));

    for (int i = 0; i < pointAmount-1; i++) {
      // get orientation for vector between previous and new point
      DuckOrientation selectedOrientation = orientations[random.randomInt(0, orientations.length-1)];
      // magnitude of the vector between previous and new point, relative to screen size
      int vectorLength = random.randomInt(
              (int) Math.floor(screenDimension.getHeight() * 0.1),
              (int) Math.floor((screenDimension.getHeight() * 0.15) * 2)
      );
      // get previous point
      Vector2D prevPoint = (Vector2D) generatedPattern.get(generatedPattern.size()-1).clone();
      // calculate new point with vector
      Vector2D newPoint = new Vector2D(
              prevPoint.getX() + (selectedOrientation.getX() * vectorLength),
              prevPoint.getY() + (selectedOrientation.getY() * vectorLength)
      );

      // if point not in Dimension + padding boundary try new point
      if (newPoint.getX() < sidePadding || newPoint.getX() > screenDimension.getWidth()-sidePadding ||
              newPoint.getY() < sidePadding || newPoint.getY() > screenDimension.getHeight()-sidePadding) {
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
