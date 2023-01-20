package de.hhn.it.devtools.javafx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import de.hhn.it.devtools.apis.wordle.WordleService;
import de.hhn.it.devtools.apis.wordle.WordleGuessService;
import de.hhn.it.devtools.apis.wordle.IllegalGuessException;
import de.hhn.it.devtools.apis.wordle.WordlePanelService;
import de.hhn.it.devtools.apis.wordle.WordlePanelListener;
import de.hhn.it.devtools.javafx.controllers.WordleGameController;

import java.net.URL;
import java.util.ResourceBundle;

public class WordleGameController extends Controller implements Initializable {
    @FXML
    private Label fifthRowFirstLabel;

    @FXML
    private Label firstRowFourthLabel;

    @FXML
    private Label firstRowFifthLabel;

    @FXML
    private Label fifthRowFifthLabel;

    @FXML
    private Label fifthRowSecondLabel;

    @FXML
    private Label firstRowFirstLabel;

    @FXML
    private Label sixthRowFourthLabel;

    @FXML
    private Label thirdRowFirstLabel;

    @FXML
    private Label thirdRowSecondLabel;

    @FXML
    private Label secondRowSecondLabel;

    @FXML
    private Label thirdRowFifthLabel;

    @FXML
    private Label sixthRowSecondLabel;

    @FXML
    private Label fourthRowFirstLabel;

    @FXML
    private Label firstRowFSecondLabel;

    @FXML
    private Label fourthRowThirdLabel;

    @FXML
    private Label thirdRowFourthLabel;

    @FXML
    private Label fourthRowFourthLabel;

    @FXML
    private Label fifthRowThirdLabel;

    @FXML
    private Label fourthRowSecondLabel;

    @FXML
    private Label firstRowThirdLabel;

    @FXML
    private Label sixthRowThirdLabel;

    @FXML
    private Label secondRowFirstLabel;

    @FXML
    private Label secondRowFifthLabel;

    @FXML
    private Label fourthRowFifthLabel;

    @FXML
    private Label fifthRowFourthLabel;

    @FXML
    private Label secondRowFourthLabel;

    @FXML
    private Label sixthRowFirstLabel;

    @FXML
    private Label secondRowThirdLabel;

    @FXML
    private Label sixthRowFifthLabel;

    @FXML
    private Label thirdRowThirdLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
