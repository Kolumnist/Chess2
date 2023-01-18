package de.hhn.it.devtools.javafx.controllers.connectfour;

import de.hhn.it.devtools.apis.connectfour.Mode;
import de.hhn.it.devtools.apis.connectfour.Profile;
import de.hhn.it.devtools.components.connectfour.provider.ConnectFour;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.Instance;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.ProfileNameComparator;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.StartingPlayer;
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
    logger.info("Going back");
    SceneChanger.changeScene(root, "/fxml/ConnectFour.fxml");
  }

  @FXML
  void onStart() {
    logger.info("Starting");
    ConnectFour game = Instance.getInstance();
    game.setMode(Mode.SINGLEPLAYER);
    switch (startingPlayer) {
      case HUMAN -> {
        game.chooseProfileA(player);
        game.chooseProfileB(game.getComputer());
      }
      case COMPUTER -> {
        game.chooseProfileA(game.getComputer());
        game.chooseProfileB(player);
      }
      case RANDOM -> {
        if (Math.random() > 0.5) {
          game.chooseProfileA(player);
          game.chooseProfileB(game.getComputer());
        } else {
          game.chooseProfileA(game.getComputer());
          game.chooseProfileB(player);
        }
      }
      default -> logger.debug("Something went wrong");
    }
    SceneChanger.changeScene(root, "/fxml/connectfour/GameScreen.fxml");
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    logger.info("Initializing");
    Instance.reset();
    // player
    List<Profile> profiles = Instance.getInstance().getProfiles();
    Comparator<Profile> comparator = new ProfileNameComparator();
    profiles.sort(comparator);
    playerChoiceBox.getItems().addAll(profiles);
    playerChoiceBox.setOnAction(event -> {
      playerChoiceBox.setDisable(true);
      player = playerChoiceBox.getValue();
      logger.info("Player: " + player);
      // starting player
      startingPlayerChoiceBox.getItems().add(StartingPlayer.HUMAN);
      startingPlayerChoiceBox.getItems().add(StartingPlayer.COMPUTER);
      startingPlayerChoiceBox.getItems().add(StartingPlayer.RANDOM);
      startingPlayerChoiceBox.setDisable(false);
      startingPlayerChoiceBox.setOnAction(event2 -> {
        startingPlayerChoiceBox.setDisable(true);
        startingPlayer = startingPlayerChoiceBox.getValue();
        logger.info("Starting player: " + startingPlayer);
        // start
        startButton.setDisable(false);
      });
    });
  }
}
