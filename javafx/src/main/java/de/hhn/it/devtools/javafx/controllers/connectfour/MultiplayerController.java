package de.hhn.it.devtools.javafx.controllers.connectfour;

import de.hhn.it.devtools.apis.connectfour.helper.Profile;
import de.hhn.it.devtools.components.connectfour.provider.ConnectFour;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.Instance;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.sorting.ProfileNameComparator;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.enums.StartingPlayer;
import java.net.URL;
import java.util.Comparator;
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
    logger.info("Going back");
    SceneChanger.changeScene(root, "/fxml/ConnectFour.fxml");
  }

  @FXML
  void onStart() {
    logger.info("Starting");
    ConnectFour game = Instance.getConnectFour();
    game.setMode(Mode.MULTIPLAYER);
    switch (startingPlayer) {
      case PLAYER1 -> {
        game.setPlayer1(player1);
        game.setPlayer2(player2);
      }
      case PLAYER2 -> {
        game.setPlayer1(player2);
        game.setPlayer2(player1);
      }
      case RANDOM -> {
        if (Math.random() > 0.5) {
          game.setPlayer1(player1);
          game.setPlayer2(player2);
        } else {
          game.setPlayer1(player2);
          game.setPlayer2(player1);
        }
      }
      default -> logger.debug("Something went wrong");
    }
    SceneChanger.changeScene(root, "/fxml/connectfour/GameScreen.fxml");
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    logger.info("Initializing");

    // player 1
    List<Profile> profiles = Instance.getConnectFour().getProfiles();
    Comparator<Profile> comparator = new ProfileNameComparator();
    profiles.sort(comparator);
    player1ChoiceBox.getItems().addAll(profiles);
    player1ChoiceBox.setOnAction(event -> {
      player1 = player1ChoiceBox.getValue();
      player1ChoiceBox.setDisable(true);
      logger.info("Player 1: " + player1);
      // player 2
      for (Profile profile : Instance.getConnectFour().getProfiles()) {
        if (profile != player1) {
          player2ChoiceBox.getItems().add(profile);
        }
      }
      player2ChoiceBox.setDisable(false);
      player2ChoiceBox.setOnAction(event2 -> {
        player2ChoiceBox.setDisable(true);
        player2 = player2ChoiceBox.getValue();
        logger.info("Player 2: " + player2);
        // starting player
        startingPlayerChoiceBox.getItems().add(StartingPlayer.PLAYER1);
        startingPlayerChoiceBox.getItems().add(StartingPlayer.PLAYER2);
        startingPlayerChoiceBox.getItems().add(StartingPlayer.RANDOM);
        startingPlayerChoiceBox.setDisable(false);
        startingPlayerChoiceBox.setOnAction(event3 -> {
          startingPlayerChoiceBox.setDisable(true);
          startingPlayer = startingPlayerChoiceBox.getValue();
          logger.info("Starting player: " + startingPlayer);
          // start
          startButton.setDisable(false);
        });
      });
    });
  }
}
