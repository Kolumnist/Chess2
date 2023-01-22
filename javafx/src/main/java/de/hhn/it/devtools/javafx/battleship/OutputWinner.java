package de.hhn.it.devtools.javafx.battleship;

import de.hhn.it.devtools.apis.battleship.Player;
import de.hhn.it.devtools.components.battleship.provider.CmpBattleshipService;
import de.hhn.it.devtools.javafx.controllers.template.SingletonAttributeStore;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OutputWinner {

  Stage stage = new Stage();
  VBox stackpane = new VBox();
  Scene scene = new Scene(stackpane);
  CmpBattleshipService service;
  SingletonAttributeStore singletonAttributeStore = SingletonAttributeStore.getReference();
  Label lbl = new Label();
  Button btn = new Button("Return to Menu");

  OutputWinner(Player playerWon) {
    service = (CmpBattleshipService) singletonAttributeStore.getAttribute("Battleship.service");
    stage.setScene(scene);
    if (playerWon == service.getPlayer()) {
      lbl.setText("PLAYER WON");
    } else {
      lbl.setText("COMPUTER WON");
    }
    lbl.setMinWidth(400);
    lbl.setMinHeight(200);
    stackpane.getChildren().add(lbl);
    btn.setOnAction(actionEvent -> {
      // return to menu
      // closes the game window and outputwinner window
    });
    btn.setMinWidth(400);
    btn.setMinHeight(200);
    stackpane.getChildren().add(btn);
    stage.setHeight(400);
    stage.setWidth(400);
    stage.setAlwaysOnTop(true);
    stackpane.setStyle("-fx-background-color: indianred");
    stage.show();
  }
}
