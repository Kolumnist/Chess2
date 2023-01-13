package de.hhn.it.devtools.javafx.battleship;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class Concede {

    VBox vbox = new VBox();
    Scene sceneGameMenu = new Scene(vbox);

    public Concede(){
        Label a = new Label("You lost");
        Button newGameButton = new Button("Play again");
        vbox.getChildren().add(newGameButton);
        newGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    GameMenu gameMenu = new GameMenu();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        vbox.getChildren().add(a);
        Start.mainStage.setScene(sceneGameMenu);
        Start.mainStage.show();
    }
}
