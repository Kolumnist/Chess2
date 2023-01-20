package de.hhn.it.devtools.javafx.text_based_labyrinth_FX;

import de.hhn.it.devtools.apis.textbasedlabyrinth.Direction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GameMainScreen extends AnchorPane implements Initializable {


    public static final String SCREEN_NAME = "GameMainScreen";

    private GameViewModel viewModel;

    @FXML
    Button openInventory;
    @FXML
    Button moveButton;
    @FXML
    Button inspectButton;
    @FXML
    Button searchButton;
    @FXML
    Button interactionButton;
    @FXML
    Button exitGameButton;
    @FXML
    Label playerName;
    @FXML
    Label score;
    @FXML
    ChoiceBox<Direction> directionChoiceBox;
    @FXML
    TextField playerTextField;
    @FXML
    TextField roomTextField;
    @FXML
    TextField actionTestField;

    public GameMainScreen(GameScreenController screenController) {

    }



    public void update() {

    }







    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }



    public void updatePlayerActionField(String text) {
        playerTextField.clear();
        playerTextField.setText(text);
    }

    public void updateRoomField(String text) {
        roomTextField.clear();
        roomTextField.setText(text);
    }

    public void updateActionField(String text) {
        actionTestField.clear();
        actionTestField.setText(text);
    }
}
