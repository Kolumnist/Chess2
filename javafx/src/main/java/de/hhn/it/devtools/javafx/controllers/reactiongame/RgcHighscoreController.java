package de.hhn.it.devtools.javafx.controllers.reactiongame;

import de.hhn.it.devtools.components.reactiongame.provider.RgcService;
import de.hhn.it.devtools.javafx.controllers.ReactionGameController;
import de.hhn.it.devtools.javafx.controllers.template.SingletonAttributeStore;
import de.hhn.it.devtools.javafx.reactiongame.RgcHighScoreHandler;
import de.hhn.it.devtools.apis.reactiongame.HighscoreTupel;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class RgcHighscoreController implements Initializable {

  private static final SingletonAttributeStore singletonAttributeStore
      = SingletonAttributeStore.getReference();
  private RgcScreenController screenController;
  @FXML
  private Label highscore1;

  @FXML
  private Label highscore2;

  @FXML
  private Label highscore3;

  @FXML
  private Label highscore4;

  @FXML
  private Label highscore5;

  @FXML
  private Label highscore6;


  @Override
  public void initialize(URL location, ResourceBundle resources) {
    screenController = (RgcScreenController)
        singletonAttributeStore.getAttribute(ReactionGameController.RGC_SCREEN_CONTROLLER);

    try {
      readHighscores();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void readHighscores() throws IOException {

    RgcService service = (RgcService)
        singletonAttributeStore.getAttribute(ReactionGameController.RGC_SERVICE);

    ArrayList<HighscoreTupel> scores = service.saveHighscoreTable();

    RgcHighScoreHandler.writeHighscoreList(scores);

    ArrayList<Label> labels = new ArrayList<>() {
      {
        add(highscore1);
        add(highscore2);
        add(highscore3);
        add(highscore4);
        add(highscore5);
        add(highscore6);
      }
    };



    for (int i = 0; i < (Math.min(scores.size(), 6)); i++) {
      Label label = labels.get(i);
      label.setText(scores.get(i).name() + " - " + scores.get(i).score());
    }
  }

  @FXML
  void onBackToMenuBtn() {
    try {
      screenController.switchTo("RgcMenu");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
