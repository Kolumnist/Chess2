package de.hhn.it.devtools.components.reactiongame.junit;


import static org.junit.jupiter.api.Assertions.*;

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
    assertEquals(12, target.getX());
  }

  @Test
  void testGetY() {
    assertEquals(15, target.getY());
  }

  @Test
  void testGetR() {
    assertEquals(25, target.getR());
  }

  @Test
  void testGetKey() {
    assertEquals('q', target.getKey());
  }

  @Test
  void testGetId() {
    assertEquals(1, target.getId());
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

  @Test
  void testToAimTargetDescriptor(){
    RgcAimTarget target = new RgcAimTarget(1, 10, 10, 20, 'w');
    assertNotNull(RgcAimTarget.toAimTargetDescriptor(target));
  }
}