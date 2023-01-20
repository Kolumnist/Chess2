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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ProfilesEditController {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(HighscoresController.class);

  private final FileIO file = new FileIO();

  @FXML
  BorderPane root;

  @FXML
  Button cancelButton;

  @FXML
  Button saveButton;

  @FXML
  TextField nameText;

  @FXML
  ImageView profileImageView;

  @FXML
  void onCancel() {
    logger.info("Edit Profile canceled...");
    Stage stage = (Stage) cancelButton.getScene().getWindow();
    stage.close();
  }

  @FXML
  void onSave() {
    logger.info("Edit Profile saved...");
    Stage stage = (Stage) saveButton.getScene().getWindow();
    try {
      ProfilesInfoController.pinfo.setName(nameText.getText());
    } catch (IllegalArgumentException e) {
      System.err.println(e.getMessage());
    }
    file.saveProfileData();
    stage.close();
  }

  @FXML
  void onDelete(ActionEvent event) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirm delete");
    alert.setHeaderText("You're about to delete " + ProfilesInfoController.pinfo.getName() + "!");
    alert.setContentText("Do you want to delete this Profile?");

    if (alert.showAndWait().isPresent() && alert.showAndWait().get() == ButtonType.OK) {
      Stage stage = (Stage) root.getScene().getWindow();
      try {
        Instance.getInstance().deleteProfile(ProfilesInfoController.pinfo.getId());
        ProfilesInfoController.pinfo = null;
      } catch (NoSuchElementException e) {
        stage.close();
      }
      file.saveProfileData();
      stage.close();
    }
  }

  @FXML
  public void initialize() {
    saveButton.disableProperty().bind(Bindings.isEmpty(nameText.textProperty()));
    Image profileImg = new Image("/fxml/connectfour/files/images/dummy-profile-pic.png");
    profileImageView.setImage(profileImg);
  }
}
