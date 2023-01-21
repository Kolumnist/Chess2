package de.hhn.it.devtools.javafx.text_based_labyrinth_FX;

import de.hhn.it.devtools.apis.textbasedlabyrinth.CurrentScreenRequesting;
import de.hhn.it.devtools.apis.textbasedlabyrinth.Direction;
import de.hhn.it.devtools.apis.textbasedlabyrinth.Item;
import de.hhn.it.devtools.apis.textbasedlabyrinth.RoomFailedException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InteractScreen extends AnchorPane implements Initializable {


    public static final String SCREEN_NAME = "InteractScreen";


    private GameViewModel viewModel;
    private GameScreenController screenController;
    private ObservableList<Item> itemObservableList;
    private Direction direction;
    private boolean finished;



    @FXML
    TextField interactTextField;
    @FXML
    TextField itemNameField;
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
    Button useButton;
    @FXML
    ChoiceBox<Item> itemChoiceBox;



    public InteractScreen(GameScreenController screenController) {
        this.screenController = screenController;
        finished = false;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/labyrinth/InteractionScreen.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setViewModel(GameViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void update() {
        interactTextField.clear();
        itemNameField.clear();
        playerName.setText(viewModel.getGame().getPlayerName());
        itemObservableList = FXCollections.observableList(viewModel.getGame().getCurrentRoom().getItemList());
        itemChoiceBox.setItems(itemObservableList);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        score.textProperty().bind(viewModel.getScore().asString());
    }

    public void updateItemNameField(String text) {
        itemNameField.clear();
        itemNameField.setText(text);
    }


    public void updateInteractField(String text) {
        interactTextField.clear();
        interactTextField.setText(text);
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


    @FXML
    public void useAction(ActionEvent event) throws RoomFailedException {
        event.consume();
        if (finished) {
            updateInteractField("There is nothing more to do here.");
        } else {
            try {
                finished = viewModel.getGame().interaction(direction, itemChoiceBox.getValue());
            } catch (RoomFailedException e) {
                throw new RoomFailedException(e.getMessage());
            }
        }
    }

    @FXML
    public void inspectItemAction(ActionEvent event) {
        event.consume();
        if (itemChoiceBox.getValue() == null) {
            updateInteractField("Please select an item.");
        } else {
            viewModel.getGame().inspectItem(itemChoiceBox.getValue(), CurrentScreenRequesting.INTERACTION);
        }
    }
}
