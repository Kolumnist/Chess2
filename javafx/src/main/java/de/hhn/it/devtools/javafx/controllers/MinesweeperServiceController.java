package de.hhn.it.devtools.javafx.controllers;

import de.hhn.it.devtools.javafx.minesweeper.helpers.GameHelper;
import de.hhn.it.devtools.javafx.minesweeper.helpers.ViewSwitchHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Menu;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MinesweeperServiceController extends Controller implements Initializable {

    @FXML
    private Scene scene;
    @FXML
    private VBox main;
    @FXML
    private int size;
    @FXML
    private Button[][] buttons;
    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Menu timer;

    private int time;

    public MinesweeperServiceController() {
        ViewSwitchHelper.setScene(scene);
    }

    @FXML
    void colorPicked(ActionEvent event) {
        Color color = colorPicker.getValue();
        String myColor = String.format( "#%02X%02X%02X",
                (int)( color.getRed() * 255 ),
                (int)( color.getGreen() * 255 ),
                (int)( color.getBlue() * 255 ) );

        System.out.println("Test");
        for (Button[] cols : GameHelper.getMatrix()) {
            for (Button bt:cols) {
                bt.setStyle(String.format("-fx-background-color: %s",myColor));
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BorderPane menuPane;
        try {
            menuPane = FXMLLoader.load(getClass().getResource("/fxml/minesweeper/MinesweeperPopUpStart.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mainBorderPane.setLeft(menuPane);

        //Setup timer
        GameHelper.setMenu(timer);
        GameHelper.setupTimer();

        ViewSwitchHelper.setMainBorderPane(mainBorderPane);
    }

    public void loader(String name){
        try {
            mainBorderPane = FXMLLoader.load(getClass().getResource("/fxml/minesweeper/" + name + ".fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mainBorderPane.setLeft(main);
    }

    @FXML
    void handleMenuBarMenu(ActionEvent event) {
        GameHelper.getTimer().cancel();
        timer.setText("Timer");
        try {
            ViewSwitchHelper.switchTo("minesweeper/MinesweeperPopUpStart");
        } catch (IOException e) {
            System.out.println("!");
        }
    }

    @FXML
    void handleMenuBarRestart(ActionEvent event) {
        GameHelper.setupTimer();
        GameHelper.startTimer();
        try {
            ViewSwitchHelper.switchTo("minesweeper/MinesweeperGame");
        } catch (IOException e) {
            System.out.println("!");
        }
    }

}


