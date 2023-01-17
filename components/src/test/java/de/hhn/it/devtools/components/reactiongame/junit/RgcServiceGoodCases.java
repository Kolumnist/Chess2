package de.hhn.it.devtools.components.reactiongame.junit;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.reactiongame.Difficulty;
import de.hhn.it.devtools.apis.reactiongame.GameState;

import de.hhn.it.devtools.components.reactiongame.provider.RgcService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    service.addCallback(new DummyListener());
  }

  @Test
  @DisplayName("Pause and continue run")
  public void pauseRunTest() {
    service.pauseRun();
    assertEquals(service.getRun().getGameState(), GameState.PAUSED);

    service.continueRun();
    assertEquals(service.getRun().getGameState(), GameState.RUNNING);
  }


  @Test
  @DisplayName("Q - key pressed")
  public void testKeyPressed() {
    service.keyPressed('q');
    assertEquals(service.getRun().getpKey(), 'q');
  }

  @Test
  @DisplayName("Change player name")
  public void testSetCurrentPlayerName() {
    assertEquals(service.getCurrentPlayer().getName(), "Player");
    service.setCurrentPlayerName("Simone");
    assertEquals(service.getCurrentPlayer().getName(), "Simone");
  }



  @Test
  @DisplayName("Player entered obstacle and loses life")
  public void testPlayerEnteredObstacle() throws IllegalParameterException {
    int life = service.getRun().getPlayer().getCurrentLife();
    service.getRun().addObstacle(0);
    service.playerEnteredObstacle(0);

    assertEquals(service.getRun().getpObstacle().getId(), 0);
    assertEquals(service.getRun().getPlayer().getCurrentLife(), life - 1);
  }

  @Test
  @DisplayName("Player entered aimtarget")
  public void testPlayerEnteredAimtarget() throws IllegalParameterException {
    service.getRun().addAimTarget(0);
    service.playerEnteredAimTarget(0);

    assertEquals(service.getRun().getpAimTarget().getId(), 0);
  }

  @Test
  public void testPlayerLeftGameObject() {
    service.playerLeftGameObject();
    assertNull(service.getRun().getpObstacle());
    assertNull(service.getRun().getpAimTarget());
  }





}
