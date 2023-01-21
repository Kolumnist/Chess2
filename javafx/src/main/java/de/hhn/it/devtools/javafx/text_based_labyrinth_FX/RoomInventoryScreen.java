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
    private GameScreenController screenController;
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
        this.screenController = screenController;
    }



    public void setViewModel(GameViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void update() {
        itemInspectTextField.clear();
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    public void exitGame() throws UnknownTransitionException {
        try {
            screenController.changeScreen(RoomInventoryScreen.SCREEN_NAME, MenuScreen.SCREEN_NAME);
        } catch (UnknownTransitionException e) {
            throw new UnknownTransitionException(e.getMessage(), e.getFrom(), e.getTo());
        }
    }

    @FXML
    public void toMainScreen() throws UnknownTransitionException {
        try {
            screenController.changeScreen(RoomInventoryScreen.SCREEN_NAME, GameMainScreen.SCREEN_NAME);
        } catch (UnknownTransitionException e) {
            throw new UnknownTransitionException(e.getMessage(), e.getFrom(), e.getTo());
        }
    }


    public void updateInspectField(String text) {
        itemInspectTextField.clear();
        itemInspectTextField.setText(text);
    }




}
