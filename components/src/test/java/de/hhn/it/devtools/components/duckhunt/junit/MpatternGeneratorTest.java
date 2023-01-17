package de.hhn.it.devtools.components.duckhunt.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import de.hhn.it.devtools.apis.duckhunt.DuckData;
import de.hhn.it.devtools.apis.duckhunt.DuckOrientation;
import de.hhn.it.devtools.apis.duckhunt.DuckState;
import de.hhn.it.devtools.components.duckhunt.DuckOrientationTranslationException;
import de.hhn.it.devtools.components.duckhunt.MpatternGenerator;
import de.hhn.it.devtools.components.duckhunt.ScreenDimension;
import de.hhn.it.devtools.components.duckhunt.Vector2D;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MpatternGeneratorTest {
  MpatternGenerator patternGenerator;
  DuckData[] ducks;
  ScreenDimension screenDimension = new ScreenDimension(100, 100);
  int sidePadding = 10;

  @BeforeEach
  void setUp() {
    patternGenerator = new MpatternGenerator(10, screenDimension);
    ducks = new DuckData[] {
      new DuckData(0, 0, 0, DuckState.FLYING),
      new DuckData(1, 0, 0, DuckState.FLYING)
    };
  }

  @AfterEach
  void tearDown() {
    patternGenerator.clearPaths();
  }

  @Test
  void getNextMoveTest() throws DuckOrientationTranslationException {
    patternGenerator.generatePaths(ducks);
    DuckOrientation orientation = patternGenerator.getNextMove(0);
    assertTrue(orientation == DuckOrientation.WEST
              || orientation == DuckOrientation.NORTHWEST
              || orientation == DuckOrientation.NORTH
              || orientation == DuckOrientation.NORTHEAST
              || orientation == DuckOrientation.EAST);
  }

  @Test
  @DisplayName("DDA Algorithm Test")
  void digitalDifferentialAnalyzerTest() throws NoSuchMethodException,
          InvocationTargetException, IllegalAccessException {
    Method indexOfMethod = MpatternGenerator.class
            .getDeclaredMethod("digitalDifferentialAnalyzer", Vector2D.class, Vector2D.class);
    indexOfMethod.setAccessible(true);

    ArrayList<Vector2D> shouldBeCase1 = new ArrayList<>();
    shouldBeCase1.add(new Vector2D(0, 0));
    shouldBeCase1.add(new Vector2D(1, 1));
    shouldBeCase1.add(new Vector2D(2, 2));

    final ArrayList<Vector2D> result1 = (ArrayList<Vector2D>) indexOfMethod.invoke(
        patternGenerator,
        shouldBeCase1.get(0),
        shouldBeCase1.get(shouldBeCase1.size() - 1)
    );

    System.out.println("Case 1:");
    System.out.print("Should be: ");
    shouldBeCase1.forEach(System.out::print);
    System.out.println();
    System.out.print("Is like: ");
    result1.forEach(System.out::print);
    System.out.println();
    assertEquals(result1, shouldBeCase1);


    ArrayList<Vector2D> shouldBeCase2 = new ArrayList<>();
    shouldBeCase2.add(new Vector2D(5, 4));
    shouldBeCase2.add(new Vector2D(6, 4));
    shouldBeCase2.add(new Vector2D(7, 5));
    shouldBeCase2.add(new Vector2D(8, 5));
    shouldBeCase2.add(new Vector2D(9, 6));
    shouldBeCase2.add(new Vector2D(10, 6));
    shouldBeCase2.add(new Vector2D(11, 7));
    shouldBeCase2.add(new Vector2D(12, 7));

    final ArrayList<Vector2D> result2 = (ArrayList<Vector2D>) indexOfMethod.invoke(
        patternGenerator,
        shouldBeCase2.get(0),
        shouldBeCase2.get(shouldBeCase2.size() - 1)
    );

    System.out.println("Case 2:");
    System.out.print("Should be: ");
    shouldBeCase2.forEach(System.out::print);
    System.out.println();
    System.out.print("Is like: ");
    result2.forEach(System.out::print);
    System.out.println();
    assertEquals(result2, shouldBeCase2);
  }

  @Test
  @DisplayName("Orientation Waypoint Algorithm Test - Specified Seed")
  void generatePathTest1() throws NoSuchMethodException,
          InvocationTargetException, IllegalAccessException {
    Method indexOfMethod = MpatternGenerator.class
            .getDeclaredMethod("generatePath", long.class, int.class);
    indexOfMethod.setAccessible(true);

    long testSeed = 3697562753721813L;

    final ArrayList<Vector2D> result = (ArrayList<Vector2D>) indexOfMethod.invoke(
        patternGenerator, testSeed, 6);

    final ArrayList<Vector2D> shouldBeCase = new ArrayList<>();
    shouldBeCase.add(new Vector2D(35, 100));
    shouldBeCase.add(new Vector2D(57, 78));
    shouldBeCase.add(new Vector2D(57, 51));
    shouldBeCase.add(new Vector2D(34, 51));
    shouldBeCase.add(new Vector2D(56, 51));
    shouldBeCase.add(new Vector2D(82, 25));

    assertEquals(result, shouldBeCase);
  }

  @Test
  @DisplayName("Orientation Waypoint Algorithm Test - Random Seeds")
  void generatePathTest2() throws NoSuchMethodException,
          InvocationTargetException, IllegalAccessException {
    Method indexOfMethod = MpatternGenerator.class
            .getDeclaredMethod("generatePath", long.class, int.class);
    indexOfMethod.setAccessible(true);

    boolean errorFlag = false;

    for (int i = 0; i < 100; i++) {
      long testSeed = new Random().nextLong();

      final ArrayList<Vector2D> result = (ArrayList<Vector2D>) indexOfMethod.invoke(
          patternGenerator, testSeed, 5);
      result.remove(0); // first point never in boundaries because duck spawn on bottom of screen

      for (Vector2D v : result) {
        // if point not in Dimension boundary
        if (v.getX() < sidePadding || v.getX() > screenDimension.getWidth() - sidePadding
            || v.getY() < sidePadding || v.getY() > screenDimension.getHeight() - sidePadding) {
          errorFlag = true;
          break;
        }
      }

      if (errorFlag) {
        System.out.print("Point List where error occurred: ");
        result.forEach(System.out::print);
        fail("Generated points not in boundaries");
      }
    }
  }
}