package de.hhn.it.devtools.javafx.controllers.reactiongame;

import de.hhn.it.devtools.javafx.controllers.ReactionGameController;
import de.hhn.it.devtools.javafx.controllers.template.SingletonAttributeStore;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class RgcHighscoreController implements Initializable {

  private RgcScreenController screenController;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    SingletonAttributeStore singletonAttributeStore = SingletonAttributeStore.getReference();
    screenController =
        (RgcScreenController) singletonAttributeStore.getAttribute(ReactionGameController.RGC_SCREEN_CONTROLLER);
  }

  @FXML
  void onBackToMenuBtn(ActionEvent event) {
    try {
      screenController.switchTo("RgcMenu");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
