package de.hhn.it.devtools.javafx.chess2;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Chess2PopUp {

  //ich brauch noch ne eigene stage und scene

  // Je nachdem welcher Button in der Leiste aufgerufen wird (Give up / Won), soll das Label
  // einen untersch. Text hervorbringen
  // Der button "buttonRematch" ist bei GiveUp -> insVisible
  // Der button "buttonRematch" ist bei Won -> Visible

  @FXML
  public void initialize(Label popUpLabel) {
    Stage dialog = new Stage();
    dialog.initModality(Modality.APPLICATION_MODAL);
    VBox dialogVbox = new VBox(50);
    dialogVbox.getChildren().add(popUpLabel);
    Scene dialogScene = new Scene(dialogVbox, 300, 200);
    dialog.setScene(dialogScene);
    dialog.show();
  }
}
