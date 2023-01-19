package de.hhn.it.devtools.javafx.controllers.connectfour;

import de.hhn.it.devtools.javafx.controllers.connectfour.helper.Instance;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.io.FileIO;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ProfilesNewController {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(HighscoresController.class);

  private FileIO file = new FileIO();


  @FXML
  Button cancelButton;

  @FXML
  Button saveButton;

  @FXML
  TextField nameText;

  @FXML
  void onCancel() {
    logger.info("Add new Profile canceled...");
    Stage stage = (Stage) cancelButton.getScene().getWindow();
    stage.close();
  }

  @FXML
  void onSave() {
    logger.info("New Profile created...");
    Stage stage = (Stage) saveButton.getScene().getWindow();
    try {
      Instance.getConnectFour().createProfile(nameText.getText());
    } catch (IllegalArgumentException ex) {
      stage.close();
    }
    file.saveProfileData();
    stage.close();
  }

  @FXML
  public void initialize() {
    saveButton.disableProperty().bind(Bindings.isEmpty(nameText.textProperty()));
  }

}
