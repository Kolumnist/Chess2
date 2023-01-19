package de.hhn.it.devtools.javafx.reactiongame;

import de.hhn.it.devtools.apis.reactiongame.GameState;
import de.hhn.it.devtools.components.reactiongame.provider.RgcRun;
import java.util.concurrent.atomic.AtomicLong;
import javafx.application.Platform;
import javafx.scene.control.Label;

public class RgcIngameTimer implements Runnable {

  private final RgcRun run;

  private final Label label;


  public RgcIngameTimer(RgcRun run, Label timerLabel) {
    this.run = run;
    this.label = timerLabel;
    new Thread(this).start();
  }

  @Override
  public void run() {


    long millis = 0;
    AtomicLong secs = new AtomicLong();

    while (run.getGameState() != GameState.FINISHED) {

      try {
        Thread.sleep(0);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

      while (run.getGameState() == GameState.RUNNING) {

        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        millis = millis + 100;

        if (millis % 1000 == 0) {
          millis = 0;
          Platform.runLater(
              () -> label.setText(secs.getAndIncrement() + "")
          );
        }
      }

    }
  }
}
