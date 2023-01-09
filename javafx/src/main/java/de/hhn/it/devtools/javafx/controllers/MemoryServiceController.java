package de.hhn.it.devtools.javafx.controllers;

import de.hhn.it.devtools.javafx.controllers.memory.GameScreen;
import de.hhn.it.devtools.javafx.controllers.memory.MemoryScreenController;
import de.hhn.it.devtools.javafx.controllers.memory.StartScreen;
import de.hhn.it.devtools.javafx.controllers.template.SingletonAttributeStore;
import de.hhn.it.devtools.javafx.controllers.template.UnknownTransitionException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class MemoryServiceController extends Controller implements Initializable {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(MemoryServiceController.class);
  public static final String SCREEN_CONTROLLER = "screen.controller";

  @FXML
  AnchorPane templateAnchorPane;
  MemoryScreenController screenController;

  public MemoryServiceController() {
    logger.debug("MemoryService Controller created.");
  }



  @Override
  public void initialize(final URL location, final ResourceBundle resources) {
    // initialize screenController here because now we have the anchorPane.
    screenController = new MemoryScreenController(templateAnchorPane);
    SingletonAttributeStore singletonAttributeStore = SingletonAttributeStore.getReference();
    singletonAttributeStore.setAttribute(SCREEN_CONTROLLER, screenController);
  }

  @FXML
  void onFinishButtonClicked(ActionEvent event) {

  }

  @FXML
  void onOptionButtonClicked(ActionEvent event) {

  }

  @FXML
  void onStartButtonClicked(ActionEvent event) {
    screenController.switchTo(null, GameScreen.SCREEN);
  }


}
