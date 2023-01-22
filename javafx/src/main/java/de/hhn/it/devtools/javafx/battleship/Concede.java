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

    //
    Stage stage = new Stage();

    public Concede(){
        Label a = new Label("You lost");
        stage.setScene(sceneGameMenu);
        stage.setHeight(400);
        stage.setWidth(250);
//        Button newGameButton = new Button("Play again");
//        vbox.getChildren().add(newGameButton);
//        newGameButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
                /*
                // Create new gameMenu in the main window
                try {

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                 */
//            }
//        });
        vbox.getChildren().add(a);
        stage.show();

        // Set sceneGameMenu as new main stage from the main window and show it
    }
}
