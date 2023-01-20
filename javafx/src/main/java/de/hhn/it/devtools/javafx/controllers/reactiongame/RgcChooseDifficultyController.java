package de.hhn.it.devtools.javafx.controllers.reactiongame;

import de.hhn.it.devtools.apis.reactiongame.Difficulty;
import de.hhn.it.devtools.javafx.controllers.ReactionGameController;
import de.hhn.it.devtools.javafx.controllers.template.SingletonAttributeStore;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class RgcChooseDifficultyController implements Initializable {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(RgcChooseDifficultyController.class);

  private static final SingletonAttributeStore singletonAttributeStore
      = SingletonAttributeStore.getReference();

  public static String DIFFICULTY = "rgc.difficulty";
  private RgcScreenController screenController;

  @FXML
  void onEasyBtn(ActionEvent event) throws IOException {
    logger.info("Start run with difficulty easy.");
    singletonAttributeStore.setAttribute(DIFFICULTY, Difficulty.EASY);
    screenController.switchTo("RgcGame");
  }

  @FXML
  void onHardBtn(ActionEvent event) throws IOException {
    logger.info("Start run with difficulty hard.");
    singletonAttributeStore.setAttribute(DIFFICULTY, Difficulty.HARD);
    screenController.switchTo("RgcGame");

  }

  @FXML
  void onmediumBtn(ActionEvent event) throws IOException {
    logger.info("Start run with difficulty medium.");
    singletonAttributeStore.setAttribute(DIFFICULTY, Difficulty.MEDIUM);
    screenController.switchTo("RgcGame");
  }

  @FXML
  void onMenuBtn(ActionEvent event) throws IOException {
    screenController.switchTo("RgcMenu");
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    screenController = (RgcScreenController)
        singletonAttributeStore.getAttribute(ReactionGameController.RGC_SCREEN_CONTROLLER);
  }
}
