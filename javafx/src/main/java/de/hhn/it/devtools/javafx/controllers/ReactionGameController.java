package de.hhn.it.devtools.javafx.controllers;


import de.hhn.it.devtools.components.reactiongame.provider.RgcService;
import de.hhn.it.devtools.javafx.controllers.reactiongame.RgcScreenController;
import de.hhn.it.devtools.javafx.controllers.template.SingletonAttributeStore;
import de.hhn.it.devtools.javafx.reactiongame.RgcHighScoreHandler;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * ReactionGameController for set up screen controller etc
 */
public class ReactionGameController extends Controller implements Initializable {

  public static final String RGC_SCREEN_CONTROLLER = "rgc.screen.controller";
  public static final String RGC_SERVICE = "rgc.service";
  public static final String RGC_PLAYER_NAME = "rgc.player.name";
  public static final String RGC_ANCHOR_PANE = "rgc.anchor.pane";
  public static final String RGC_BACKGROUND_PANE = "rgc.background.pane";

  @FXML // fx:id="anchorPane"
  private AnchorPane anchorPane; // Value injected by FXMLLoader
  @FXML
  private Pane backgroundPane;


  @FXML
  void onMouseEntered() {
  }

  @FXML
  void onMouseMoved() {
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    RgcScreenController screenController = new RgcScreenController(anchorPane);
    RgcService service = new RgcService();

    SingletonAttributeStore singletonAttributeStore = SingletonAttributeStore.getReference();
    singletonAttributeStore.setAttribute(RGC_SCREEN_CONTROLLER, screenController);

    singletonAttributeStore.setAttribute(RGC_SERVICE, service);

    singletonAttributeStore.setAttribute(RGC_PLAYER_NAME, "Player");

    singletonAttributeStore.setAttribute(RGC_ANCHOR_PANE, anchorPane);

    singletonAttributeStore.setAttribute(RGC_BACKGROUND_PANE, backgroundPane);

    RgcHighScoreHandler.highscoreList = RgcHighScoreHandler.readHighscoreList();
    service.loadHighscoreTable(RgcHighScoreHandler.highscoreList);

    try {
      screenController.switchTo("RgcMenu");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}

