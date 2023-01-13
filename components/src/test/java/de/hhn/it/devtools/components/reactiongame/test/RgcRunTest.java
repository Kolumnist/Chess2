package de.hhn.it.devtools.components.reactiongame.test;

import static org.junit.jupiter.api.Assertions.*;

import de.hhn.it.devtools.apis.reactiongame.Difficulty;
import de.hhn.it.devtools.components.reactiongame.provider.RgcAimTarget;
import de.hhn.it.devtools.components.reactiongame.provider.RgcObstacle;
import de.hhn.it.devtools.components.reactiongame.provider.RgcPlayer;
import de.hhn.it.devtools.components.reactiongame.provider.RgcRun;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RgcRunTest {

  long delta = 500;

  RgcRun run;

  @BeforeEach
  void setUp() {
    run = new RgcRun(Difficulty.MEDIUM, new RgcPlayer("Player"));
  }

  @Test
  void testIfObstaclesAdded() {
    try {
      Thread.sleep(Difficulty.MEDIUM.obstacleIntervall + delta);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    assertEquals(run.getGameField().getObstacles().size() , 1);

  }


  @Test
  void testPauseMethod() {
    long timePreAC = run.getAimTargetClock().getTime();
    long timePreOC = run.getObstacleClock().getTime();

    run.pauseClocks();

    assertAll(
        () -> assertEquals(timePreAC, run.getAimTargetClock().getTime()),
        () -> assertEquals(timePreOC, run.getObstacleClock().getTime())
    );
  }

  @Test
  void testContinueClock() {
    run.pauseClocks();

    long timeAC = run.getAimTargetClock().getTime();
    long timeOC = run.getObstacleClock().getTime();

    run.continueClocks();

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    assertNotEquals(timeAC, run.getAimTargetClock().getTime());
    assertNotEquals(timeOC, run.getObstacleClock().getTime());
  }

  @Test
  void testEndRun() {
    run.endRun();

    assertFalse(run.getAimTargetClock().getIsRunning());
    assertTrue(run.getAimTargetClock().getIsEnded());

    assertFalse(run.getObstacleClock().getIsRunning());
    assertTrue(run.getObstacleClock().getIsEnded());

  }

  @Test
  void testPlayerDoesHitObstacleWhileInvincible() {
    int life = run.getPlayer().getCurrentLife();

    run.setInvincible(true);
    run.setpObstacle(null);

    run.playerHitObstacle();

    assertEquals(life, run.getPlayer().getCurrentLife());
  }

  @Test
  void testPlayerDoesHitObstacleWhileNotInvincible() {
    int life = run.getPlayer().getCurrentLife();

    run.setInvincible(false);
    run.setpObstacle(new RgcObstacle(1,1,1,1,1));

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

    assertTrue(run.getAimTargetClock().getIsEnded());
    assertTrue(run.getObstacleClock().getIsEnded());
  }

  public RgcAimTarget target;

  @Test
  void testCheckForTargetHit() {
    run.setpKey('q');

    run.setpAimTarget(target = new RgcAimTarget(1,1,1,1,'q'));

    int scorealt = run.getScore();

    run.checkForTargetHit();

    assertNotEquals(run.getScore(), scorealt);
  }

  @Test
  void testAddObstacles() {
    run.addObstacle(0);

  }


}