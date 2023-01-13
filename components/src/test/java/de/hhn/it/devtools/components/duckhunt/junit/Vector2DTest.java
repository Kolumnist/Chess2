package de.hhn.it.devtools.components.duckhunt.junit;

import de.hhn.it.devtools.components.duckhunt.Vector2D;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2DTest {

  @Test
  @DisplayName("Vector2D getter tests")
  void getterTest() {

    Vector2D testObject = new Vector2D(1, 2);
    int xGiven = testObject.getX();
    int yGiven = testObject.getY();
    assertAll("getter tests",
            () -> assertEquals(1, xGiven),
            () -> assertEquals(2, yGiven));
  }

  @Test
  @DisplayName("Vector2D setter tests")
  void setterTest() {
    Vector2D testObject = new Vector2D(0, 0);
    testObject.setX(4);
    testObject.setY(5);

    assertAll("setter tests",
            () -> assertEquals(4, testObject.getX()),
            () -> assertEquals(5, testObject.getY()));
  }

  @Test
  @DisplayName("Vector2D equals test")
  void equalsTest() {
    Vector2D testObject1 = new Vector2D(2, 1);
    Vector2D testObject2 = new Vector2D(1, 0);
    Vector2D testObject3 = new Vector2D(2, 1);
    Integer testObjectInt = Integer.valueOf(2);

    assertTrue(testObject1.equals(testObject3) && !testObject1.equals(testObject2));
    assertTrue(testObject1.equals(testObject1));
    assertFalse(testObject3.equals(testObjectInt));
  }

  @Test
  @DisplayName("Vector2D toString test")
  void toStringTest() {
    // format: "Vector2D{" + "x=" + x + ", y=" + y + '}'
    Vector2D testObject = new Vector2D(5, 2);
    String methodOutput = testObject.toString();

    assertEquals("Vector2D{x=5, y=2}", methodOutput);
  }

  @Test
  @DisplayName("Vector2D hashCode test")
  void hashCodeTest() {
    Vector2D testVector = new Vector2D(3,6);
    int methodOutput = testVector.hashCode();

    assertEquals(1060, methodOutput);
  }

  @Test
  @DisplayName("Vector2D clone Test")
  void cloneTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    Vector2D testVector = new Vector2D(3,6);
    Class<Vector2D> testingClass = Vector2D.class;
    Method testingMethod = testingClass.getDeclaredMethod("clone");
    testingMethod.setAccessible(true);
    Vector2D testVectorClone = (Vector2D) testingMethod.invoke(testVector);

    assertEquals(testVector, testVectorClone);
  }
}
