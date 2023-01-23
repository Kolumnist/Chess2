package de.hhn.it.devtools.javafx.controllers;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.components.wordle.provider.WordleGameLogic;
import de.hhn.it.devtools.javafx.controllers.wordleAdditional.SimpleWordlePanelListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import de.hhn.it.devtools.apis.wordle.WordleService;
import de.hhn.it.devtools.apis.wordle.IllegalGuessException;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
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
  private Button button;

  @FXML
  private Button playAnotherRound;

  private Integer rowCount;

  private Integer colCount = 0;

  private String userInput;

  private final WordleService backend = new WordleGameLogic();

  private boolean isGameRunning = true;


  @FXML
  void enterButtonClicked(ActionEvent event) throws IllegalGuessException, IOException {

    if(checkIfGuessWasValid()) {
      isGameRunning = !backend.receiveAndComputeGuess(textField.getText());
      fillCurrentRowWithUserInput();
      textField.clear();
    }
    if (!isGameRunning){
      textField.setEditable(false);
      button.setDisable(true);
      System.out.println("eZ win");
      playAnotherRound.setVisible(true);
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Victory");
      alert.setHeaderText("Congratulations! You won!");
      alert.show();


    }
    if (rowCount != null && isGameRunning && rowCount > 5){
      textField.setEditable(false);
      button.setDisable(true);
      System.out.println("-35 LP");
      playAnotherRound.setVisible(true);
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Defeat");
      alert.setHeaderText("Oh no! You lost!");
      alert.setContentText("Better luck next time!");
      alert.show();
    }
  }

  @FXML
  void restartGame(ActionEvent event) {
    backend.startAnotherGame();
    isGameRunning = true;
    for(Node node: rowGridPane.getChildren()) {
      Label currentLabel = (Label) node;
      currentLabel.setBackground(new Background(new BackgroundFill(Color.rgb(18,18,19), CornerRadii.EMPTY, Insets.EMPTY)));
      currentLabel.setText(" ");
    }
    textField.setEditable(true);
    button.setDisable(false);
    rowCount = null;
    try {
      connectListenersToLabels();
    } catch (IllegalParameterException e) {
      throw new RuntimeException(e);
    }
    playAnotherRound.setVisible(false);
  }

  private boolean checkIfGuessWasValid() {
    userInput = textField.getText();

    char[] inputArray = userInput.toCharArray();

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

    for(int i = 0; i < 5; i++) {
      if (!Character.isLetter(inputArray[i])) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error!");
        alert.setContentText("Only letters are allowed!");
        alert.show();
        return false;
      }
    }
    return true;
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
    playAnotherRound.setVisible(false);
    backend.startGame();
    try {
      connectListenersToLabels();
    } catch (IllegalParameterException e) {
      throw new RuntimeException(e);
    }
  }
}

