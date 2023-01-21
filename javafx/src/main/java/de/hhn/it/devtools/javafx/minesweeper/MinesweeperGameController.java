package de.hhn.it.devtools.javafx.minesweeper;

import de.hhn.it.devtools.apis.minesweeper.MinesweeperCoordinates;
import de.hhn.it.devtools.apis.minesweeper.MinesweeperService;
import de.hhn.it.devtools.apis.minesweeper.Status;
import de.hhn.it.devtools.components.minesweeper.provider.LjMinesweeperService;
import de.hhn.it.devtools.components.ttrpgsheets.Stat;
import de.hhn.it.devtools.javafx.battleship.Game;
import de.hhn.it.devtools.javafx.controllers.Controller;
import de.hhn.it.devtools.javafx.minesweeper.helpers.GameHelper;
import de.hhn.it.devtools.javafx.minesweeper.helpers.ViewSwitchHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class MinesweeperGameController extends Controller implements Initializable {
    @FXML
    BorderPane borderPane;

    @FXML
    Button button;
    @FXML
    GridPane grid;
    @FXML
    private Scene scene;

    int size;
    int time;
    int bombCount;
    int clickedFields;
    Button[][] buttons;
    boolean[][] revealed;
    MinesweeperService service;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        size = GameHelper.getSize();
        time = GameHelper.getTime();
        bombCount = GameHelper.getMines();
        clickedFields = 0;

        revealed = new boolean[size][size];

        buttons = new Button[size][size];
        service = new LjMinesweeperService(size,time,bombCount);

        grid = new GridPane();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Button button = new Button();
                button.setMinHeight(3*131/size);
                button.setMinWidth(3*132/size);
                button.setMaxHeight(3*131/size);
                button.setMaxWidth(3*132/size);
                button.setPrefHeight(3*131/size);
                button.setPrefWidth(3*132/size);
                int x = col;
                int y = row;
                button.setOnMouseClicked(e -> {
                    if (e.getButton() == MouseButton.PRIMARY) {
                        clickField(new MinesweeperCoordinates(x, y));
                    } else if (e.getButton() == MouseButton.SECONDARY) {
                        markField(new MinesweeperCoordinates(x, y));
                    }
                });
                buttons[col][row] = button;
                grid.add(button, col, row);
            }
        }
        borderPane.setCenter(grid);
        GameHelper.setMatrix(buttons);
    }

    private void clickField(MinesweeperCoordinates coords) {
        //Prevent clicking the same Filed multiple times
        if(revealed[coords.x()][coords.y()]){
            return;
        }
        Status status = service.clickField(coords);
        //Handle Flag
        if (status == Status.FLAG) {
            return;
        }
        //Handle Visuals
        revealed[coords.x()][coords.y()] = true;
        changeButtonVisuals(coords, status);

        //Handle Bomb
        if (status == Status.BOMB) {
            try {
                ViewSwitchHelper.switchTo("minesweeper/LosePopUp");
                return;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        //Handle Nothing
        if (status == Status.NOTHING) {
            revealEmptyNeighbourFields(coords);
        }
        checkWin();
    }

    private void revealEmptyNeighbourFields(MinesweeperCoordinates coords){
        for (int y = -1; y <= 1; y++) {
            for (int x = -1; x <= 1; x++) {
                if(x != 0 || y != 0){
                    try {
                        clickField(new MinesweeperCoordinates(coords.x() + x, coords.y() + y));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.print("-");
                    }
                }
            }
        }
    }

    private void checkWin() {
        if(++clickedFields >= (size*size)-bombCount){
            try {
                GameHelper.stopTimer();
                ViewSwitchHelper.switchTo("minesweeper/WinPopUp");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void markField(MinesweeperCoordinates coords) {
        Status status = service.markField(coords);
        if(revealed[coords.x()][coords.y()] || status == Status.FLAG){
            changeButtonVisuals(coords, status);
        }else{
            buttons[coords.x()][coords.y()].setGraphic(null);
        }
    }

    private void changeButtonVisuals(MinesweeperCoordinates coords, Status status){
        Image img = new Image("/minesweeper/images/" + status + ".jpg");
        ImageView view = new ImageView(img);
        view.fitWidthProperty().bind(buttons[coords.x()][coords.y()].widthProperty());
        view.fitHeightProperty().bind(buttons[coords.x()][coords.y()].heightProperty());
        buttons[coords.x()][coords.y()].setGraphic(view);
    }

    private void changeButtonColors(){

    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setBombCount(int bombCount) {
        this.bombCount = bombCount;
    }
}
