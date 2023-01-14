package de.hhn.it.devtools.javafx.controllers.reactiongame;

import de.hhn.it.devtools.javafx.controllers.ReactionGameController;
import de.hhn.it.devtools.javafx.controllers.template.SingletonAttributeStore;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class RgcHighscoreController implements Initializable {

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
    SingletonAttributeStore singletonAttributeStore = SingletonAttributeStore.getReference();
    screenController =
        (RgcScreenController) singletonAttributeStore.getAttribute(ReactionGameController.RGC_SCREEN_CONTROLLER);

    readHighscores();
  }

  private void readHighscores() {

  }

  private void writeHighscores() {

  }

  @FXML
  void onBackToMenuBtn(ActionEvent event) {
    writeHighscores();

    try {
      screenController.switchTo("RgcMenu");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
