package de.hhn.it.devtools.javafx.battleship;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Game extends Stage{

    VBox root = new VBox();
    Scene GameScene = new Scene(root);

    GridPane playgroundUpper = new GridPane();
    GridPane playgroundLower = new GridPane();
    GridPane numbersBetween = new GridPane();

    MenuBar menuBar = new MenuBar();
    Menu menuGame= new Menu("Game");
    Menu menuSound= new Menu("Sound");
    Menu menuHelp= new Menu("Help");

    MenuItem gameSave = new MenuItem("Save");
    MenuItem gameLoad = new MenuItem("Load");
    MenuItem gameConcede = new MenuItem("Concede");
    MenuItem soundChangeVolume = new MenuItem("Change Volume");
    MenuItem helpShipsLeft = new MenuItem("Ships left");
    MenuItem helpRules = new MenuItem("Rules");


    public Game(int sizeGrid){

        root.getStylesheets().add("battleship/style.css");
        root.setId("gameVbox");

        menuBar.setStyle("-fx-use-system-menu-bar: true");

        menuGame.getItems().add(gameSave);
        menuGame.getItems().add(gameLoad);
        menuGame.getItems().add(gameConcede);
        menuSound.getItems().add(soundChangeVolume);
        menuHelp.getItems().add(helpShipsLeft);
        menuHelp.getItems().add(helpRules);

        menuBar.getMenus().add(menuGame);
        menuBar.getMenus().add(menuSound);
        menuBar.getMenus().add(menuHelp);
        menuBar.prefWidthProperty().bind(Start.mainStage.widthProperty());


        int sizeButtons = 50;

        Button[][] buttonsUpper = new Button[sizeGrid][sizeGrid];
        Button[][] buttonsLower = new Button[sizeGrid][sizeGrid];

        Character[] abc = {'A', 'B', 'C', 'D', 'E',
                           'F', 'G', 'H', 'I', 'J',
                           'K', 'L', 'M', 'N', 'O'};



        for ( int k = 0; k <sizeGrid; k++) {
            Label lblAbc = new Label(abc[k].toString());
            lblAbc.setStyle("-fx-text-fill: white; -fx-font-size: 20; -fx-padding: 0 15 0 0 ");

            playgroundUpper.add(lblAbc, 0 ,k);

            for ( int i = 0; i < sizeGrid; i++) {
                buttonsUpper[k][i] = new Button();
                buttonsUpper[k][i].setPrefWidth(sizeButtons);
                buttonsUpper[k][i].setPrefHeight(sizeButtons);
                buttonsUpper[k][i].setId("buttonsUpper");

                int k1=k;
                int i1= i;

                buttonsUpper[k][i].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {

                        buttonsUpper[k1][i1].setStyle("-fx-background-color: red");

                    }
                });

                playgroundUpper.add(buttonsUpper[k][i], i + 1, k);
            }
        }



        for(int i = 1; i<=sizeGrid ; i++){
            Label numbers = new Label(Integer.toString(i));
            numbers.setStyle("-fx-text-fill: white; -fx-font-size: 20; -fx-padding: 15 0 -50  15");
            numbers.setAlignment(Pos.CENTER);
            playgroundUpper.add(numbers,i,sizeGrid);
        }

        for (int k = 0; k <sizeGrid; k++) {
            Label lblAbc = new Label(abc[k].toString());
            lblAbc.setStyle("-fx-text-fill: white; -fx-font-size: 20; -fx-padding: 0 15 0 0 ");

            playgroundLower.add(lblAbc, 0 ,k);

            for (int i = 0; i < sizeGrid; i++) {
                buttonsLower[k][i] = new Button();
                buttonsLower[k][i].setPrefWidth(sizeButtons);
                buttonsLower[k][i].setPrefHeight(sizeButtons);
                buttonsLower[k][i].setId("buttonsLower");

                // needed but i don't fully understand why
                int k1=k;
                int i1= i;

                buttonsLower[k][i].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        buttonsLower[k1][i1].setStyle("-fx-background-color: red");
                    }
                });

                playgroundLower.add(buttonsLower[k][i], i + 1, k);
            }
        }

        helpRules.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Rules rules = new Rules();
            }
        });

        helpShipsLeft.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ShipsLeft shipsleft = new ShipsLeft();
            }
        });

        soundChangeVolume.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                VolumeChanger volumeChanger = new VolumeChanger();
            }
        });

        gameSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save Game");
                fileChooser.showSaveDialog(Start.mainStage);
            }
        });

        gameLoad.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Load Game");
                fileChooser.showOpenDialog(Start.mainStage);
            }
        });

        gameConcede.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Concede concede = new Concede();
            }
        });


        root.getChildren().add(0,playgroundUpper);
        root.getChildren().add(1,numbersBetween);
        root.getChildren().add(2,playgroundLower);


        root.getChildren().add(menuBar);

        Start.mainStage.setTitle("Battleship");
        Start.mainStage.setHeight(115*sizeGrid);
        Start.mainStage.setWidth(60*sizeGrid);
        Start.mainStage.setScene(GameScene);

    }


}
