package de.hhn.it.devtools.components.reactiongame.junit;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.reactiongame.Difficulty;
import de.hhn.it.devtools.components.reactiongame.provider.RgcService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RgcListenerTest {


  private DummyListener listener;

  private RgcService service;

  @BeforeEach
  public void setup() throws IllegalParameterException {
    listener = new DummyListener();
    service = new RgcService();

    service.newRun(Difficulty.MEDIUM);
    service.addCallback(listener);
  }


  @Test
  @DisplayName("Test for callback if player collects target")
  public void testTargetHitCallback() throws IllegalParameterException {
    service.getRun().addAimTarget(0);
    service.playerEnteredAimTarget(0);
    service.keyPressed('q');
    service.keyPressed('w');
    service.keyPressed('e');
    service.keyPressed('r');

    assertEquals(1, listener.getMethodCallCountMap().get("removeAimTarget"));
    assertEquals(1, listener.getMethodCallCountMap().get("changeScore"));
  }

  @Test
  @DisplayName("Callback for add obstacle")
  public void testAddObstacle() {
    service.getRun().addObstacle(0);

    assertEquals(1, listener.getMethodCallCountMap().get("addObstacle"));
  }

  @Test
  @DisplayName("Callback for add aimtarget")
  public void testAddAimTarget() {
    service.getRun().addAimTarget(0);

    assertEquals(1, listener.getMethodCallCountMap().get("addAimTarget"));
  }

  @Test
  @DisplayName("Callback for remove obstacle")
  public void testRemoveObstacle() {
    service.getRun().addObstacle(0);
    service.getRun().removeObstacle(0);

    assertEquals(1, listener.getMethodCallCountMap().get("removeObstacle"));
  }

  @Test
  @DisplayName("Callback for remove aimtarget")
  public void testRemoveAimTarget() {
    service.getRun().addAimTarget(0);
    service.getRun().removeAimTarget(0);

    assertEquals(1, listener.getMethodCallCountMap().get("removeAimTarget"));
  }

  @Test
  @DisplayName("Callback for player game over")
  public void testGameOver() throws IllegalParameterException {
    service.getRun().getPlayer().setCurrentLife(1);

    service.getRun().addObstacle(0);
    service.playerEnteredObstacle(0);


    assertEquals(1, listener.getMethodCallCountMap().get("gameOver"));
  }


  @Test
  @DisplayName("Callback hitObstacle")
  public void testHitObstacle() throws IllegalParameterException {
    service.getRun().addObstacle(0);
    service.playerEnteredObstacle(0);

    assertEquals(1, listener.getMethodCallCountMap().get("hitObstacle"));
  }
}
