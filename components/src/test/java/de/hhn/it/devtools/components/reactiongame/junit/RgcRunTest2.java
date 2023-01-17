package de.hhn.it.devtools.components.reactiongame.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import de.hhn.it.devtools.apis.reactiongame.Difficulty;
import de.hhn.it.devtools.apis.reactiongame.GameState;
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
    run.setGameState(GameState.PAUSED);
    run.addObstacle(0);

    assertEquals(0, run.getField().getObstacles().size());

    run.setGameState(GameState.RUNNING);
    run.addObstacle(0);
    run.setGameState(GameState.PAUSED);

    assertEquals(1, run.getField().getObstacles().size());
  }

  @Test
  void testAddAndRemoveAimTarget() {

    run.addAimTarget(0);
    run.setGameState(GameState.PAUSED);
    assertNotEquals(0, run.getField().getTargetCount());
    run.setGameState(GameState.RUNNING);
    run.removeAimTarget(0);
    run.setGameState(GameState.PAUSED);
    assertEquals(0, run.getField().getTargetCount());

  }

}
