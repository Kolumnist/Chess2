package de.hhn.it.devtools.javafx.controllers;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.components.wordle.provider.WordleGameLogic;
import de.hhn.it.devtools.javafx.controllers.wordleAdditional.SimpleWordlePanelListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import de.hhn.it.devtools.apis.wordle.WordleService;
import de.hhn.it.devtools.apis.wordle.IllegalGuessException;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class WordleGameController extends Controller implements Initializable {


    @FXML
    private TextField textField;
    @FXML
    private GridPane rowGridPane;

    @FXML
    private VBox mainScreenVBox;

    @FXML
    private Button button;

    private Integer rowCount;

    private Integer colCount = 0;

    private String userInput;

    private final WordleService backend = new WordleGameLogic();

    private boolean isGameRunning = true;


    @FXML
    void buttonClicked(ActionEvent event) throws IllegalGuessException, IOException {

        if(checkIfGuessWasValid()) {
            isGameRunning = !backend.receiveAndComputeGuess(textField.getText());
            fillCurrentRowWithUserInput();
            textField.clear();
        }
        if (!isGameRunning){
            textField.setEditable(false);
            button.setDisable(true);
            System.out.println("eZ win");
            loadVictoryScreen();

        }
        if (isGameRunning && rowCount > 5){
            textField.setEditable(false);
            button.setDisable(true);
            System.out.println("-35 LP");
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

    @FXML
    private void loadVictoryScreen() throws IOException{
        VBox vbox = FXMLLoader.load(getClass().getResource("/fxml/wordle/victoryScreen.fxml"));
        mainScreenVBox.getChildren().setAll(vbox);
    }

    private void fillCurrentRowWithUserInput() {
        userInput = textField.getText();

        for(Node node: rowGridPane.getChildren()) {
            if(Objects.equals(GridPane.getRowIndex(node), rowCount)) {
                Label currentLabel = (Label) node;
                currentLabel.setText(userInput.substring(colCount, colCount + 1).toUpperCase());
                colCount++;
            }
        }
        if(rowCount == null) {
            rowCount = 0;
        }
        rowCount = rowCount +1;
        colCount = 0;
    }

    private void connectListenersToLabels() throws IllegalParameterException {
        int counter = 0;
        for (Node node: rowGridPane.getChildren()) {
            Label currentLabel = (Label) node;
            backend.addCallback(new SimpleWordlePanelListener(currentLabel), backend.getPanelById(counter));
            counter++;
        }
    }

        @Override
        public void initialize (URL url, ResourceBundle resourceBundle){
        backend.startGame();
            try {
                connectListenersToLabels();
            } catch (IllegalParameterException e) {
                throw new RuntimeException(e);
            }
        }
    }

