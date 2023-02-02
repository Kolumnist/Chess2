package de.hhn.it.devtools.javafx.controllers.connectfour;

import de.hhn.it.devtools.apis.connectfour.helper.Profile;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This class models the profile info controller.
 */
public class ProfilesInfoController {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(HighscoresController.class);

  public static Profile pinfo;

  @FXML
  Pane root;
  @FXML
  ImageView profileImageView;
  @FXML
  Label nameLabel;
  @FXML
  Button btnEditProfile;
  @FXML
  Label swonLabel;
  @FXML
  Label mwonLabel;
  @FXML
  Label slostLabel;
  @FXML
  Label mlostLabel;
  @FXML
  Label sdrawLabel;
  @FXML
  Label mdrawLabel;
  @FXML
  Label swinpLabel;
  @FXML
  Label mwinpLabel;

  /**
   * Edit profile.
   */
  @FXML
  void onEditProfile() {
    logger.info("onEditProfile: no params");
    try {
      Parent proot =
          FXMLLoader.load(Objects.requireNonNull(
              getClass().getResource("/fxml/connectfour/ProfilesEditScreen.fxml")));
      Stage stage = new Stage();
      stage.setTitle("Edit Profile " + pinfo.getName());
      stage.setScene(new Scene(proot, 426, 240));
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setResizable(false);
      stage.showAndWait();
      SceneChanger.changeScene(root, "/fxml/connectfour/ProfilesInfoScreen.fxml");
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }

  /**
   * Go back to profiles-screen.
   */
  @FXML
  void onBack() {
    logger.info("onBack: no params");
    SceneChanger.changeScene(root, "/fxml/connectfour/ProfilesScreen.fxml");
  }

  /**
   * Initialize the controller.
   */
  @FXML
  public void initialize() {
    logger.info("initialize: no params");
    if (pinfo == null) {
      SceneChanger.changeScene(root, "/fxml/connectfour/ProfilesScreen.fxml");
    } else {
      nameLabel.setText(pinfo.getName());
      swonLabel.setText(pinfo.getSingleplayerWin() + "");
      mwonLabel.setText(pinfo.getMultiplayerWin() + "");
      slostLabel.setText(pinfo.getSingleplayerLoose() + "");
      mlostLabel.setText(pinfo.getMultiplayerLoose() + "");
      sdrawLabel.setText(pinfo.getSingleplayerDraw() + "");
      mdrawLabel.setText(pinfo.getMultiplayerDraw() + "");
      swinpLabel.setText(pinfo.getSingleplayerWinPercentage() + "");
      mwinpLabel.setText(pinfo.getMultiplayerWinPercentage() + "");
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
}
