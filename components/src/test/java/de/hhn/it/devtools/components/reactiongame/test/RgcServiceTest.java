package de.hhn.it.devtools.components.reactiongame.test;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.reactiongame.Difficulty;
import de.hhn.it.devtools.apis.reactiongame.GameState;
import de.hhn.it.devtools.apis.reactiongame.ReactiongameListener;
import de.hhn.it.devtools.components.reactiongame.provider.RgcObstacleClock;
import de.hhn.it.devtools.components.reactiongame.provider.RgcPlayer;
import de.hhn.it.devtools.components.reactiongame.provider.RgcRun;

import de.hhn.it.devtools.components.reactiongame.provider.RgcService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the methods of RgcService
 */
public class RgcServiceTest {

  private RgcRun run;
  private RgcService service;

  @BeforeEach
  public void setup() {
    service = new RgcService();
    try {
      service.newRun(Difficulty.MEDIUM);
      run = service.getRun();
    } catch (IllegalParameterException e) {

    }
  }

  @Test
  public void callbackTest() {

  }

  @Test
  public void pauseRunTest() {
    service.pauseRun();
    assertEquals(run.getState(), GameState.PAUSED);
    assertFalse(run.getObstacleClock().getIsRunning());
    assertFalse(run.getAimTargetClock().getIsRunning());

    service.continueRun();
    assertEquals(run.getState(), GameState.RUNNING);
    assertTrue(run.getObstacleClock().getIsRunning());
    assertTrue(run.getAimTargetClock().getIsRunning());
  }
}
