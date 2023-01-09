package de.hhn.it.devtools.javafx.controllers.memory;

import de.hhn.it.devtools.javafx.controllers.template.ScreenController;
import de.hhn.it.devtools.javafx.controllers.template.SingletonAttributeStore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameScreen implements Initializable {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(GameScreen.class);

  private MemoryScreenController screenController;
  public static final String SCREEN = "game.screen";


  public GameScreen(final MemoryScreenController screenController) {
    SingletonAttributeStore singletonAttributeStore = SingletonAttributeStore.getReference();
    this.screenController = screenController;
  }

  @Override
  public void initialize(final URL location, final ResourceBundle resources) {
    logger.info("Game Screen initialized.");
  }
  @FXML
  private Button finishButton;

  @FXML
  private AnchorPane gameScreenAnchorPane;

  @FXML
  private Button optionsButton;

  @FXML
  private Label timeDisplayLabel;

  @FXML
  private Label timeTextLabel;

  @FXML
  void onFinishButtonClicked(ActionEvent event) {

  }

  @FXML
  void onOptionsButtonClicked(ActionEvent event) {

  }
}
