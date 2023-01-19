package de.hhn.it.devtools.javafx.controllers.connectfour;

import de.hhn.it.devtools.javafx.controllers.connectfour.helper.Instance;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.io.FileIO;
import java.util.NoSuchElementException;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ProfilesEditController {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(HighscoresController.class);

  private FileIO file = new FileIO();

  @FXML
  BorderPane root;

  @FXML
  Button cancelButton;

  @FXML
  Button saveButton;

  @FXML
  TextField nameText;

  @FXML
  void onCancel() {
    logger.info("Edit Profile canceled...");
    Stage stage = (Stage) cancelButton.getScene().getWindow();
    stage.close();
  }

  @FXML
  void onSave() {
    logger.info("Edit Profile saved...");
    file.saveProfileData();
  }

  @FXML
  void onDelete(ActionEvent event) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirm delete");
    alert.setHeaderText("You're about to delete " + ProfilesInfoController.pinfo.getName() + "!");
    alert.setContentText("Do you want to delete this Profile?");

    if (alert.showAndWait().get() == ButtonType.OK) {
      Stage stage = (Stage) root.getScene().getWindow();
      try {
        Instance.getConnectFour().deleteProfile(ProfilesInfoController.pinfo.getId());
      } catch (NoSuchElementException ex) {
        stage.close();
      }
      file.saveProfileData();
      stage.close();
    }
  }

  @FXML
  public void initialize() {
    saveButton.disableProperty().bind(Bindings.isEmpty(nameText.textProperty()));
  }

}
