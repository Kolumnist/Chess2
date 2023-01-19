package de.hhn.it.devtools.javafx.controllers.connectfour;


import de.hhn.it.devtools.apis.connectfour.helper.Profile;
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

  @FXML
  void onEditProfile() {
    try {
      Parent proot = FXMLLoader.load(Objects.requireNonNull(
          getClass().getResource("/fxml/connectfour/ProfilesEditScreen.fxml")));
      Stage stage = new Stage();
      stage.setTitle("New Profile");
      stage.setScene(new Scene(proot, 854, 480));
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setResizable(false);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  void onBack() {
    logger.info("Going back...");
    SceneChanger.changeScene(root, "/fxml/connectfour/ProfilesScreen.fxml");
  }

  @FXML
  public void initialize() {
    nameLabel.setText(pinfo.getName());
    swonLabel.setText(pinfo.getSingleplayerWin() + "");
    mwonLabel.setText(pinfo.getMultiplayerWin() + "");
    slostLabel.setText(pinfo.getSingleplayerLoose() + "");
    mlostLabel.setText(pinfo.getMultiplayerLoose() + "");
    sdrawLabel.setText(pinfo.getSingleplayerDraw() + "");
    mdrawLabel.setText(pinfo.getMultiplayerDraw() + "");
    swinpLabel.setText(pinfo.getSingleplayerWinPercentage() + "");
    mwinpLabel.setText(pinfo.getMultiplayerWinPercentage() + "");
    Image profileImg = new Image("/fxml/connectfour/files/dummy-profile-pic.png");
    profileImageView.setImage(profileImg);

  }
}
