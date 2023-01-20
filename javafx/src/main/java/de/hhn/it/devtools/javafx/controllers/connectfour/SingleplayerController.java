package de.hhn.it.devtools.javafx.controllers.connectfour;

import de.hhn.it.devtools.apis.connectfour.helper.Profile;
import de.hhn.it.devtools.components.connectfour.provider.ConnectFour;
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

  Profile player1 = null;
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
    ConnectFour instance = Instance.getInstance();
    switch (startingPlayer) {
      case PLAYER1 -> instance.playSingleplayerGame(player1, true);
      case PLAYER2 -> instance.playSingleplayerGame(player1, false);
      default -> instance.playSingleplayerGame(player1, Math.random() > 0.5);
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
      player1 = playerChoiceBox.getValue();
      logger.info("Player: " + player1);
      // Choose starting player:
      for (StartingPlayer value : StartingPlayer.values()) {
        startingPlayerChoiceBox.getItems().add(value);
      }
      startingPlayerChoiceBox.setDisable(false);
      // Set action:
      startingPlayerChoiceBox.setOnAction(event2 -> {
        startingPlayerChoiceBox.setDisable(true);
        startingPlayer = startingPlayerChoiceBox.getValue();
        logger.info("Starting player: " + startingPlayer);
        // Allow start.
        startButton.setDisable(false);
      });
    });
  }
}
