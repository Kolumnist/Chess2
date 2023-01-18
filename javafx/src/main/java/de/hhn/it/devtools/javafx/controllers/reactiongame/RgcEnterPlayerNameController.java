package de.hhn.it.devtools.javafx.controllers.reactiongame;

import de.hhn.it.devtools.components.reactiongame.provider.RgcService;
import de.hhn.it.devtools.javafx.controllers.ReactionGameController;
import de.hhn.it.devtools.javafx.controllers.template.SingletonAttributeStore;
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
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    service = (RgcService) singletonAttributeStore.getAttribute(ReactionGameController.RGC_SERVICE);

    nameTf.setText(service.getCurrentPlayer().getName());
  }
}
