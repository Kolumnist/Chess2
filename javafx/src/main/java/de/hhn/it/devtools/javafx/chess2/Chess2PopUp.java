package de.hhn.it.devtools.javafx.chess2;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This class handles all communication between buttons and components.
 *
 * @author Collin Hoss, Lara Mangi, Michel Jouaux
 * @version 1.0
 */
public class Chess2PopUp {

  /**
   * This method create a popUp Window.
   *
   * @param popUpLabel is a label in the middle of the window
   */
  @FXML
  public void initialize(Label popUpLabel) {
    Stage dialog = new Stage();
    VBox dialogVbox = new VBox(50);
    dialogVbox.getChildren().add(popUpLabel);
    Scene dialogScene = new Scene(dialogVbox, 300, 200);
    dialog.setScene(dialogScene);
    dialog.show();
  }
}
