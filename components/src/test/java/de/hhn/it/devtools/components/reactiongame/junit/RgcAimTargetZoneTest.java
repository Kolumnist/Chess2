package de.hhn.it.devtools.components.reactiongame.junit;

import static org.junit.jupiter.api.Assertions.*;

import de.hhn.it.devtools.components.reactiongame.provider.RgcAimTargetZone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RgcAimTargetZoneTest {

  RgcAimTargetZone zone;
  @BeforeEach
  void setUp() {
    zone = new RgcAimTargetZone(10,10,15,15);
  }

  @Test
  void testAddRandomAimTarget() {
    zone.addRandomAimTarget(0);
    assertEquals(1, zone.getAimTargets().toArray().length);
  }

  @Test
  void testGenerateRandomKeyForAimTarget() {
    char cha = zone.generateRandomKeyforAimTarget();
    assertTrue((cha == 'q') || (cha == 'w') || (cha == 'e') || (cha == 'r'));
  }
}