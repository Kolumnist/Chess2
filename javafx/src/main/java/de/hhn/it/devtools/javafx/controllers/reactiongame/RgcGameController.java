package de.hhn.it.devtools.javafx.controllers.reactiongame;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.reactiongame.Difficulty;
import de.hhn.it.devtools.components.reactiongame.provider.RgcService;
import de.hhn.it.devtools.javafx.controllers.ReactionGameController;
import de.hhn.it.devtools.javafx.controllers.template.SingletonAttributeStore;
import de.hhn.it.devtools.javafx.reactiongame.RgcListener;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class RgcGameController implements Initializable {

  private static SingletonAttributeStore singletonAttributeStore = SingletonAttributeStore.getReference();

  public RgcGameController() {

  }

  @FXML // fx:id="infoLable"
  private Label infoLable; // Value injected by FXMLLoader

  @FXML // fx:id="anchorPane"
  private AnchorPane anchorPane; // Value injected by FXMLLoader

  @FXML // fx:id="liveLable"
  private Label liveLable; // Value injected by FXMLLoader

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    RgcService service = (RgcService) singletonAttributeStore.getAttribute(ReactionGameController.RGC_SERVICE);

    try {
      service.newRun(
          (Difficulty) singletonAttributeStore.getAttribute(RgcChooseDifficultyController.DIFFICULTY));
    } catch (IllegalParameterException e) {
      throw new RuntimeException(e);
    }

    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/template/ThirdScreen.fxml"));


    RgcListener listener = new RgcListener(
        (AnchorPane) singletonAttributeStore.getAttribute(ReactionGameController.RGC_ANCHOR_PANE),
        service, this);
  }


  public Label getLiveLable() {
    return liveLable;
  }

  public Label getInfoLable() {
    return infoLable;
  }
}
