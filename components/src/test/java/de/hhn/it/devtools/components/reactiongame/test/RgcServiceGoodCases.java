package de.hhn.it.devtools.components.reactiongame.test;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.reactiongame.Difficulty;
import de.hhn.it.devtools.apis.reactiongame.GameState;

import de.hhn.it.devtools.components.reactiongame.provider.RgcService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the methods of RgcService
 */
public class RgcServiceGoodCases {

  private RgcService service;

  @BeforeEach
  public void setup() throws IllegalParameterException {
    service = new RgcService();
    service.newRun(Difficulty.MEDIUM);
  }

  @Test
  public void pauseRunTest() {
    service.pauseRun();
    assertEquals(service.getRun().getState(), GameState.PAUSED);
    assertFalse(service.getRun().getObstacleClock().getIsRunning());
    assertFalse(service.getRun().getAimTargetClock().getIsRunning());

    boolean thrown = false;
    try {
      service.pauseRun();
    } catch (IllegalStateException e) {
      thrown = true;
    }
    assertTrue(thrown);

    service.continueRun();
    assertEquals(service.getRun().getState(), GameState.RUNNING);
    assertTrue(service.getRun().getObstacleClock().getIsRunning());
    assertTrue(service.getRun().getAimTargetClock().getIsRunning());

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
    assertEquals(service.getRun().getpKey(), 'q');
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
    service.getRun().addAimTarget(0);
    try {
      service.playerEnteredAimTarget(0);
    } catch (IllegalParameterException e) {
      thrown = true;
    }
    assertEquals(service.getRun().getpAimTarget().getId(), 0);
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

    int life = service.getRun().getPlayer().getCurrentLife();
    thrown = false;
    service.getRun().addObstacle(0);
    try {
      service.playerEnteredObstacle(0);
    } catch (IllegalParameterException e) {
      thrown = true;
    }
    assertEquals(service.getRun().getpObstacle().getId(), 0);
    assertFalse(thrown);
    assertEquals(service.getRun().getPlayer().getCurrentLife(), life - 1);
  }

  @Test
  public void testPlayerLeftGameObject() {

    service.playerLeftGameObject();
    assertNull(service.getRun().getpObstacle());
    assertNull(service.getRun().getpAimTarget());
  }


}
