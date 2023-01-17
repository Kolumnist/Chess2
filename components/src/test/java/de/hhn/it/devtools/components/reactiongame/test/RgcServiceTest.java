package de.hhn.it.devtools.components.reactiongame.test;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.reactiongame.Difficulty;
import de.hhn.it.devtools.apis.reactiongame.GameState;
import de.hhn.it.devtools.apis.reactiongame.ReactiongameListener;
import de.hhn.it.devtools.components.reactiongame.provider.RgcAimTarget;
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
  public void testKeyPressed() {
    service.keyPressed('q');
    assertEquals(run.getpKey(), 'q');
  }

  @Test
  public void testSetCurrentPlayerName() {
    assertEquals(service.getCurrentPlayer().getName(), "Player");
    service.setCurrentPlayerName("Simone");
    assertEquals(service.getCurrentPlayer().getName(), "Simone");
  }

  @Test
  public void testPlayerEnteredAimTarget() {
    boolean thrown = false;
    try {
      service.playerEnteredAimTarget(-1);
    } catch (IllegalParameterException e) {
      thrown = true;
    }
    assertTrue(thrown);

    thrown = false;
    run.addAimTarget(0);
    try {
      service.playerEnteredAimTarget(0);
    } catch (IllegalParameterException e) {
      thrown = true;
    }
    assertEquals(run.getpAimTarget().getId(), 0);
    assertFalse(thrown);
  }

  @Test
  public void testPlayerEnteredObstacle() {
    boolean thrown = false;
    try {
      service.playerEnteredObstacle(-1);
    } catch (IllegalParameterException e) {
      thrown = true;
    }
    assertTrue(thrown);

    int life = run.getPlayer().getCurrentLife();
    thrown = false;
    run.addObstacle(0);
    try {
      service.playerEnteredObstacle(0);
    } catch (IllegalParameterException e) {
      thrown = true;
    }
    assertEquals(run.getpObstacle().getId(), 0);
    assertFalse(thrown);
    assertEquals(run.getPlayer().getCurrentLife(), life - 1);
  }

  @Test
  public void testPlayerLeftGameObject() {
    service.playerLeftGameObject();
    assertNull(run.getpObstacle());
    assertNull(run.getpAimTarget());
  }
}
