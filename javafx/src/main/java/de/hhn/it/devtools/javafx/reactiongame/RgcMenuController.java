/**
 * Sample Skeleton for 'RgcMenu.fxml' Controller Class
 */

package de.hhn.it.devtools.javafx.reactiongame;

import de.hhn.it.devtools.javafx.controllers.Controller;
import de.hhn.it.devtools.javafx.controllers.ReactionGameController;
import de.hhn.it.devtools.javafx.controllers.TemplateController;
import de.hhn.it.devtools.javafx.controllers.reactiongame.RgcScreenController;
import de.hhn.it.devtools.javafx.controllers.template.ScreenController;
import de.hhn.it.devtools.javafx.controllers.template.SingletonAttributeStore;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class RgcMenuController extends Controller implements Initializable {

  @FXML // ResourceBundle that was given to the FXMLLoader
  private ResourceBundle resources;

  @FXML // URL location of the FXML file that was given to the FXMLLoader
  private URL location;

  @FXML // fx:id="menuPane"
  private Pane menuPane; // Value injected by FXMLLoader

  @FXML // fx:id="endGameBtn"
  private Button endGameBtn; // Value injected by FXMLLoader

  @FXML // fx:id="highscoreBtn"
  private Button highscoreBtn; // Value injected by FXMLLoader

  @FXML // fx:id="startGameBtn"
  private Button startGameBtn; // Value injected by FXMLLoader

  private RgcScreenController screenController;

  @FXML
  void onEndgameBtn(ActionEvent event) {
    System.exit(0);
  }

  @FXML
  void onHighscoreBtn(ActionEvent event) {

  }

  @FXML
  void onStartGameBtn(ActionEvent event) {
    try {
      screenController.switchTo("RgcChooseDifficulty");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


  @Override
  public void initialize(URL location, ResourceBundle resources) {
    SingletonAttributeStore singletonAttributeStore = SingletonAttributeStore.getReference();
    screenController =
        (RgcScreenController) singletonAttributeStore.getAttribute(ReactionGameController.SCREEN_CONTROLLER);
  }
}
