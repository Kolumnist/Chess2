package de.hhn.it.devtools.javafx.controllers.reactiongame;

import de.hhn.it.devtools.javafx.controllers.Controller;
import de.hhn.it.devtools.javafx.controllers.ReactionGameController;
import de.hhn.it.devtools.javafx.controllers.template.SingletonAttributeStore;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;

  public class RgcSoundMenuController extends Controller implements Initializable {

    private RgcScreenController screenController;

    @FXML // fx:id="MasterSlider"
    private Slider MasterSlider; // Value injected by FXMLLoader

    @FXML // fx:id="SfxSlider"
    private Slider SfxSlider; // Value injected by FXMLLoader

    @FXML // fx:id="MusicSlider"
    private Slider MusicSlider; // Value injected by FXMLLoader

    @FXML // fx:id="MuteCheckbox"
    private CheckBox MuteCheckbox; // Value injected by FXMLLoader

    @FXML // fx:id="MenuButton"
    private Button MenuButton; // Value injected by FXMLLoader

    @FXML // fx:id="SaveButton"
    private Button SaveButton; // Value injected by FXMLLoader

    @FXML
    void onMenuButton(ActionEvent actionEvent) throws IOException {
      screenController.switchTo("RgcMenu");
    }

    @FXML
    void onSaveButton(ActionEvent actionEvent) throws IOException {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      SingletonAttributeStore singletonAttributeStore = SingletonAttributeStore.getReference();
      screenController =
          (RgcScreenController) singletonAttributeStore.getAttribute(ReactionGameController.RGC_SCREEN_CONTROLLER);
    }
  }

