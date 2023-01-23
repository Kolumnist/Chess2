package de.hhn.it.devtools.javafx.battleship;

import de.hhn.it.devtools.apis.battleship.GameState;
import de.hhn.it.devtools.apis.battleship.IllegalGameStateException;
import de.hhn.it.devtools.apis.battleship.IllegalPositionException;
import de.hhn.it.devtools.apis.battleship.IllegalShipStateException;
import de.hhn.it.devtools.apis.battleship.PanelState;
import de.hhn.it.devtools.apis.battleship.Player;
import de.hhn.it.devtools.apis.battleship.Ship;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.components.battleship.provider.CmpBattleshipService;
import de.hhn.it.devtools.javafx.controllers.template.SingletonAttributeStore;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Game extends Stage {


  VBox root = new VBox();
  Scene GameScene = new Scene(root);

  ShipsLeft shipsleft;

  Stage stage = new Stage();

  GridPane playgroundUpper = new GridPane();
  GridPane playgroundLower = new GridPane();
  GridPane numbersBetween = new GridPane();

  MenuBar menuBar = new MenuBar();
  Menu menuGame = new Menu("Game");
  Menu menuSound = new Menu("Sound");
  Menu menuHelp = new Menu("Help");

  SingletonAttributeStore singletonAttributeStore = SingletonAttributeStore.getReference();
  CmpBattleshipService service;

  Player player;

  MenuItem gameSave = new MenuItem("Save");
  MenuItem gameLoad = new MenuItem("Load");
  MenuItem gameConcede = new MenuItem("Concede");
  MenuItem soundChangeVolume = new MenuItem("Change Volume");
  MenuItem helpShipsLeft = new MenuItem("Ships left");
  MenuItem helpRules = new MenuItem("Rules");

  FXBattleshipListener listener;
  Button[][] buttonsUpper;
  Button[][] buttonsLower;
  int sizeGrid;

  SoundHandler soundHandler;

  public Game(int gridSizeChoosen) {

    service = (CmpBattleshipService) singletonAttributeStore.getAttribute("Battleship.service");
    listener = new FXBattleshipListener(this);
    singletonAttributeStore.setAttribute("Battleship.game", this);

    stage.setOnCloseRequest(event -> soundHandler.stopMusic());

    try {
      service.addCallBack(listener);
    } catch (IllegalParameterException e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
    soundHandler = new SoundHandler();
    new Reminder();

    player = service.getPlayer();

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

    int sizeButtons = 50;

    buttonsUpper = new Button[sizeGrid][sizeGrid];
    buttonsLower = new Button[sizeGrid][sizeGrid];

    Character[] abc = {'A', 'B', 'C', 'D', 'E',
        'F', 'G', 'H', 'I', 'J',
        'K', 'L', 'M', 'N', 'O'};

    // Creates the upper Playground
    for (int k = 0; k < sizeGrid; k++) {
      Label lblAbc = new Label(abc[k].toString());
      lblAbc.setStyle("-fx-text-fill: white; -fx-font-size: 20; -fx-padding: 0 15 0 0 ");

      playgroundUpper.add(lblAbc, 0, k);

      for (int i = 0; i < sizeGrid; i++) {
        buttonsUpper[k][i] = new Button();
        buttonsUpper[k][i].setPrefWidth(sizeButtons);
        buttonsUpper[k][i].setPrefHeight(sizeButtons);
        buttonsUpper[k][i].setId("buttonsUpper");

        int k1 = k;
        int i1 = i;

        buttonsUpper[k][i].setOnAction(actionEvent -> {
          if (service.getCurrentGameState() == GameState.FIRINGSHOTS) {
            try {
              service.bombPanel(player, service.getComputer(), i1, k1);
            } catch (IllegalGameStateException e) {
              e.printStackTrace();
            }

            if (!(service.getPlayer().getAttackField().getPanelMarker(i1, k1)
                == PanelState.HIT)) {
              // 0 as oldZ because im forced to
              try {
                service.getComputer().comBomb(null, player, -1);

              } catch (IllegalGameStateException e) {
                e.printStackTrace();
              }
            }

          }

        });

        playgroundUpper.add(buttonsUpper[k][i], i + 1, k);
      }
    }

    // Labels the playgrounds with numbers
    for (int i = 1; i <= sizeGrid; i++) {
      Label numbers = new Label(Integer.toString(i));
      numbers.setStyle("-fx-text-fill: white; -fx-font-size: 20; -fx-padding: 15 0 -50  15");
      numbers.setAlignment(Pos.CENTER);
      playgroundUpper.add(numbers, i, sizeGrid);
    }

    // Creates the lower Playground
    for (int k = 0; k < sizeGrid; k++) {
      Label lblAbc = new Label(abc[k].toString());
      lblAbc.setStyle("-fx-text-fill: white; -fx-font-size: 20; -fx-padding: 0 15 0 0 ");

      playgroundLower.add(lblAbc, 0, k);

      for (int i = 0; i < sizeGrid; i++) {
        buttonsLower[k][i] = new Button();
        buttonsLower[k][i].setPrefWidth(sizeButtons);
        buttonsLower[k][i].setPrefHeight(sizeButtons);
        buttonsLower[k][i].setId("buttonsLower");

        // needed but I don't fully understand why
        int k1 = k;
        int i1 = i;

        buttonsLower[k][i].setOnAction(actionEvent -> {
          if (service.getCurrentGameState() == GameState.PLACINGSHIPS) {
            Ship ship = shipsleft.getShipSelected();

            // if place ship is selected
            if (!shipsleft.placeMode && !shipsleft.isHorizontal) {

              if (!shipsleft.getShipSelected().getIsVertical()) {
                try {
                  service.rotateShip(player, ship);
                } catch (IllegalPositionException | IllegalGameStateException | IllegalShipStateException e) {
                  System.out.println(e.getMessage());
                  e.printStackTrace();
                }
              }

              try {
                service.placeShip(player, shipsleft.getShipSelected(), i1, k1);
              } catch (IllegalPositionException | IllegalGameStateException | IllegalShipStateException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
              }
            }

            if (!shipsleft.placeMode && shipsleft.isHorizontal) {

              if (shipsleft.getShipSelected().getIsVertical()) {
                try {
                  service.rotateShip(player, ship);
                } catch (IllegalPositionException | IllegalGameStateException | IllegalShipStateException e) {
                  System.out.println(e.getMessage());
                  e.printStackTrace();
                }
              }

              try {
                service.placeShip(player, shipsleft.getShipSelected(), i1, k1);

              } catch (IllegalPositionException | IllegalGameStateException | IllegalShipStateException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
              }
            }

            // if unplace ship is selected
            if (shipsleft.placeMode) {

              ship = player.getShipField().getShipsOnField(i1, k1);

              if (ship != null) {
                try {
                  service.unPlace(player, player.getShipField().getShipsOnField(i1, k1));
                } catch (IllegalGameStateException e) {
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
    helpRules.setOnAction(event -> {
      new Rules();
    });

    // Menubar functionality
    helpShipsLeft.setOnAction(event -> shipsleft = new ShipsLeft());

    // Menubar functionality
    soundChangeVolume.setOnAction(actionEvent -> {
      new VolumeChanger();
    });

    // Menubar functionality
    gameSave.setOnAction(actionEvent -> {
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Save Game");
      fileChooser.showSaveDialog(stage);
    });

    // Menubar functionality
    gameLoad.setOnAction(actionEvent -> {
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Load Game");
      fileChooser.showOpenDialog(stage);
    });

    // Menubar functionality
    gameConcede.setOnAction(actionEvent -> {
      new Concede();
      try {
        service.concede();
      } catch (IllegalGameStateException e) {
        throw new RuntimeException(e);
      }
    });

    root.getChildren().add(0, playgroundUpper);
    root.getChildren().add(1, numbersBetween);
    root.getChildren().add(2, playgroundLower);

    root.getChildren().add(menuBar);

    stage.setTitle("Battleship");
    stage.setHeight(115 * sizeGrid);
    stage.setWidth(60 * sizeGrid);
    stage.setScene(GameScene);
    stage.show();


  }


}




