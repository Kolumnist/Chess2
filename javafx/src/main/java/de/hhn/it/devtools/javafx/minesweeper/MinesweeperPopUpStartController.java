package de.hhn.it.devtools.javafx.minesweeper;

import de.hhn.it.devtools.javafx.controllers.MinesweeperServiceController;
import de.hhn.it.devtools.javafx.minesweeper.helpers.GameHelper;
import de.hhn.it.devtools.javafx.minesweeper.helpers.ViewSwitchHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class MinesweeperPopUpStartController implements Initializable {
    @FXML
    BorderPane borderPane;
    @FXML
    private Label popUpLabel;
    @FXML
    private CheckBox checkBox;
    @FXML
    private TextField tbMines;
    @FXML
    private TextField tbSize;
    @FXML
    private TextField tbTime;

    public MinesweeperPopUpStartController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTextBoxValues();
        //Setup timer

        //Setup timer
        GameHelper.setupTimer();
    }
    public void handleButtonHard(ActionEvent actionEvent) {
        GameHelper.setLevelValues(20, 180, 60);
        setTextBoxValues();
    }

    public void handleButtonMedium(ActionEvent actionEvent) {
        GameHelper.setLevelValues(15, 260, 20);
        setTextBoxValues();
    }

    public void handleButtonEasy(ActionEvent actionEvent) {
        GameHelper.setLevelValues(5, 300, 5);
        setTextBoxValues();
    }

    private void setTextBoxValues() {
        if(GameHelper.getTime() == -99){
            GameHelper.setTime(300);
            checkBox.setSelected(true);
        }
        tbSize.setText(String.valueOf(GameHelper.getSize()));
        tbTime.setText(String.valueOf(GameHelper.getTime()));
        tbMines.setText(String.valueOf(GameHelper.getMines()));
    }

    public void handleButtonStart(ActionEvent actionEvent) throws IOException {
        int size = 5;
        int time = 300;
        int bombCount = 5;
        try{
            size = Integer.parseInt(tbSize.getText());
            time = Integer.parseInt(tbTime.getText());
            bombCount = Integer.parseInt(tbMines.getText());
        }catch (Exception e){
            GameHelper.setLevelValues(5, 300, 5);
            setTextBoxValues();
            return;
        }
        if(size <= 0 || time <= 0 || bombCount <= 0 || bombCount >= (size*size)){
            tbSize.setText(5 + "");
            tbTime.setText(300 + "");
            tbMines.setText(5 + "");
            return;
        }
        if(checkBox.isSelected()){
            time = -99;
        }
        GameHelper.setupTimer();
        GameHelper.startTimer();

        GameHelper.setLevelValues(size, time, bombCount);

        ViewSwitchHelper.switchTo("minesweeper/MinesweeperGame");
    }
}

