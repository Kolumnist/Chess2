package de.hhn.it.devtools.javafx.chess2;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Chess2PopUp {

  @FXML
  private Label popUpLabel;

  @FXML
  private Button buttonRematch;

  // Je nachdem welcher Button in der Leiste aufgerufen wird (Give up / Won), soll das Label einen untersch. Text hervorbringen
  // Der button "buttonRematch" ist bei GiveUp -> insVisible
  // Der button "buttonRematch" ist bei Won -> Visible

  public Chess2PopUp() {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/chess2"
        + "/Chess2PopUp.fxml"));

    try {
      loader.load();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @FXML
  public void initialize() {
    buttonRematch.setVisible(false);
  }
}
