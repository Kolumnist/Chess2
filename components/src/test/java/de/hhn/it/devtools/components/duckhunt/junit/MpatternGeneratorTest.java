package de.hhn.it.devtools.components.duckhunt.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.hhn.it.devtools.apis.duckhunt.DuckData;
import de.hhn.it.devtools.apis.duckhunt.DuckState;
import de.hhn.it.devtools.components.duckhunt.MpatternGenerator;
import de.hhn.it.devtools.components.duckhunt.ScreenDimension;
import de.hhn.it.devtools.components.duckhunt.Vector2D;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MpatternGeneratorTest {
  MpatternGenerator patternGenerator;
  DuckData[] ducks;

  @BeforeEach
  void setUp() {
    patternGenerator = new MpatternGenerator(10, new ScreenDimension(100, 100));
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
}