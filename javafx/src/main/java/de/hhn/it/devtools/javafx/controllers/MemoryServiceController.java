package de.hhn.it.devtools.javafx.controllers;

import de.hhn.it.devtools.javafx.controllers.template.PurProgrammingScreen;
import de.hhn.it.devtools.javafx.controllers.template.ScreenController;
import de.hhn.it.devtools.javafx.controllers.template.SingletonAttributeStore;
import de.hhn.it.devtools.javafx.controllers.template.UnknownTransitionException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class MemoryServiceController extends Controller implements Initializable {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(TemplateController.class);
  public static final String SCREEN_CONTROLLER = "screen.controller";

  @FXML
  AnchorPane templateAnchorPane;
  ScreenController screenController;

  public MemoryServiceController() {
    logger.debug("MemoryService Controller created.");
  }

  @FXML
  void onBeenden(ActionEvent event) {

  }

  @FXML
  void onOptionen(ActionEvent event) {

  }

  @FXML
  void onStart(ActionEvent event) {

  }


  @Override
  public void initialize(final URL location, final ResourceBundle resources) {
    // initialize screenController here because now we have the anchorPane.
    screenController = new ScreenController(templateAnchorPane);
    SingletonAttributeStore singletonAttributeStore = SingletonAttributeStore.getReference();
    singletonAttributeStore.setAttribute(SCREEN_CONTROLLER, screenController);
    try {
      screenController.switchTo(null, PurProgrammingScreen.SCREEN);
    } catch (UnknownTransitionException e) {
      e.printStackTrace();
    }

  }

}
