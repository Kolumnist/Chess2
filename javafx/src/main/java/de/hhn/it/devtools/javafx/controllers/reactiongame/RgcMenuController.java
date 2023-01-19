/**
 * Sample Skeleton for 'RgcMenu.fxml' Controller Class
 */

package de.hhn.it.devtools.javafx.controllers.reactiongame;

import de.hhn.it.devtools.javafx.controllers.Controller;
import de.hhn.it.devtools.javafx.controllers.ReactionGameController;
import de.hhn.it.devtools.javafx.controllers.template.SingletonAttributeStore;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class RgcMenuController extends Controller implements Initializable {

  private RgcScreenController screenController;

  @FXML
  void onEndgameBtn(ActionEvent event) {
    System.exit(0);
  }

  @FXML
  void onHighscoreBtn(ActionEvent event) {
    try {
      screenController.switchTo("RgcHighscores");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @FXML
  void onStartGameBtn(ActionEvent event) {
    try {
      screenController.switchTo("RgcChooseDifficulty");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  @FXML
  void onSoundBtn(ActionEvent actionEvent) {
    try {
      screenController.switchTo("RgcSoundMenu");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    SingletonAttributeStore singletonAttributeStore = SingletonAttributeStore.getReference();
    screenController = (RgcScreenController)
        singletonAttributeStore.getAttribute(ReactionGameController.RGC_SCREEN_CONTROLLER);
  }


}
