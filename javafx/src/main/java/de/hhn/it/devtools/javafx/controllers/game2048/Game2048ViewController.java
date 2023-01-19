package de.hhn.it.devtools.javafx.controllers.game2048;

import de.hhn.it.devtools.apis.game2048.State;
import de.hhn.it.devtools.components.game2048.provider.ImplementationGame2048Service;
import de.hhn.it.devtools.javafx.controllers.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Game2048ViewController extends Controller implements Initializable {
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="currentScore"
    private Label currentScore; // Value injected by FXMLLoader

    @FXML // fx:id="currentScoreNumber"
    private Label currentScoreNumber; // Value injected by FXMLLoader

    @FXML // fx:id="gameBlock1"
    private Label gameBlock1; // Value injected by FXMLLoader

    @FXML // fx:id="gameBlock10"
    private Label gameBlock10; // Value injected by FXMLLoader

    @FXML // fx:id="gameBlock11"
    private Label gameBlock11; // Value injected by FXMLLoader

    @FXML // fx:id="gameBlock12"
    private Label gameBlock12; // Value injected by FXMLLoader

    @FXML // fx:id="gameBlock13"
    private Label gameBlock13; // Value injected by FXMLLoader

    @FXML // fx:id="gameBlock14"
    private Label gameBlock14; // Value injected by FXMLLoader

    @FXML // fx:id="gameBlock15"
    private Label gameBlock15; // Value injected by FXMLLoader

    @FXML // fx:id="gameBlock16"
    private Label gameBlock16; // Value injected by FXMLLoader

    @FXML // fx:id="gameBlock2"
    private Label gameBlock2; // Value injected by FXMLLoader

    @FXML // fx:id="gameBlock3"
    private Label gameBlock3; // Value injected by FXMLLoader

    @FXML // fx:id="gameBlock4"
    private Label gameBlock4; // Value injected by FXMLLoader

    @FXML // fx:id="gameBlock5"
    private Label gameBlock5; // Value injected by FXMLLoader

    @FXML // fx:id="gameBlock6"
    private Label gameBlock6; // Value injected by FXMLLoader

    @FXML // fx:id="gameBlock7"
    private Label gameBlock7; // Value injected by FXMLLoader

    @FXML // fx:id="gameBlock8"
    private Label gameBlock8; // Value injected by FXMLLoader

    @FXML // fx:id="gameBlock9"
    private Label gameBlock9; // Value injected by FXMLLoader

    @FXML // fx:id="highScore"
    private Label highScore; // Value injected by FXMLLoader

    @FXML // fx:id="highScoreNumber"
    private Label highScoreNumber; // Value injected by FXMLLoader

    @FXML // fx:id="newGame"
    private Button newGame; // Value injected by FXMLLoader

    @FXML // fx:id="screenHeader"
    private Label screenHeader; // Value injected by FXMLLoader

    Label[] blocks;
    ImplementationGame2048Service game2048Service;

    private State currentState;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        game2048Service = new ImplementationGame2048Service();
        game2048Service.initialisation();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        game2048Service = new ImplementationGame2048Service();
        game2048Service.initialisation();
    }
}