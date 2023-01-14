package de.hhn.it.devtools.javafx.controllers.reactiongame;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.reactiongame.Difficulty;
import de.hhn.it.devtools.components.reactiongame.provider.RgcService;
import de.hhn.it.devtools.javafx.controllers.ReactionGameController;
import de.hhn.it.devtools.javafx.controllers.template.SingletonAttributeStore;
import de.hhn.it.devtools.javafx.reactiongame.RgcListener;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RgcGameController implements Initializable {

  private static SingletonAttributeStore singletonAttributeStore = SingletonAttributeStore.getReference();


  private RgcService service;
  @FXML // fx:id="gamePane"
  private Pane gamePane; // Value injected by FXMLLoader
  @FXML // fx:id="infoLable"
  private Label infoLable; // Value injected by FXMLLoader

  @FXML // fx:id="anchorPane"
  private AnchorPane anchorPane; // Value injected by FXMLLoader

  @FXML // fx:id="liveLable"
  private Label liveLable; // Value injected by FXMLLoader

  @FXML // fx:id="scoreLabel"
  private Label scoreLabel; // Value injected by FXMLLoader

  @FXML // fx:id="timeLabel"
  private Label timeLabel; // Value injected by FXMLLoader

  @FXML
  void gpOnMouseEntered(MouseEvent event) {
    service.playerLeftGameObject();
  }
  @FXML
  void gpOnMouseClicked(MouseEvent event) {
    System.out.println("Mouse clicked");
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    service = (RgcService) singletonAttributeStore.getAttribute(ReactionGameController.RGC_SERVICE);
    anchorPane = (AnchorPane) singletonAttributeStore.getAttribute(ReactionGameController.RGC_ANCHOR_PANE);
    scoreLabel.setText("00000");
    timeLabel.setText("0000");

    try {
      service.newRun(
          (Difficulty) singletonAttributeStore.getAttribute(RgcChooseDifficultyController.DIFFICULTY));
    } catch (IllegalParameterException e) {
      throw new RuntimeException(e);
    }

    Stage stage = (Stage) anchorPane.getScene().getWindow();
    stage.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
      String key = event.getText();
      service.keyPressed(key.charAt(0));
    });

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

  public Label getScoreLabel() {
    return scoreLabel;
  }

  public Label getTimeLabel() {
    return timeLabel;
  }
}
