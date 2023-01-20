package de.hhn.it.devtools.javafx.battleship;

import de.hhn.it.devtools.apis.battleship.Player;
import de.hhn.it.devtools.apis.battleship.Ship;
import de.hhn.it.devtools.apis.battleship.ShipType;
import de.hhn.it.devtools.components.battleship.provider.CmpBattleshipService;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ShipsLeft {


    Stage stage = new Stage();
    BorderPane borderPane=new BorderPane();
    Scene scene = new Scene(borderPane);
    VBox vbox = new VBox();

    Button carrier = new Button("Carrier, Length: 5");
    Button battleship = new Button("Battleship, length: 4");
    Button cruiser = new Button("Cruiser, length: 3");
    Button submarine = new Button("Submarine, length: 3");
    Button destroyer = new Button("Destroyer, length: 2");

    Label carriersLeft = new Label();
    Label battleshipsLeft = new Label();
    Label cruisersLeft = new Label();
    Label submarinesLeft = new Label();
    Label destroyersLeft = new Label();

    private Ship shipSelected;

    ShipsLeft(){
        scene.getStylesheets().add("battleship/style.css");

        stage.setWidth(800);
        stage.setHeight(600);
        stage.setResizable(false);


        Label title = new Label("Ship left to place");

        title.setStyle("-fx-font-size: 30; -fx-background-color: white; -fx-text-fill: #5BC0BE; -fx-background-radius: 10");
        title.setPadding(new Insets(30,30,30,30));

        // Sets selected Ship
        carrier.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                shipSelected = CmpBattleshipService.service.getPlayer().returnNextShip(ShipType.CARRIER);
                resetStylesSelectShips();
                carrier.getStyleClass().add("buttonSelectShip");

            }
        });

        // Sets selected Ship
        battleship.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                shipSelected = CmpBattleshipService.service.getPlayer().returnNextShip(ShipType.BATTLESHIP);
                resetStylesSelectShips();
                battleship.getStyleClass().add("buttonSelectShip");
            }
        });

        // Sets selected Ship
        cruiser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                shipSelected = CmpBattleshipService.service.getPlayer().returnNextShip(ShipType.CRUISER);
                resetStylesSelectShips();
                cruiser.getStyleClass().add("buttonSelectShip");
            }
        });

        // Sets selected Ship
        submarine.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                shipSelected = CmpBattleshipService.service.getPlayer().returnNextShip(ShipType.SUBMARINE);
                resetStylesSelectShips();
                submarine.getStyleClass().add("buttonSelectShip");
            }
        });

        // Sets selected Ship
        destroyer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                shipSelected = CmpBattleshipService.service.getPlayer().returnNextShip(ShipType.DESTROYER);
                resetStylesSelectShips();
                destroyer.getStyleClass().add("buttonSelectShip");
            }
        });



        BorderPane.setAlignment(title, Pos.CENTER);
        BorderPane.setMargin(title,new Insets(30,0,30,0));
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


        stage.setScene(scene);
        stage.setTitle("Ships left to place");
        stage.show();

    }

    public void resetStylesSelectShips(){
        carrier.getStyleClass().removeAll("buttonSelectShip");
        battleship.getStyleClass().removeAll("buttonSelectShip");
        cruiser.getStyleClass().removeAll("buttonSelectShip");
        submarine.getStyleClass().removeAll("buttonSelectShip");
        destroyer.getStyleClass().removeAll("buttonSelectShip");

    }

    public Ship getShipSelected() {
        return shipSelected;
    }
}

