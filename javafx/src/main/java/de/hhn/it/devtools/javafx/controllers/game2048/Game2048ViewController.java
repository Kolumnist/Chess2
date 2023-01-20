package de.hhn.it.devtools.javafx.controllers.game2048;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.game2048.Block;
import de.hhn.it.devtools.apis.game2048.Position;
import de.hhn.it.devtools.apis.game2048.State;
import de.hhn.it.devtools.components.game2048.provider.ImplementationGame2048Service;
import de.hhn.it.devtools.javafx.game2048.Game2048FileIO;
import de.hhn.it.devtools.javafx.game2048.ImplemtListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Game2048ViewController {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(Game2048ViewController.class);

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

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

    @FXML // fx:id="highScoreNumber"
    private Label highScoreNumber; // Value injected by FXMLLoader

    @FXML // fx:id="newGame"
    private Button newGame; // Value injected by FXMLLoader

    Label[] labelGameBoard;
    ImplementationGame2048Service service;
    ImplemtListener listener;

    private State currentState;

    public Game2048ViewController() {
        service = new ImplementationGame2048Service();
        labelGameBoard = new Label[16];
        listener = new ImplemtListener(this);
    }

    @FXML
    void startGameButtonClicked(ActionEvent event) {
        service.initialisation();
    }

    @FXML
    void initialize() {
        logger.info("initialize: no params");
        try {
            service.addCallback(listener);
        } catch (IllegalParameterException e) {
            e.printStackTrace();
        }
        service.initialisation();
    }

    private void updateGameBoard() {
        logger.info("updateGameBoard: no params");
        for (Block block : currentState.getBlocksOnGameboard()) {
            Label label = getLabel(block.getXYPosition());
            label.setText(String.valueOf(block.getValue()));
            label.setVisible(true);
        }
    }

    public void setState(State state) {
        logger.info("setState: state = {}", state);
        clearScreen();
        currentState = state;
        updateScreen();
    }

    private void clearScreen() {
        if (currentState != null) {
            for (Block block : currentState.getBlocksOnGameboard()) {
                getLabel(block.getXYPosition()).setText("");
            }
        }
    }

    private void updateScreen() {
        updateGameBoard();
        currentScoreNumber.setText(String.valueOf(currentState.getCurrentScore()));
        updateHighScore();
    }

    private void updateHighScore() {
        int oldHighscore = Game2048FileIO.loadHighscore();
        int currentScore = currentState.getCurrentScore();
        if (currentScore > oldHighscore) {
            Game2048FileIO.saveHighscore(currentScore);
            highScoreNumber.setText(String.valueOf(currentScore));
        } else {
            highScoreNumber.setText(String.valueOf(oldHighscore));
        }
    }

    /**
     * Calling Labels through a Map<Position, Label> was not possible.
     * Because of this I wrote this work around.
     *
     * @return Label of a Block
     */
    private Label getLabel(Position position) {
        switch (position.getXPosition()) {
            case 0 -> {
                switch (position.getYPosition()) {
                    case 0 -> {
                        return gameBlock13;
                    }
                    case 1 -> {
                        return gameBlock14;
                    }
                    case 2 -> {
                        return gameBlock15;
                    }
                    case 3 -> {
                        return gameBlock16;
                    }
                }
            }
            case 1 -> {
                switch (position.getYPosition()) {
                    case 0 -> {
                        return gameBlock9;
                    }
                    case 1 -> {
                        return gameBlock10;
                    }
                    case 2 -> {
                        return gameBlock11;
                    }
                    case 3 -> {
                        return gameBlock12;
                    }
                }
            }
            case 2 -> {
                switch (position.getYPosition()) {
                    case 0 -> {
                        return gameBlock5;
                    }
                    case 1 -> {
                        return gameBlock6;
                    }
                    case 2 -> {
                        return gameBlock7;
                    }
                    case 3 -> {
                        return gameBlock8;
                    }
                }
            }
            case 3 -> {
                switch (position.getYPosition()) {
                    case 0 -> {
                        return gameBlock1;
                    }
                    case 1 -> {
                        return gameBlock2;
                    }
                    case 2 -> {
                        return gameBlock3;
                    }
                    case 3 -> {
                        return gameBlock4;
                    }
                }
            }
        }
        return gameBlock7;
    }
}