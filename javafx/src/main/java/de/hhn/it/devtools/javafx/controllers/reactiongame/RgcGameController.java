package de.hhn.it.devtools.javafx.controllers.reactiongame;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.reactiongame.Difficulty;
import de.hhn.it.devtools.apis.reactiongame.GameState;
import de.hhn.it.devtools.components.reactiongame.provider.RgcService;
import de.hhn.it.devtools.javafx.controllers.ReactionGameController;
import de.hhn.it.devtools.javafx.controllers.template.SingletonAttributeStore;
import de.hhn.it.devtools.javafx.reactiongame.RgcIngameTimer;
import de.hhn.it.devtools.javafx.reactiongame.RgcListener;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RgcGameController implements Initializable {

  public static String RGC_PLAYER_X_POSITION = "rgc.player.pos.x";

  public static String RGC_PLAYER_Y_POSITION = "rgc.player.pos.y";

  public static String RGC_GAME_NODES = "rgc.game.nodes";

  private static final SingletonAttributeStore singletonAttributeStore
      = SingletonAttributeStore.getReference();


  private RgcService service;

  private EventHandler<KeyEvent> keyHandler;

  private Stage stage;
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
  void gpOnMouseEntered() {
    service.playerLeftGameObject();
  }

  @FXML
  void gpOnMouseClicked() {
    System.out.println("Mouse clicked");
  }

  @FXML
  void gpOnMouseMoved(MouseEvent event) {
    singletonAttributeStore.setAttribute(RgcGameController.RGC_PLAYER_X_POSITION, event.getX());
    singletonAttributeStore.setAttribute(RgcGameController.RGC_PLAYER_Y_POSITION, event.getY());
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    service = (RgcService) singletonAttributeStore.getAttribute(ReactionGameController.RGC_SERVICE);
    anchorPane = (AnchorPane) singletonAttributeStore.getAttribute(
        ReactionGameController.RGC_ANCHOR_PANE);
    scoreLabel.setText("000");
    timeLabel.setText("0");

    anchorPane.addEventFilter(MouseEvent.MOUSE_MOVED, e -> {

    });

    try {
      service.newRun((Difficulty) singletonAttributeStore.getAttribute(
          RgcChooseDifficultyController.DIFFICULTY));
    } catch (IllegalParameterException e) {
      throw new RuntimeException(e);
    }

    singletonAttributeStore.setAttribute(RgcGameController.RGC_PLAYER_X_POSITION, 0d);
    singletonAttributeStore.setAttribute(RgcGameController.RGC_PLAYER_X_POSITION, 0d);

    if (service.getRun().getGameState() != GameState.RUNNING) {
      service.continueRun();
    }

    stage = (Stage) anchorPane.getScene().getWindow();

    keyHandler = event -> {
      if (event.getCode() == KeyCode.ESCAPE) {
        try {
          if (service.getRun().getGameState() == GameState.RUNNING) {
            openPauseMenu();
          }
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        return;
        //TODO: Spiel return hier nach dem zweiten Spiel erneut raus.
      }

      String key = event.getText();
      service.keyPressed(key.charAt(0));
    };

    stage.addEventFilter(KeyEvent.KEY_PRESSED, keyHandler);

    new RgcIngameTimer(service.getRun(), timeLabel);

    new RgcListener(
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

  /**
   * Set a button at the current mouse position to continue the game
   *
   * @throws IOException exception
   */
  public void openPauseMenu() throws IOException {
    singletonAttributeStore.setAttribute(RgcGameController.RGC_GAME_NODES,
        anchorPane.getChildren());
    service.pauseRun();

    double x = (double) singletonAttributeStore.getAttribute(
        RgcGameController.RGC_PLAYER_X_POSITION);
    double y = (double) singletonAttributeStore.getAttribute(
        RgcGameController.RGC_PLAYER_Y_POSITION);

    Button b = new Button("Continue");

    b.setMinWidth(30);
    b.setMinHeight(30);

    b.setMaxWidth(30);
    b.setMinHeight(30);


    b.setOnAction(event -> {
      service.continueRun();

      anchorPane.getChildren().remove(b);
    });

    b.setLayoutX(x - 15 > 0 ? x - 20 : 0);
    b.setLayoutY(y - 15 > 0 ? y - 20 : 0);

    anchorPane.getChildren().add(b);
  }

  public void deleteHandler() {
    stage.removeEventFilter(KeyEvent.KEY_PRESSED, keyHandler);
  }
}
