package de.hhn.it.devtools.javafx.text_based_labyrinth_FX;

import de.hhn.it.devtools.apis.textbasedlabyrinth.Item;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RoomInventoryScreen extends AnchorPane implements Initializable {


    public static final String SCREEN_NAME = "RoomInventoryScreen";

    private GameViewModel viewModel;
    private ArrayList<Item> currentRoomItems;

    @FXML
    Button exitGameButton;
    @FXML
    Button returnButton;
    @FXML
    Label playerName;
    @FXML
    Label score;
    @FXML
    Button inspectItem;
    @FXML
    Button pickUpItem;
    @FXML
    TextField itemInspectTextField;
    @FXML
    ChoiceBox<Item> itemChoiceBox;



    public RoomInventoryScreen(GameScreenController screenController) {

    }



    public void update() {

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void exitGame() {

    }

    public void toMainScreen() {

    }


    public void updateInspectField(String text) {
        itemInspectTextField.clear();
        itemInspectTextField.setText(text);
    }




}
