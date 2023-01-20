package de.hhn.it.devtools.javafx.battleship;

import de.hhn.it.devtools.apis.battleship.IllegalGameStateException;
import de.hhn.it.devtools.apis.battleship.IllegalPositionException;
import de.hhn.it.devtools.apis.battleship.IllegalShipStateException;
import de.hhn.it.devtools.apis.battleship.PanelState;
import de.hhn.it.devtools.components.battleship.provider.CmpBattleshipService;
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

    ShipsLeft shipsleft;

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

    Button[][] buttonsUpper;
    Button[][] buttonsLower;
    int sizeGrid;
    public Game(int gridSizeChoosen){

        sizeGrid = gridSizeChoosen;

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

        buttonsUpper = new Button[sizeGrid][sizeGrid];
        buttonsLower = new Button[sizeGrid][sizeGrid];

        Character[] abc = {'A', 'B', 'C', 'D', 'E',
                           'F', 'G', 'H', 'I', 'J',
                           'K', 'L', 'M', 'N', 'O'};


        // Creates the upper Playground
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


        // Labels the playgrounds with numbers
        for(int i = 1; i<=sizeGrid ; i++){
            Label numbers = new Label(Integer.toString(i));
            numbers.setStyle("-fx-text-fill: white; -fx-font-size: 20; -fx-padding: 15 0 -50  15");
            numbers.setAlignment(Pos.CENTER);
            playgroundUpper.add(numbers,i,sizeGrid);
        }


        // Creates the lower Playground
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
                        switch(CmpBattleshipService.service.getCurrentGameState()){
                            case PLACINGSHIPS -> {
                                try {
                                    CmpBattleshipService.service.placeShip(CmpBattleshipService.service.getPlayer(),shipsleft.getShipSelected(),i1,k1);
                                    updateField();
                                } catch (IllegalPositionException | IllegalShipStateException | IllegalGameStateException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                });

                playgroundLower.add(buttonsLower[k][i], i + 1, k);
            }
        }


        // Menubar functionality
        helpRules.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Rules rules = new Rules();
            }
        });

        // Menubar functionality
        helpShipsLeft.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                shipsleft = new ShipsLeft();
            }
        });

        // Menubar functionality
        soundChangeVolume.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                VolumeChanger volumeChanger = new VolumeChanger();
            }
        });

        // Menubar functionality
        gameSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save Game");
                fileChooser.showSaveDialog(Start.mainStage);
            }
        });

        // Menubar functionality
        gameLoad.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Load Game");
                fileChooser.showOpenDialog(Start.mainStage);
            }
        });

        // Menubar functionality
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

    public void updateField(){

        // Only update if player places ship is done till now
        //PanelState[][] playerShipsPlaced = CmpBattleshipService.service.getPlayer().getShipField().getPanelMarkerMat();
        for (int i = 0; i < sizeGrid ; i++) {
            for (int k = 0; k < sizeGrid ; k++) {

                switch (CmpBattleshipService.service.getPlayer().getShipField().getShipsOnField(k,i).getShipType()){

                    case CARRIER:
                        buttonsLower[k][i].getStyleClass().add("carrier");
                        break;

                    case BATTLESHIP:
                        buttonsLower[k][i].getStyleClass().add("battleship");
                        break;

                    case CRUISER:
                        buttonsLower[k][i].getStyleClass().add("cruiser");
                        break;

                    case SUBMARINE:
                        buttonsLower[k][i].getStyleClass().add("submarine");
                        break;

                    case DESTROYER:
                        buttonsLower[k][i].getStyleClass().add("destroyer");
                        break;

                    default: break;
                }

                //buttonsLower[k][i].getStyleClass().add("buttonPlacedShips");
                }
            }
        }
    }

