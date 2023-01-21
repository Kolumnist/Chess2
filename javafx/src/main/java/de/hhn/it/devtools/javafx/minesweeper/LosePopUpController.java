package de.hhn.it.devtools.javafx.minesweeper;

import de.hhn.it.devtools.apis.minesweeper.MinesweeperCoordinates;
import de.hhn.it.devtools.javafx.battleship.Game;
import de.hhn.it.devtools.javafx.minesweeper.helpers.GameHelper;
import de.hhn.it.devtools.javafx.minesweeper.helpers.ViewSwitchHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LosePopUpController implements Initializable {
    @FXML
    private Label losePopUpLabel;
    @FXML
    private Button restart;
    @FXML
    private Button menu;
    @FXML
    private Label lbTimeSpent;

    public LosePopUpController() {

    }

    public void handleButtonRestart(ActionEvent actionEvent) throws IOException {
        GameHelper.setupTimer();
        GameHelper.startTimer();
        ViewSwitchHelper.switchTo("minesweeper/MinesweeperGame");
    }

    public void handleButtonMenu(ActionEvent actionEvent) throws IOException {
        ViewSwitchHelper.switchTo("minesweeper/MinesweeperPopUpStart");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GameHelper.getTimer().cancel();
        lbTimeSpent.setText(GameHelper.getTimeSpent() + "");
    }
}
