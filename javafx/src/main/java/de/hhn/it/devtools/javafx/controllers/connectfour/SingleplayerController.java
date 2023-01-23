package de.hhn.it.devtools.javafx.controllers.connectfour;

import de.hhn.it.devtools.apis.connectfour.helper.Profile;
import de.hhn.it.devtools.apis.connectfour.interfaces.ConnectFourInterface;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.Instance;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.enums.StartingPlayer;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.sorting.ProfileNameComparator;
import java.net.URL;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;

/**
 * Controller for singleplayer mode.
 */
public class SingleplayerController implements Initializable {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SingleplayerController.class);

  Profile player = null;
  StartingPlayer startingPlayer = null;

  @FXML
  Pane root;
  @FXML
  ChoiceBox<Profile> playerChoiceBox;
  @FXML
  ChoiceBox<StartingPlayer> startingPlayerChoiceBox;
  @FXML
  Button startButton;

  @FXML
  void onBack() {
    logger.info("onBack: no params");
    SceneChanger.changeScene(root, "/fxml/ConnectFour.fxml");
  }

  @FXML
  void onStart() {
    logger.info("onStart: no params");
    ConnectFourInterface instance = Instance.getInstance();
    switch (startingPlayer) {
      case HUMAN -> instance.playSingleplayerGame(player, true);
      case COMPUTER -> instance.playSingleplayerGame(player, false);
      default -> instance.playSingleplayerGame(player, Math.random() > 0.5);
    }
    SceneChanger.changeScene(root, "/fxml/connectfour/GameScreen.fxml");
  }

  /**
   * Initializes the singleplayer controller.
   *
   * @param location  Unused.
   * @param resources Unused.
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    logger.info("initialize: {}, {}", location, resources);
    // Choose profile:
    List<Profile> profiles = new LinkedList<>(Instance.getInstance().getProfiles().values());
    Comparator<Profile> comparator = new ProfileNameComparator();
    profiles.sort(comparator);
    playerChoiceBox.getItems().addAll(profiles);
    // Set action:
    playerChoiceBox.setOnAction(event -> {
      playerChoiceBox.setDisable(true);
      player = playerChoiceBox.getValue();
      // Choose starting player:
      startingPlayerChoiceBox.getItems().add(StartingPlayer.HUMAN);
      startingPlayerChoiceBox.getItems().add(StartingPlayer.COMPUTER);
      startingPlayerChoiceBox.getItems().add(StartingPlayer.RANDOM);
      startingPlayerChoiceBox.setDisable(false);
      // Set action:
      startingPlayerChoiceBox.setOnAction(event2 -> {
        startingPlayerChoiceBox.setDisable(true);
        startingPlayer = startingPlayerChoiceBox.getValue();
        // Allow start.
        startButton.setDisable(false);
      });
    });
  }
}
