package de.hhn.it.devtools.javafx.text_based_labyrinth_FX;

import de.hhn.it.devtools.apis.textbasedlabyrinth.CurrentScreenRequesting;
import de.hhn.it.devtools.apis.textbasedlabyrinth.Item;
import de.hhn.it.devtools.apis.textbasedlabyrinth.NoSuchItemFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RoomInventoryScreen extends AnchorPane implements Initializable {


    public static final String SCREEN_NAME = "RoomInventoryScreen";

    private GameViewModel viewModel;
    private GameScreenController screenController;
    private ArrayList<Item> currentRoomItems;
    private ObservableList<Item> itemObservableList;

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
    TextArea itemInspectTextField;
    @FXML
    TextArea itemNameField;
    @FXML
    ChoiceBox<Item> itemChoiceBox;



    public RoomInventoryScreen(GameScreenController screenController) {
        this.screenController = screenController;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/labyrinth/RoomInventoryScreen.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void setViewModel(GameViewModel viewModel) {
        this.viewModel = viewModel;
        score.textProperty().bind(viewModel.getScore().asString());
    }

    public void update() {
        itemInspectTextField.clear();
        itemNameField.clear();
        playerName.setText(viewModel.getGame().getPlayerName());
        itemObservableList = FXCollections.observableList(viewModel.getGame().getCurrentRoom().getItemList());
        itemChoiceBox.setItems(itemObservableList);
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    public void exitGame(ActionEvent event) throws UnknownTransitionException {
        event.consume();
        screenController.getMenuScreen().update();
        viewModel.getGame().end();
        try {
            screenController.changeScreen(RoomInventoryScreen.SCREEN_NAME, MenuScreen.SCREEN_NAME);
        } catch (UnknownTransitionException e) {
            throw new UnknownTransitionException(e.getMessage(), e.getFrom(), e.getTo());
        }
    }

    @FXML
    public void toMainScreen(ActionEvent event) throws UnknownTransitionException {
        event.consume();
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

    public void updateItemNameField(String text) {
        itemNameField.clear();
        itemNameField.setText(text);
    }

    @FXML
    public void takeItemAction(ActionEvent event) {
        event.consume();
        if (itemChoiceBox.getValue() == null) {
            updateInspectField("Please select an item.");
        } else {
            try {
                viewModel.getGame().pickUpItem(itemChoiceBox.getValue().getItemId());
            } catch (NoSuchItemFoundException e) {
                updateInspectField(e.getMessage());
            }
        }
    }

    @FXML
    public void inspectItemAction(ActionEvent event) {
        event.consume();
        if (itemChoiceBox.getValue() == null) {
            updateInspectField("Please select an item.");
        } else {
            viewModel.getGame().inspectItem(itemChoiceBox.getValue(), CurrentScreenRequesting.ROOMINVENTORY);
        }
    }




}
