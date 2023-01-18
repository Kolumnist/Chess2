package de.hhn.it.devtools.components.reactiongame.junit;

import static org.junit.jupiter.api.Assertions.*;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.reactiongame.Difficulty;
import de.hhn.it.devtools.apis.reactiongame.GameState;
import de.hhn.it.devtools.components.reactiongame.provider.RgcAimTarget;
import de.hhn.it.devtools.components.reactiongame.provider.RgcObstacle;
import de.hhn.it.devtools.components.reactiongame.provider.RgcPlayer;
import de.hhn.it.devtools.components.reactiongame.provider.RgcRun;
import de.hhn.it.devtools.components.reactiongame.provider.RgcService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RgcRunTest {

  private final long delta = 1000;

  private RgcRun run;

  @BeforeEach
  void setUp() {
    run = new RgcRun(Difficulty.MEDIUM, new RgcPlayer("Player"));
  }

  @Test
  void testIfObstaclesAdded() throws IllegalParameterException {
    RgcService service = new RgcService();
    service.newRun(Difficulty.MEDIUM);

    try {
      Thread.sleep(Difficulty.MEDIUM.obstacleIntervall * 1000L);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    assertEquals(1 , service.getRun().getField().getObstacles().size());

  }


  @Test
  void testPauseMethod() {
    long timePreAC = run.getAimTargetClock().getTime();
    long timePreOC = run.getObstacleClock().getTime();

    run.setGameState(GameState.PAUSED);

    assertAll(
        () -> assertEquals(timePreAC, run.getAimTargetClock().getTime()),
        () -> assertEquals(timePreOC, run.getObstacleClock().getTime())
    );
  }

  @Test
  void testContinueClock() {
    run.setGameState(GameState.PAUSED);

    long timeAC = run.getAimTargetClock().getTime();
    long timeOC = run.getObstacleClock().getTime();

    run.setGameState(GameState.RUNNING);

    try {
      Thread.sleep(delta + 1000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    assertAll(
        () -> assertNotEquals(timeAC, run.getAimTargetClock().getTime()),
        () -> assertNotEquals(timeOC, run.getObstacleClock().getTime())
    );

  }

  @Test
  void testPlayerDoesHitObstacleWhileInvincible() {
    int life = run.getPlayer().getCurrentLife();

    run.setInvincible(true);
    run.setObstacle(null);

    run.playerHitObstacle();

    assertEquals(life, run.getPlayer().getCurrentLife());
  }

  @Test
  void testPlayerDoesHitObstacleWhileNotInvincible() {
    int life = run.getPlayer().getCurrentLife();

    run.setInvincible(false);
    run.setObstacle(new RgcObstacle(1,1,1,1,1));

    run.playerHitObstacle();

    assertNotEquals(life, run.getPlayer().getCurrentLife());
  }

  @Test
  void testPlayerLosesLife() {
    int life = run.getPlayer().getCurrentLife();

    run.playerLosesLife();

    assertNotEquals(life, run.getPlayer().getCurrentLife());
  }

  @Test
  void testPlayerLosesLifeWhileHavingOneLife() {
    run.getPlayer().setCurrentLife(1);

    run.playerLosesLife();

    assertSame(run.getGameState(), GameState.FINISHED);
  }

  public RgcAimTarget target;

  @Test
  void testCheckForTargetHit() {
    run.setKey('q');

    run.setAimTarget(target = new RgcAimTarget(1,1,1,1,'q'));

    int scorealt = run.getScore();

    run.checkForTargetHit();

    assertNotEquals(run.getScore(), scorealt);
  }

  @Test
  void testGetCallbacks() {
    assertNotNull(run.getCallbacks());
  }
}