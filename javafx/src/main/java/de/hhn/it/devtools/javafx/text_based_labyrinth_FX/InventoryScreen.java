package de.hhn.it.devtools.javafx.text_based_labyrinth_FX;

import de.hhn.it.devtools.apis.textbasedlabyrinth.CurrentScreenRequesting;
import de.hhn.it.devtools.apis.textbasedlabyrinth.Item;
import de.hhn.it.devtools.apis.textbasedlabyrinth.NoSuchItemFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class InventoryScreen extends AnchorPane implements Initializable {



    public static final String SCREEN_NAME = "InventoryScreen";

    private GameViewModel viewModel;
    private GameScreenController screenController;


    private ObservableList<Item> itemObservableList;

    @FXML
    TextField itemInspectTextField;
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
    Button dropItem;
    @FXML
    ChoiceBox<Item> itemChoiceBox;


    public InventoryScreen(GameScreenController screenController) {
        this.screenController = screenController;
    }




    public void update() {
        itemInspectTextField.clear();
        playerName.setText(viewModel.getGame().getPlayerName());
        itemObservableList = FXCollections.observableList(viewModel.getGame().getCurrentRoom().getItemList());
        itemChoiceBox.setItems(itemObservableList);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        score.textProperty().bind(viewModel.getScore().asString());
    }


    public void updateInspectField(String text) {
        itemInspectTextField.clear();
        itemInspectTextField.setText(text);
    }

    public void setViewModel(GameViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @FXML
    public void inspectItemAction(ActionEvent event) {
        event.consume();
        if (itemChoiceBox.getValue() == null) {
            updateInspectField("Please select an item.");
        } else {
            viewModel.getGame().inspectItem(itemChoiceBox.getValue(), CurrentScreenRequesting.PLAYERINVENTORY);
        }
    }

    @FXML
    public void dropItemAction(ActionEvent event) {
        event.consume();
        if (itemChoiceBox.getValue() == null) {
            updateInspectField("Please select an item.");
        } else {
            try {
                viewModel.getGame().dropItem(itemChoiceBox.getValue().getItemId());
            } catch (NoSuchItemFoundException e) {
                updateInspectField(e.getMessage());
            }
        }
    }



    @FXML
    public void exitGame(ActionEvent event) throws UnknownTransitionException {
        event.consume();
        screenController.getMenuScreen().update();
        try {
            screenController.changeScreen(InteractScreen.SCREEN_NAME, MenuScreen.SCREEN_NAME);
        } catch (UnknownTransitionException e) {
            throw new UnknownTransitionException(e.getMessage(), e.getFrom(), e.getTo());
        }
    }

    @FXML
    public void toMainScreen(ActionEvent event) throws UnknownTransitionException {
        event.consume();
        try {
            screenController.changeScreen(InteractScreen.SCREEN_NAME, GameMainScreen.SCREEN_NAME);
        } catch (UnknownTransitionException e) {
            throw new UnknownTransitionException(e.getMessage(), e.getFrom(), e.getTo());
        }
    }
}
