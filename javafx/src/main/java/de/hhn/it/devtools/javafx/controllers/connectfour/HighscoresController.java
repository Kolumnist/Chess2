package de.hhn.it.devtools.javafx.controllers.connectfour;

import de.hhn.it.devtools.apis.connectfour.helper.Profile;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.Instance;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.sorting.ProfileMultiplayerComparator;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.sorting.ProfileNameComparator;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.sorting.ProfileSingleplayerComparator;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.sorting.SortBy;
import java.util.LinkedList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

/**
 * Controller for the highscores screen.
 */
public class HighscoresController {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(HighscoresController.class);

  @FXML
  Pane root;
  @FXML
  ChoiceBox<SortBy> sortByChoiceBox;
  @FXML
  ListView<Profile> ranking;
  @FXML
  ListView<Integer> position;
  @FXML
  ListView<Integer> wins;

  @FXML
  void onBack() {
    logger.info("onBack: no params");
    SceneChanger.changeScene(root, "/fxml/ConnectFour.fxml");
  }

  @FXML
  public void initialize() {
    logger.info("initialize: no params");
    sortByChoiceBox.getItems().addAll(SortBy.values());
    sortByChoiceBox.setValue(SortBy.Multiplayer_RANKING);
    List<Profile> profiles = new LinkedList<>(Instance.getInstance().getProfiles().values());
    sortByChoiceBox.setOnAction(event -> {
      ranking.getItems().clear();
      position.getItems().clear();
      wins.getItems().clear();
      if (sortByChoiceBox.getValue() == SortBy.Multiplayer_RANKING) {
        profiles.sort(new ProfileMultiplayerComparator());
        ranking.getItems().addAll(profiles);
        for (int i = 0; i < profiles.size(); i++) {
          position.getItems().add(i+1);
          wins.getItems().add(profiles.get(i).getMultiplayerWin());
        }
      } else {
        profiles.sort(new ProfileSingleplayerComparator());
        ranking.getItems().addAll(profiles);
        for (int i = 0; i < profiles.size(); i++) {
          position.getItems().add(i+1);
          wins.getItems().add(profiles.get(i).getSingleplayerWin());
        }
      }
    });
    sortByChoiceBox.fireEvent(new ActionEvent());
  }
}
