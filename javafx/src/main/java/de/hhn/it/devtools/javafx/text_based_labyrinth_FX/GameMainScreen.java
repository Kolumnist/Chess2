package de.hhn.it.devtools.javafx.text_based_labyrinth_FX;

import de.hhn.it.devtools.apis.textbasedlabyrinth.Direction;
import de.hhn.it.devtools.apis.textbasedlabyrinth.Door;
import de.hhn.it.devtools.apis.textbasedlabyrinth.RoomFailedException;
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

public class GameMainScreen extends AnchorPane implements Initializable {


    public static final String SCREEN_NAME = "GameMainScreen";

    private GameViewModel viewModel;
    private GameScreenController screenController;

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
        this.screenController = screenController;
    }



    public void update() {
        actionTestField.clear();
        roomTextField.clear();
        playerTextField.clear();
        playerName.setText(viewModel.getGame().getPlayerName());
    }






    public void setViewModel(GameViewModel viewModel) {
        this.viewModel = viewModel;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        score.textProperty().bind(viewModel.getScore().asString());
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
    public void searchAction(ActionEvent event) {
        event.consume();

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
    public void openInventoryAction(ActionEvent event) {

    }
}
