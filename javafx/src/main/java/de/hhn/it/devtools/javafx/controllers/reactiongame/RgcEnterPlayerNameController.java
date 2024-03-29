package de.hhn.it.devtools.javafx.controllers.reactiongame;

import de.hhn.it.devtools.components.reactiongame.provider.RgcService;
import de.hhn.it.devtools.javafx.controllers.ReactionGameController;
import de.hhn.it.devtools.javafx.controllers.template.SingletonAttributeStore;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class RgcEnterPlayerNameController implements Initializable {

  private static final SingletonAttributeStore singletonAttributeStore
      = SingletonAttributeStore.getReference();

  private RgcService service;
  @FXML
  private TextField nameTf;

  @FXML
  void onContinueBtn() {

    if (!(nameTf.getText().equals(service.getCurrentPlayer().getName()))) {

      if (nameTf.getText().equals("")) {
        service.setCurrentPlayerName("Player");
      } else {
        service.setCurrentPlayerName(nameTf.getText());
      }
    }

    RgcScreenController screenController = (RgcScreenController)
        singletonAttributeStore.getAttribute(ReactionGameController.RGC_SCREEN_CONTROLLER);

    try {
      screenController.switchTo("RgcHighscores");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    service = (RgcService) singletonAttributeStore.getAttribute(ReactionGameController.RGC_SERVICE);

    nameTf.setText(service.getCurrentPlayer().getName());
  }
}
