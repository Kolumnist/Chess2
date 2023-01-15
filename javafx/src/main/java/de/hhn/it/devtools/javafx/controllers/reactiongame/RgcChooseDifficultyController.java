package de.hhn.it.devtools.javafx.controllers.reactiongame;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.reactiongame.Difficulty;
import de.hhn.it.devtools.components.reactiongame.provider.RgcService;
import de.hhn.it.devtools.javafx.Main;
import de.hhn.it.devtools.javafx.controllers.ReactionGameController;
import de.hhn.it.devtools.javafx.controllers.template.SingletonAttributeStore;
import de.hhn.it.devtools.javafx.reactiongame.RgcListener;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class RgcChooseDifficultyController implements Initializable {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(RgcChooseDifficultyController.class);

  private static SingletonAttributeStore singletonAttributeStore = SingletonAttributeStore.getReference();

  public static String DIFFICULTY = "rgc.difficulty";
  private RgcScreenController screenController;

  @FXML // fx:id="easyBtn"
  private Button easyBtn; // Value injected by FXMLLoader

  @FXML // fx:id="hardBtn"
  private Button hardBtn; // Value injected by FXMLLoader

  @FXML // fx:id="mediumBtn"
  private Button mediumBtn; // Value injected by FXMLLoader

  @FXML // fx:id="menuBtn"
  private Button menuBtn; // Value injected by FXMLLoader

  @FXML
  void onEasyBtn(ActionEvent event) throws IOException, IllegalParameterException {
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
    logger.info("Start run with difficulty easy.");
    singletonAttributeStore.setAttribute(DIFFICULTY, Difficulty.MEDIUM);
    screenController.switchTo("RgcGame");
  }

  @FXML
  void onMenuBtn(ActionEvent event) throws IOException {
    screenController.switchTo("RgcMenu");
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    SingletonAttributeStore singletonAttributeStore = SingletonAttributeStore.getReference();
    screenController =
        (RgcScreenController) singletonAttributeStore.getAttribute(ReactionGameController.RGC_SCREEN_CONTROLLER);
  }
}
