package de.hhn.it.devtools.javafx.battleship;

import de.hhn.it.devtools.apis.battleship.GameState;
import de.hhn.it.devtools.apis.battleship.Ship;
import de.hhn.it.devtools.apis.battleship.ShipType;
import de.hhn.it.devtools.components.battleship.provider.CmpBattleshipService;
import de.hhn.it.devtools.javafx.controllers.template.SingletonAttributeStore;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ShipsLeft {


  Stage stage = new Stage();
  BorderPane borderPane = new BorderPane();
  Scene scene = new Scene(borderPane);
  VBox vbox = new VBox();

  Button carrier = new Button("Carrier, Length: 5");
  Button battleship = new Button("Battleship, length: 4");
  Button cruiser = new Button("Cruiser, length: 3");
  Button submarine = new Button("Submarine, length: 3");
  Button destroyer = new Button("Destroyer, length: 2");

  Button rotate = new Button("horizontal");
  Button unplace = new Button("place");
  Button startFiring = new Button("Click to start firing");

  Boolean placeMode = false;
  boolean isHorizontal = true;


  SingletonAttributeStore singletonAttributeStore = SingletonAttributeStore.getReference();
  CmpBattleshipService service;

  private Ship shipSelected;

  ShipsLeft() {

    service = (CmpBattleshipService) singletonAttributeStore.getAttribute("Battleship.service");

    int carrierCount = service.getPlayer().countShipType(ShipType.CARRIER);
    int battleshipCount = service.getPlayer().countShipType(ShipType.BATTLESHIP);
    int cruiserCount = service.getPlayer().countShipType(ShipType.CRUISER);
    int submarineCount = service.getPlayer().countShipType(ShipType.SUBMARINE);
    int destroyerCount = service.getPlayer().countShipType(ShipType.DESTROYER);

    carrier.setText("Carrier, Length: 5, Ships Left: " + carrierCount);
    battleship.setText("Battleship, Length: 4, Ships Left: " + battleshipCount);
    cruiser.setText("Cruiser, Length: 3, Ships Left: " + cruiserCount);
    submarine.setText("Submarine, Length: 3, Ships Left: " + submarineCount);
    destroyer.setText("Destroyer, Length: 2, Ships Left: " + destroyerCount);

    scene.getStylesheets().add("battleship/style.css");

    stage.setWidth(800);
    //stage.setHeight(600);
    stage.setHeight(800);
    stage.setResizable(false);

    Label title = new Label("Ship left to place");

    title.setStyle(
        "-fx-font-size: 30; -fx-background-color: white; -fx-text-fill: #5BC0BE; -fx-background-radius: 10");
    title.setPadding(new Insets(30, 30, 30, 30));

    startFiring.setVisible(false);

    startFiring.setOnAction(actionEvent -> {
      service.setCurrentGameState(GameState.FIRINGSHOTS);
      vbox.getChildren().remove(startFiring);
    });

    rotate.setOnAction(actionEvent -> {
      if (rotate.getText().equals("horizontal")) {
        rotate.setText("vertical");
        isHorizontal = false;
      } else {
        rotate.setText("horizontal");
        isHorizontal = true;
      }
    });

    unplace.setOnAction(actionEvent -> {
      if (unplace.getText().equals("place")) {
        unplace.setText("unplace");
        placeMode = true;
      } else {
        unplace.setText("place");
        placeMode = false;
      }
    });

    // Sets selected Ship
    carrier.setOnAction(actionEvent -> {
      setShipSelected(service.getPlayer().returnNextShip(ShipType.CARRIER));
      if (shipSelected != null) {
        resetStylesSelectShips();
        carrier.getStyleClass().add("buttonSelectShip");
      } else {
        resetStylesSelectShips();
      }

    });

    // Sets selected Ship
    battleship.setOnAction(actionEvent -> {
      setShipSelected(service.getPlayer().returnNextShip(ShipType.BATTLESHIP));
      if (shipSelected != null) {
        resetStylesSelectShips();
        battleship.getStyleClass().add("buttonSelectShip");
      } else {
        resetStylesSelectShips();
      }
    });

    // Sets selected Ship
    cruiser.setOnAction(actionEvent -> {
      setShipSelected(service.getPlayer().returnNextShip(ShipType.CRUISER));
      if (shipSelected != null) {
        resetStylesSelectShips();
        cruiser.getStyleClass().add("buttonSelectShip");
      } else {
        resetStylesSelectShips();
      }
    });

    // Sets selected Ship
    submarine.setOnAction(actionEvent -> {
      setShipSelected(service.getPlayer().returnNextShip(ShipType.SUBMARINE));
      if (shipSelected != null) {
        resetStylesSelectShips();
        submarine.getStyleClass().add("buttonSelectShip");
      } else {
        resetStylesSelectShips();
      }
    });

    // Sets selected Ship
    destroyer.setOnAction(actionEvent -> {
      setShipSelected(service.getPlayer().returnNextShip(ShipType.DESTROYER));
      if (shipSelected != null) {
        resetStylesSelectShips();
        destroyer.getStyleClass().add("buttonSelectShip");
      } else {
        resetStylesSelectShips();
      }
    });

    BorderPane.setAlignment(title, Pos.CENTER);
    BorderPane.setMargin(title, new Insets(30, 0, 30, 0));
    borderPane.setTop(title);
    borderPane.setStyle("-fx-background-color: #5BC0BE");

    borderPane.setCenter(vbox);
    vbox.setSpacing(30);
    vbox.setPadding(new Insets(50));

    vbox.getChildren().add(carrier);
    vbox.getChildren().add(battleship);
    vbox.getChildren().add(cruiser);
    vbox.getChildren().add(submarine);
    vbox.getChildren().add(destroyer);

    vbox.getChildren().add(unplace);
    vbox.getChildren().add(rotate);
    vbox.getChildren().add(startFiring);

    stage.setScene(scene);
    stage.setTitle("Ships left to place");
    stage.show();

  }

  public void resetStylesSelectShips() {
    carrier.getStyleClass().removeAll("buttonSelectShip");
    battleship.getStyleClass().removeAll("buttonSelectShip");
    cruiser.getStyleClass().removeAll("buttonSelectShip");
    submarine.getStyleClass().removeAll("buttonSelectShip");
    destroyer.getStyleClass().removeAll("buttonSelectShip");

  }

  public Ship getShipSelected() {
    return shipSelected;
  }

  public void setShipSelected(Ship ship) {
    shipSelected = ship;
  }
}

