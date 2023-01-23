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
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class InventoryScreen extends AnchorPane implements Initializable {



    public static final String SCREEN_NAME = "InventoryScreen";

    private GameViewModel viewModel;
    private GameScreenController screenController;

    private HashMap<String, Item> itemHashMap;
    private List<String> itemNames;
    private ObservableList<String> itemObservableList;


    @FXML
    TextArea itemInspectTextField;
    @FXML
    TextArea itemNameField;
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
    ChoiceBox<String> itemChoiceBox;


    public InventoryScreen(GameScreenController screenController) {
        this.screenController = screenController;
        this.itemHashMap = new HashMap<>();
        this.itemNames = new ArrayList<>();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/labyrinth/InventoryScreen.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public void update() {
        itemInspectTextField.clear();
        itemNameField.clear();
        playerName.setText(viewModel.getGame().getPlayerName());
        itemHashMap = new HashMap<>();
        itemNames = new ArrayList<>();
        itemHashMap = viewModel.getGame().getPlayer().getInventoryWithNames();
        itemHashMap.keySet().stream().iterator().forEachRemaining(item -> itemNames.add(item));
        itemObservableList = FXCollections.observableList(itemNames);
        itemChoiceBox.setItems(itemObservableList);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void updateInspectField(String text) {
        itemInspectTextField.clear();
        itemInspectTextField.setText(text);
        itemInspectTextField.setWrapText(true);
    }

    public void updateItemNameField(String text) {
        itemNameField.clear();
        itemNameField.setText(text);
    }

    public void setViewModel(GameViewModel viewModel) {
        this.viewModel = viewModel;
        score.textProperty().bind(viewModel.getScore().asString());
    }

    @FXML
    public void inspectItemAction(ActionEvent event) {
        event.consume();
        if (itemChoiceBox.getValue() == null) {
            updateInspectField("Please select an item.");
        } else {
            viewModel.getGame().inspectItem(itemHashMap.get(itemChoiceBox.getValue()),
                    CurrentScreenRequesting.PLAYERINVENTORY);
        }
    }

    @FXML
    public void dropItemAction(ActionEvent event) {
        event.consume();
        if (itemChoiceBox.getValue() == null) {
            updateInspectField("Please select an item.");
        } else {
            try {
                viewModel.getGame().dropItem(itemHashMap.get(itemChoiceBox.getValue()).getItemId());
                itemHashMap.remove(itemChoiceBox.getValue());
                itemNames.remove(itemChoiceBox.getValue());
            } catch (NoSuchItemFoundException e) {
                updateInspectField(e.getMessage());
            }
        }
    }



    @FXML
    public void exitGame(ActionEvent event) throws UnknownTransitionException {
        event.consume();
        screenController.getMenuScreen().update();
        viewModel.getGame().end();
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
