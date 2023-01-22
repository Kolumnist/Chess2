package de.hhn.it.devtools.javafx.duckhunt;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * Controller for the Description (how to play) screen.
 */
public class DuckHuntDescriptionController implements Initializable {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(DuckHuntDescriptionController.class);

  public static final String SCREEN = "description.screen";
  private DuckHuntScreenController screenController;
  private DuckHuntSoundManager soundManager;

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private Button backButton;

  @FXML
  private AnchorPane templateAnchorPane;

  @FXML
  void backToMainMenu(MouseEvent event) {
    soundManager.playSound(DuckHuntSounds.BUTTONCLICK);
    screenController.switchTo(SCREEN, DuckHuntMenuController.SCREEN);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    DuckHuntAttributeStore duckHuntAttributeStore = DuckHuntAttributeStore.getReference();
    screenController =
        (DuckHuntScreenController) duckHuntAttributeStore
            .getAttribute(DuckHuntScreenController.SCREEN_CONTROLLER);
    logger.info("Description Screen initialized.");
    soundManager = (DuckHuntSoundManager) duckHuntAttributeStore
            .getAttribute("soundManager");
  }
}
