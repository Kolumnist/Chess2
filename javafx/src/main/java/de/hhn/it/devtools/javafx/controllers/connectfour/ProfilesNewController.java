package de.hhn.it.devtools.javafx.controllers.connectfour;

import de.hhn.it.devtools.apis.connectfour.IllegalNameException;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.FileIO;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.Instance;
import java.io.IOException;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
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
      Instance.getInstance().createProfile(nameText.getText());
    } catch (IllegalNameException ex){
      stage.close();
    }
    file.saveProfileData();
    stage.close();
  }

  @FXML
  public void initialize(){
    saveButton.disableProperty().bind(Bindings.isEmpty(nameText.textProperty()));
  }

}
