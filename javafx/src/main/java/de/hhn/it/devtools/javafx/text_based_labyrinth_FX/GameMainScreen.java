package de.hhn.it.devtools.javafx.text_based_labyrinth_FX;

import de.hhn.it.devtools.apis.textbasedlabyrinth.Direction;
import de.hhn.it.devtools.apis.textbasedlabyrinth.Door;
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
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameMainScreen extends AnchorPane implements Initializable {


    public static final String SCREEN_NAME = "GameMainScreen";

    private GameViewModel viewModel;
    private GameScreenController screenController;
    private ObservableList<Direction> directions;

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
    Button helpButton;
    @FXML
    Label playerName;
    @FXML
    Label score;
    @FXML
    ChoiceBox<Direction> directionChoiceBox;
    @FXML
    TextArea playerTextField;
    @FXML
    TextArea roomTextField;
    @FXML
    TextArea actionTextField;

    public GameMainScreen(GameScreenController screenController) {
        this.screenController = screenController;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/labyrinth/GameMainScreen.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void update() {
        actionTextField.clear();
        roomTextField.clear();
        playerTextField.clear();
        playerName.setText(viewModel.getGame().getPlayerName());
        directions = FXCollections.observableList(viewModel.getGame().getCurrentRoom().getDirections());
        directionChoiceBox.setItems(directions);
    }






    public void setViewModel(GameViewModel viewModel) {
        this.viewModel = viewModel;
        score.textProperty().bind(viewModel.getScore().asString());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }



    public void updatePlayerActionField(String text) {
        playerTextField.clear();
        playerTextField.setText(text);
        playerTextField.setWrapText(true);
    }

    public void updateRoomField(String text) {
        roomTextField.clear();
        roomTextField.setText(text);
        roomTextField.setWrapText(true);
    }


    public void updateActionField(String text) {
        actionTextField.clear();
        actionTextField.setText(text);
        actionTextField.setWrapText(true);
    }

    @FXML
    public void moveAction(ActionEvent event) {
        event.consume();
        if (directionChoiceBox.getValue() == null) {
            updateActionField("Please select a direction in which to move.");
        } else {
            viewModel.getGame().move(directionChoiceBox.getValue());
        }
    }

    @FXML
    public void inspectAction(ActionEvent event) {
        event.consume();
        if (directionChoiceBox.getValue() == null) {
            updateActionField("Please select a direction in which to inspect.");
        } else {
            viewModel.getGame().inspect(directionChoiceBox.getValue());
        }
    }


    @FXML
    public void searchAction(ActionEvent event) throws UnknownTransitionException {
        event.consume();
        screenController.getRoomInventoryScreen().update();
        try {
            screenController.changeScreen(GameMainScreen.SCREEN_NAME, RoomInventoryScreen.SCREEN_NAME);
        } catch (UnknownTransitionException e) {
            throw new UnknownTransitionException(e.getMessage(), e.getFrom(), e.getTo());
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
    public void interactionAction(ActionEvent event) throws UnknownTransitionException, RoomFailedException {
        event.consume();
        boolean transition = false;
        if (directionChoiceBox.getValue() == null) {
            updateActionField("Please select a direction in which to interact with something.");
        } else {
            try {
                Door targetDoor = viewModel.getGame().getCurrentRoom().getDoor(directionChoiceBox.getValue());
                if (targetDoor.checkIfLocked()) {
                    transition = true;
                }
            } catch (RoomFailedException e) {
                throw new RoomFailedException(e.getMessage());
            }

            if (transition) {
                screenController.getInteractScreen().setDirection(directionChoiceBox.getValue());
                screenController.getInteractScreen().update();
                try {
                    screenController.changeScreen(GameMainScreen.SCREEN_NAME, InteractScreen.SCREEN_NAME);
                } catch (UnknownTransitionException e) {
                    throw new UnknownTransitionException(e.getMessage(), e.getFrom(), e.getTo());
                }
            } else {
                updatePlayerActionField("There is nothing to interact with in that direction.");
            }
        }
    }

    @FXML
    public void openInventoryAction(ActionEvent event) throws UnknownTransitionException {
        event.consume();
        screenController.getInventoryScreen().update();
        try {
            screenController.changeScreen(GameMainScreen.SCREEN_NAME, InventoryScreen.SCREEN_NAME);
        } catch (UnknownTransitionException e) {
            throw new UnknownTransitionException(e.getMessage(), e.getFrom(), e.getTo());
        }
    }

    @FXML
    public void openHelp(ActionEvent event) throws UnknownTransitionException {
        event.consume();
        try {
            screenController.changeScreen(GameMainScreen.SCREEN_NAME, HelpScreen.SCREEN_NAME);
        } catch (UnknownTransitionException e) {
            throw new UnknownTransitionException(e.getMessage(), e.getFrom(), e.getTo());
        }
    }
}
