package de.hhn.it.devtools.javafx.battleship;

import de.hhn.it.devtools.javafx.controllers.template.SingletonAttributeStore;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VolumeChanger {

  Stage stageVolume = new Stage();
  VBox vbox = new VBox();
  Scene sceneVolume = new Scene(vbox);
  Game game;
  Button mute = new Button("Mute");
  boolean isMuted = false;

  SingletonAttributeStore singletonAttributeStore = SingletonAttributeStore.getReference();

  public VolumeChanger() {

    mute.setPrefSize(50, 50);
    Slider volumeSlider = new Slider(0, 1, 0.05);
    volumeSlider.setShowTickMarks(true);
    game = (Game) singletonAttributeStore.getAttribute("Battleship.game");

    volumeSlider.valueProperty().addListener(
        (observable, oldValue, newValue) -> game.soundHandler.changeVolume(
            (float) volumeSlider.getValue()));

    mute.setOnAction(actionEvent -> {
      if (isMuted) {
        game.soundHandler.runMusic();
        isMuted = false;
      } else {
        game.soundHandler.stopMusic();
        isMuted = true;
      }
    });

    vbox.setStyle("-fx-background-color: #5BC0BE");
    vbox.setAlignment(Pos.CENTER);
    vbox.getChildren().add(volumeSlider);
    vbox.getChildren().add(mute);
    stageVolume.setScene(sceneVolume);
    stageVolume.setWidth(300);
    stageVolume.setHeight(300);
    stageVolume.setResizable(false);
    stageVolume.setTitle("Volume");
    stageVolume.show();
  }

}
