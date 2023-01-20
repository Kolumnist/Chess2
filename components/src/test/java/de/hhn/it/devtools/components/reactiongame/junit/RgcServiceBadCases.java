package de.hhn.it.devtools.components.reactiongame.junit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.reactiongame.Difficulty;
import de.hhn.it.devtools.apis.reactiongame.HighscoreTupel;
import de.hhn.it.devtools.components.reactiongame.provider.RgcService;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RgcServiceBadCases {


  private RgcService service;

  @BeforeEach
  public void setup() throws IllegalParameterException {
    service = new RgcService();
    service.newRun(Difficulty.MEDIUM);
    service.addCallback(new DummyListener());
  }


  @Test
  @DisplayName("Delete new callback from service")
  public void removeIllegalCallbackFromService() {
    assertThrows(IllegalParameterException.class,
        () -> service.removeCallback(new DummyListener()));
  }

  @Test
  @DisplayName("Pause an already paused run")
  public void pausePausedRun() {
    service.pauseRun();

    assertThrows(IllegalStateException.class, () -> service.pauseRun());
  }

  @Test
  @DisplayName("Continue a running run run")
  public void continueRunningRun() {
    assertThrows(IllegalStateException.class, () -> service.continueRun());
  }


  @Test
  @DisplayName("Test aim target interactions")
  public void playerEnteredIllegalAimTarget() {
    assertThrows(IllegalParameterException.class,
        () -> service.playerEnteredAimTarget(-1));
  }


  @Test
  @DisplayName("Player entered illegal obstacle")
  public void playerEnteredIllegalObstacle() {
    assertThrows(IllegalParameterException.class,
        () -> service.playerEnteredObstacle(-1));
  }

  @Test
  @DisplayName("Try to continue a finished run")
  public void testContinuePausedRun() {
    service.endRun();
    assertThrows(IllegalStateException.class,
        () -> service.continueRun());
  }

  @Test
  @DisplayName("Add a new highscore")
  public void testNewHighscore() {
    ArrayList<HighscoreTupel> testHighscores = new ArrayList<>();

    testHighscores.add(new HighscoreTupel("Tobi", 100));
    testHighscores.add(new HighscoreTupel("Nadja", 500));

    service.loadHighscoreTable(testHighscores);

    ArrayList<HighscoreTupel> sortedHighscores = service.saveHighscoreTable();

    assertAll(
        () -> assertNotEquals(sortedHighscores.get(0).name(), "Tobi"),
        () -> assertNotEquals(sortedHighscores.get(0).score(), 100),
        () -> assertNotEquals(sortedHighscores.get(1).name(), "Nadja"),
        () -> assertNotEquals(sortedHighscores.get(1).score(), 500)
    );
  }


}
