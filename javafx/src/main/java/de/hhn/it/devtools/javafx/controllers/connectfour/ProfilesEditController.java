package de.hhn.it.devtools.javafx.controllers.connectfour;

import de.hhn.it.devtools.apis.connectfour.helper.Profile;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.Instance;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.io.FileIo;
import java.io.File;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.UUID;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * This class modells the edit profile controller.
 */
public class ProfilesEditController {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(HighscoresController.class);

  private final FileIo file = new FileIo();

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
    logger.info("onCancel: no params");
    Stage stage = (Stage) cancelButton.getScene().getWindow();
    stage.close();
  }

  @FXML
  void onSave() {
    logger.info("onSave: no params");
    boolean goodName = true;
    HashMap<UUID, Profile> profiles = Instance.getInstance().getProfiles();
    Stage stage = (Stage) saveButton.getScene().getWindow();
    for (Profile value : profiles.values()) {
      if (value.getName().equalsIgnoreCase(nameText.getText())) {
        goodName = false;
      }
    }
    if (!goodName) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Name already taken");
      alert.setHeaderText("The profile " + nameText.getText() + " already exists!");
      alert.setContentText("Please use another name.");
      alert.showAndWait();
      nameText.clear();
    } else {
      try {
        ProfilesInfoController.pinfo.setName(nameText.getText());
      } catch (IllegalArgumentException e) {
        System.err.println(e.getMessage());
      }
      file.saveProfileData();
      stage.close();
    }
  }

  @FXML
  void onDelete() {
    logger.info("onDelete: no params");
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.OK, ButtonType.CANCEL);
    alert.setTitle("Confirm delete");
    alert.setHeaderText("You're about to delete " + ProfilesInfoController.pinfo.getName() + "!");
    alert.setContentText("Do you want to delete this Profile?");
    alert.showAndWait();

    if (alert.getResult() == ButtonType.OK) {
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

  /**
   * Initialize the controller.
   */
  @FXML
  public void initialize() {
    logger.info("initialize: no params");
    saveButton.disableProperty().bind(Bindings.isEmpty(nameText.textProperty()));
    String path = "javafx/src/main/resources/fxml/connectfour/files/images/dummy-profile-pic.png";
    File file;
    Image profileImg = null;
    try {
      file = new File(path);
      profileImg = new Image(file.getAbsolutePath());
    } catch (Exception e) {
      logger.info("initialize: failed once!");
      try {
        path = "src/main/resources/fxml/connectfour/files/images/dummy-profile-pic.png";
        file = new File(path);
        profileImg = new Image(file.getAbsolutePath());
      } catch (Exception ex) {
        logger.info("initialize: failed twice!");
        System.err.println(ex.getMessage());
      }
    }
    profileImageView.setImage(profileImg);
  }
}
