package de.hhn.it.devtools.javafx.controllers;

import de.hhn.it.devtools.components.wordle.provider.WordleGameLogic;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import de.hhn.it.devtools.apis.wordle.WordleService;
import de.hhn.it.devtools.apis.wordle.WordleGuessService;
import de.hhn.it.devtools.apis.wordle.IllegalGuessException;
import de.hhn.it.devtools.apis.wordle.WordlePanelService;
import de.hhn.it.devtools.apis.wordle.WordlePanelListener;
import de.hhn.it.devtools.javafx.controllers.WordleGameController;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class WordleGameController extends Controller implements Initializable {


    @FXML
    private TextField textField;
    @FXML
    private GridPane rowGridPane;
     private Integer rowCount;
    private Integer colCount = 0;

    private String userInput;

    private WordleService backend = new WordleGameLogic();

    @FXML
    void buttonClicked(ActionEvent event) throws IllegalGuessException {

        if(checkIfGuessWasValid()) {
            backend.receiveAndComputeGuess(textField.getText());
            fillCurrentRowWithUserInput();
            textField.clear();
        }
    }

    private boolean checkIfGuessWasValid() {
        userInput = textField.getText();
        if(userInput.length() < 5) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error!");
            alert.setContentText("The guess was not long enough! Try again!");
            alert.show();
            return false;
        }
        if(userInput.length() > 5) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error!");
            alert.setContentText("The guess was too long! Try again!");
            alert.show();
            return false;
        }
        return true;
    }

    private void fillCurrentRowWithUserInput() {
        userInput = textField.getText();

        for(Node node: rowGridPane.getChildren()) {
            if(Objects.equals(GridPane.getRowIndex(node), rowCount)) {
                Label currentLabel = (Label) node;
                currentLabel.setText(userInput.substring(colCount, colCount + 1));
                colCount++;
            }
        }
        if(rowCount == null) {
            rowCount = 0;
        }
        rowCount = rowCount +1;
        colCount = 0;
    }

        @Override
        public void initialize (URL url, ResourceBundle resourceBundle){
        backend.startGame();
        }
    }

