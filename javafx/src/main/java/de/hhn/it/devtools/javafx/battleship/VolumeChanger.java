package de.hhn.it.devtools.javafx.battleship;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VolumeChanger {

    // Value saving not implemented
    static int currentVolume = 0;

    SimpleDoubleProperty test = new SimpleDoubleProperty();
    Stage stageVolume= new Stage();
    VBox vbox = new VBox();
    Scene sceneVolume = new Scene(vbox);

    public VolumeChanger(){

        Slider volumeSlider = new Slider(0,100,10);

        volumeSlider.setShowTickMarks(true);
        volumeSlider.setShowTickLabels(true);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(volumeSlider);
        stageVolume.setScene(sceneVolume);
        stageVolume.setWidth(300);
        stageVolume.setHeight(100);
        stageVolume.setResizable(false);
        stageVolume.setTitle("Volume");
        stageVolume.show();
    }

}
