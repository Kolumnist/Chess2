package de.hhn.it.devtools.javafx.controllers.memory;

import de.hhn.it.devtools.javafx.controllers.template.SingletonAttributeStore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartScreen implements Initializable {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(StartScreen.class);

  private MemoryScreenController screenController;
  public static final String SCREEN = "start.screen";

  public StartScreen(final MemoryScreenController screenController) {
    SingletonAttributeStore singletonAttributeStore = SingletonAttributeStore.getReference();
    this.screenController = screenController;
  }

  @Override
  public void initialize(final URL location, final ResourceBundle resources) {
    logger.info("Start Screen initialized.");
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
}
