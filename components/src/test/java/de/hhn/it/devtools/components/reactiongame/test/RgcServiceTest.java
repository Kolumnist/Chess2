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
  public void pauseRunTest() {
    service.pauseRun();
    assertEquals(run.getState(), GameState.PAUSED);
    assertFalse(run.getObstacleClock().getIsRunning());
    assertFalse(run.getAimTargetClock().getIsRunning());

    boolean thrown = false;
    try {
      service.pauseRun();
    } catch (IllegalStateException e) {
      thrown = true;
    }
    assertTrue(thrown);

    service.continueRun();
    assertEquals(run.getState(), GameState.RUNNING);
    assertTrue(run.getObstacleClock().getIsRunning());
    assertTrue(run.getAimTargetClock().getIsRunning());

    thrown = false;
    try {
      service.continueRun();
    } catch (IllegalStateException e) {
      thrown = true;
    }
    assertTrue(thrown);
  }

  @Test
  public void testEndRun() {
    service.endRun();
    assertEquals(run.getState(), GameState.FINISHED);
  }

  @Test
  public void testKeyPressed() {
    service.keyPressed('q');
    assertEquals(run.getpKey(), 'q');
  }

  @Test
  public void testSetCurrentPlayername() {
    assertEquals(service.getCurrentPlayer().getName(), "Player");
    service.setCurrentPlayerName("Simone");
    assertEquals(service.getCurrentPlayer().getName(), "Simone");
  }
}
