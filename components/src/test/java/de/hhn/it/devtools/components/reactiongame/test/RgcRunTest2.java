package de.hhn.it.devtools.components.reactiongame.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import de.hhn.it.devtools.apis.reactiongame.Difficulty;
import de.hhn.it.devtools.components.reactiongame.provider.RgcPlayer;
import de.hhn.it.devtools.components.reactiongame.provider.RgcRun;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RgcRunTest2 {
  RgcRun run;

  @BeforeEach
  void setUp() {
    run = new RgcRun(Difficulty.MEDIUM, new RgcPlayer("Player"));
  }
  @Test
  void testAddAndRemoveObstacle() {

    run.pauseClocks();
    run.removeObstacle(0);

    run.addObstacle(0);

    assertNotEquals(0, run.getField().getObstacles().size());

    run.removeObstacle(0);

    assertEquals(0, run.getField().getObstacles().size());
  }

  @Test
  void testAddAndRemoveAimTarget() {

    run.pauseClocks();
    run.removeAimTarget(0);

    run.addAimTarget(0);

    assertNotEquals(0, run.getField().getTargets().size());

    run.removeAimTarget(0);

    assertEquals(0, run.getField().getTargets().size());
  }

}
