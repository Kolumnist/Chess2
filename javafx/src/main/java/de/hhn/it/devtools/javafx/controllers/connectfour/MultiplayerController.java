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
 * Controller for multiplayer mode.
 */
public class MultiplayerController implements Initializable {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(MultiplayerController.class);

  Profile player1 = null;
  Profile player2 = null;
  StartingPlayer startingPlayer = null;

  @FXML
  Pane root;
  @FXML
  ChoiceBox<Profile> player1ChoiceBox;
  @FXML
  ChoiceBox<Profile> player2ChoiceBox;
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
      case PLAYER1 -> instance.playMultiplayerGame(player1, player2, true);
      case PLAYER2 -> instance.playMultiplayerGame(player1, player2, false);
      default -> instance.playMultiplayerGame(player1, player2, Math.random() > 0.5);
    }
    SceneChanger.changeScene(root, "/fxml/connectfour/GameScreen.fxml");
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    logger.info("initialize: location = {}, resources = {}", location, resources);
    // Player 1:
    List<Profile> profiles = new LinkedList<>(Instance.getInstance().getProfiles().values());
    Comparator<Profile> comparator = new ProfileNameComparator();
    profiles.sort(comparator);
    player1ChoiceBox.getItems().addAll(profiles);
    player1ChoiceBox.setOnAction(event -> {
      player1 = player1ChoiceBox.getValue();
      player1ChoiceBox.setDisable(true);
      // Player 2:
      for (Profile profile : profiles) {
        if (profile != player1) {
          player2ChoiceBox.getItems().add(profile);
        }
      }
      player2ChoiceBox.setDisable(false);
      player2ChoiceBox.setOnAction(event2 -> {
        player2ChoiceBox.setDisable(true);
        player2 = player2ChoiceBox.getValue();
        // Starting player:
        startingPlayerChoiceBox.getItems().add(StartingPlayer.PLAYER1);
        startingPlayerChoiceBox.getItems().add(StartingPlayer.PLAYER2);
        startingPlayerChoiceBox.getItems().add(StartingPlayer.RANDOM);
        startingPlayerChoiceBox.setDisable(false);
        startingPlayerChoiceBox.setOnAction(event3 -> {
          startingPlayerChoiceBox.setDisable(true);
          startingPlayer = startingPlayerChoiceBox.getValue();
          // Start.
          startButton.setDisable(false);
        });
      });
    });
  }
}
