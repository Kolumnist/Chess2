package de.hhn.it.devtools.components.reactiongame.test;

import static org.junit.Assert.*;

import de.hhn.it.devtools.components.reactiongame.provider.RgcAimTarget;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RgcAimTargetTest {

  private RgcAimTarget target;
  @BeforeEach
  void setup(){
    target = new RgcAimTarget(1, 12, 15, 25, 'q');
  }
  @Test
  void testGetX() {
    assertTrue(target.getX() == 12);
  }

  @Test
  void testGetY() {
    assertTrue(target.getY() == 15);
  }

  @Test
  void testGetR() {
    assertTrue(target.getR() == 25);
  }

  @Test
  void testGetKey() {
    assertEquals('q', target.getKey());
  }

  @Test
  void testGetId() {
    assertTrue(target.getId() == 1);
  }

  @Test
  void testToString() {
    assertEquals("AimTarget{"
        + "x=" + 12
        + ", y=" + 15
        + ", r=" + 25
        + ", key=" + 'q'
        + ", id=" + 1
        + '}' , target.toString());
  }
}