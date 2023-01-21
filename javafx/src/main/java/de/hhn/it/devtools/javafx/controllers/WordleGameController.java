package de.hhn.it.devtools.javafx.controllers;

import de.hhn.it.devtools.components.wordle.provider.WordleGameLogic;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
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

    private WordleService backend = new WordleGameLogic();

    @FXML
    void buttonClicked(ActionEvent event) {
        fillCurrentRowWithUserInput();

    }

    private void fillCurrentRowWithUserInput() {
        String userInput = textField.getText();

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
        }
    }

