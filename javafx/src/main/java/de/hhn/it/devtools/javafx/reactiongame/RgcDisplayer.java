package de.hhn.it.devtools.javafx.reactiongame;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class RgcDisplayer implements Runnable{

  private Label label;

  private String text;

  private int milliSeconds;

  public RgcDisplayer(Label label) {
    this.label = label;
  }

  public void displayText(String text, int milliSeconds) {
    this.text = text;
    this.milliSeconds = milliSeconds;

    new Thread(this).start();
  }

  @Override
  public void run() {

    Platform.runLater(() -> label.setText(text));

    try {
      Thread.sleep(milliSeconds);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    Platform.runLater(() -> label.setText(""));
  }

}