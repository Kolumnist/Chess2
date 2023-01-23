package de.hhn.it.devtools.javafx.minesweeper;

import de.hhn.it.devtools.javafx.minesweeper.helpers.GameHelper;
import de.hhn.it.devtools.javafx.minesweeper.helpers.ViewSwitchHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WinPopUpController implements Initializable {
    @FXML
    private Label winPopUpLabel;
    @FXML
    private Button restart;
    @FXML
    private Button menu;
    @FXML
    private Label lbTimeSpent;

    public WinPopUpController() {
       /* FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/minesweeper"
                + "/WinPopUp.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }

    public void handleButtonRestartWin(ActionEvent actionEvent) throws IOException {
        GameHelper.setupTimer();
        GameHelper.startTimer();
        ViewSwitchHelper.switchTo("minesweeper/MinesweeperGame");
    }

    public void handleButtonMenuWin(ActionEvent actionEvent) throws IOException {
        ViewSwitchHelper.switchTo("minesweeper/MinesweeperPopUpStart");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GameHelper.getTimer().cancel();
        lbTimeSpent.setText(GameHelper.getTimeSpent() + "");
    }
}
