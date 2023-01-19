package de.hhn.it.devtools.javafx.controllers.connectfour;

import de.hhn.it.devtools.apis.connectfour.helper.Profile;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.Instance;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.sorting.ProfileMultiplayerComparator;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.sorting.ProfileSingleplayerComparator;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.sorting.SortBy;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

/**
 * Controller for the highscores screen.
 */
public class HighscoresController implements Initializable {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(HighscoresController.class);

  @FXML
  Pane root;

  @FXML
  ChoiceBox sortByChoiceBox;

  @FXML
  ListView<Profile> ranking;

  @FXML
  ListView<Integer> position;

  @FXML
  ListView<Integer> wins;


  @FXML
  void onBack() {
    logger.info("Going back...");
    SceneChanger.changeScene(root, "/fxml/ConnectFour.fxml");
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    logger.info("Initializing...");
    sortByChoiceBox.getItems().addAll(SortBy.values());
    sortByChoiceBox.setValue(SortBy.Multiplayer_RANKING);
    sortByChoiceBox.setOnAction(event -> {
      ranking.getItems().clear();
      position.getItems().clear();
      wins.getItems().clear();
      List<Profile> profiles = Instance.getConnectFour().getProfiles();
      if (sortByChoiceBox.getValue() == SortBy.Multiplayer_RANKING) {
        logger.info("sort by multiplayer");
        profiles.sort(new ProfileMultiplayerComparator());
      } else {
        profiles.sort(new ProfileSingleplayerComparator());
        logger.info("sort by singleplayer");
      }
      ranking.getItems().addAll(profiles);
      for (int i = 1; i <= profiles.size(); i++) {
        position.getItems().add(i);
        wins.getItems().add(profiles.get(i - 1).getSingleplayerWin());
      }
    });
    List<Profile> profiles = Instance.getConnectFour().getProfiles();
    logger.info("sort by multiplayer");
    profiles.sort(new ProfileMultiplayerComparator());
    ranking.getItems().addAll(profiles);
    for (int i = 1; i <= profiles.size(); i++) {
      position.getItems().add(i);
      wins.getItems().add(profiles.get(i - 1).getMultiplayerWin());
    }
  }
}
