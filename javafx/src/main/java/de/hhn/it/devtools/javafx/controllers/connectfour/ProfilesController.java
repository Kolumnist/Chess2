package de.hhn.it.devtools.javafx.controllers.connectfour;

import de.hhn.it.devtools.apis.connectfour.helper.Profile;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.Instance;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This class models the profiles controller.
 */
public class ProfilesController {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(HighscoresController.class);

  @FXML
  Pane root;
  @FXML
  VBox verticalBox;
  @FXML
  Button btnNewProfile;

  @FXML
  void onNewProfile() {
    logger.info("onNewProfile: no params");
    try {
      Parent proot = FXMLLoader.load(Objects.requireNonNull(
          getClass().getResource("/fxml/connectfour/ProfilesNewScreen.fxml")));
      Stage stage = new Stage();
      stage.setTitle("New Profile");
      stage.setScene(new Scene(proot, 426, 240));
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setResizable(false);
      stage.showAndWait();
      SceneChanger.changeScene(root, "/fxml/connectfour/ProfilesScreen.fxml");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  void onProfile(ActionEvent event) {
    logger.info("onProfile: event = {}", event);
    Button button = (Button) event.getTarget();
    try {
      ProfilesInfoController.pinfo =
          Instance.getInstance().getProfile(UUID.fromString(button.getId()));
    } catch (NoSuchElementException e) {
      SceneChanger.changeScene(root, "/fxml/connectfour/ProfilesScreen.fxml");
    }
    SceneChanger.changeScene(root, "/fxml/connectfour/ProfilesInfoScreen.fxml");
  }

  /**
   * Go back to main screen.
   */
  @FXML
  void onBack() {
    logger.info("onBack: no params");
    SceneChanger.changeScene(root, "/fxml/ConnectFour.fxml");
  }

  /**
   * Add profile button.
   *
   * @param p The profile button.
   */
  public void addProfileButton(Profile p) {
    logger.info("addProfile: p = {}", p);
    Button next = new Button();
    next.setPrefSize(200, 40);
    next.setText(p.getName());
    next.setId(p.getId() + "");
    next.setOnAction(this::onProfile);
    verticalBox.getChildren().add(next);
  }

  /**
   * Initialize the controller.
   */
  @FXML
  public void initialize() {
    logger.info("initialize: no params");
    List<Profile> profiles =
        new LinkedList<>(Instance.getInstance().getProfiles().values().stream().toList());
    profiles.sort(Comparator.comparing(Profile::getName));
    verticalBox.getChildren().clear();
    for (Profile profile : profiles) {
      addProfileButton(profile);
    }
  }
}
