package de.hhn.it.devtools.javafx.controllers;

/**
 * Sample Skeleton for 'RgcMenu.fxml' Controller Class
 */

import de.hhn.it.devtools.javafx.controllers.reactiongame.RgcScreenController;
import de.hhn.it.devtools.javafx.controllers.template.SingletonAttributeStore;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class ReactionGameController extends Controller implements Initializable {

  public static final String SCREEN_CONTROLLER = "rgc.screen.controller";
  @FXML // fx:id="anchorPane"
  private AnchorPane anchorPane; // Value injected by FXMLLoader

  private RgcScreenController screenController;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    screenController = new RgcScreenController(anchorPane);
    SingletonAttributeStore singletonAttributeStore = SingletonAttributeStore.getReference();
    singletonAttributeStore.setAttribute(SCREEN_CONTROLLER, screenController);

    try {
      screenController.switchTo("RgcMenu");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}

