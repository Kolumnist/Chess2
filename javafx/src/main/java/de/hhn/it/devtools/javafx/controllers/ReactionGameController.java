package de.hhn.it.devtools.javafx.controllers;

/**
 * Sample Skeleton for 'RgcMenu.fxml' Controller Class
 */

import de.hhn.it.devtools.components.reactiongame.provider.RgcService;
import de.hhn.it.devtools.javafx.controllers.reactiongame.RgcScreenController;
import de.hhn.it.devtools.javafx.controllers.template.SingletonAttributeStore;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ReactionGameController extends Controller implements Initializable {

  public static final String RGC_SCREEN_CONTROLLER = "rgc.screen.controller";
  public static final String RGC_SERVICE = "rgc.service";
  public static final String RGC_PLAYER_NAME = "rgc.player.name";
  public static final String RGC_ANCHOR_PANE = "rgc.anchor.pane";

  public static final String RGC_CURRENT_PANE = "ReactionGame";

  public static final String RGC_KEY_listener = "rgc.key.listener";

  @FXML // fx:id="anchorPane"
  private AnchorPane anchorPane; // Value injected by FXMLLoader

  private RgcScreenController screenController;



  @Override
  public void initialize(URL location, ResourceBundle resources) {
    screenController = new RgcScreenController(anchorPane);
    SingletonAttributeStore singletonAttributeStore = SingletonAttributeStore.getReference();
    singletonAttributeStore.setAttribute(RGC_SCREEN_CONTROLLER, screenController);

    singletonAttributeStore.setAttribute(RGC_SERVICE, new RgcService());

    singletonAttributeStore.setAttribute(RGC_PLAYER_NAME, "Player");

    singletonAttributeStore.setAttribute(RGC_ANCHOR_PANE, anchorPane);




    try {
      screenController.switchTo("RgcMenu");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}

