package de.hhn.it.devtools.javafx.controllers.connectfour;

import de.hhn.it.devtools.javafx.controllers.connectfour.helper.Instance;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.io.FileIo;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ProfilesNewController {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(HighscoresController.class);

  private final FileIo file = new FileIo();

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
    Stage stage = (Stage) saveButton.getScene().getWindow();
    try {
      Instance.getInstance().createProfile(nameText.getText());
    } catch (IllegalArgumentException e) {
      System.err.println(e);
    }
    file.saveProfileData();
    stage.close();
  }

  @FXML
  public void initialize() {
    logger.info("initialize: no params");
    saveButton.disableProperty().bind(Bindings.isEmpty(nameText.textProperty()));
    Image profileImg = new Image("/fxml/connectfour/files/images/dummy-profile-pic.png");
    profileImageView.setImage(profileImg);
  }

}
