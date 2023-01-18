package de.hhn.it.devtools.javafx.controllers;


import de.hhn.it.devtools.components.reactiongame.provider.RgcService;
import de.hhn.it.devtools.javafx.controllers.reactiongame.RgcScreenController;
import de.hhn.it.devtools.javafx.controllers.template.SingletonAttributeStore;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.TreeMap;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * ReactionGameController for set up screen controller etc
 */
public class ReactionGameController extends Controller implements Initializable {

  public static final String RGC_SCREEN_CONTROLLER = "rgc.screen.controller";
  public static final String RGC_SERVICE = "rgc.service";
  public static final String RGC_PLAYER_NAME = "rgc.player.name";
  public static final String RGC_ANCHOR_PANE = "rgc.anchor.pane";

  public static final String RGC_HIGHSCORE_LIST = "rgc.highscore.listL";

  @FXML // fx:id="anchorPane"
  private AnchorPane anchorPane; // Value injected by FXMLLoader

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    RgcScreenController screenController = new RgcScreenController(anchorPane);
    SingletonAttributeStore singletonAttributeStore = SingletonAttributeStore.getReference();
    singletonAttributeStore.setAttribute(RGC_SCREEN_CONTROLLER, screenController);

    singletonAttributeStore.setAttribute(RGC_SERVICE, new RgcService());

    singletonAttributeStore.setAttribute(RGC_PLAYER_NAME, "Player");

    singletonAttributeStore.setAttribute(RGC_ANCHOR_PANE, anchorPane);

    TreeMap<String, String> highscoreList = new TreeMap<>();
    Properties properties = new Properties();
    try {
      properties.load(new FileInputStream(
          String.valueOf(
              getClass().getResource("javafx/src/main/resources/reactiongame/highscore.list")
          )));

      for (String key :
          properties.stringPropertyNames()) {
        highscoreList.put(key, String.valueOf(properties.get(key)));
      }

    } catch (IOException ignore) {

    }

    singletonAttributeStore.setAttribute(RGC_HIGHSCORE_LIST, highscoreList);


    try {
      screenController.switchTo("RgcMenu");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}

