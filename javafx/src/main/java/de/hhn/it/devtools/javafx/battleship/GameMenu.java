package de.hhn.it.devtools.javafx.battleship;

import de.hhn.it.devtools.apis.battleship.IllegalGameStateException;
import de.hhn.it.devtools.apis.battleship.IllegalPositionException;
import de.hhn.it.devtools.apis.battleship.IllegalShipStateException;
import de.hhn.it.devtools.components.battleship.provider.CmpBattleshipService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;

public class GameMenu{

    VBox vbox = new VBox();
    Scene sceneGameMenu = new Scene(vbox);
    Button smallGame = new Button("Small (5x5)");
    Button mediumGame = new Button("Medium (10x10)");
    Button bigGame = new Button("Big (15x15)");

    public GameMenu() throws FileNotFoundException {

        sceneGameMenu.getStylesheets().add("battleship/style.css");
        vbox.setId("gameMenuVbox");

        ImageView imageView = new ImageView("battleship/GameMenuLogo.jpg");

        int imageSize  = 600;
        imageView.setFitWidth(imageSize);
        imageView.setFitHeight(imageSize/2);
        imageView.setSmooth(true);

        vbox.getChildren().add(imageView);

        vbox.setPrefSize(900,900);
        Start.mainStage.setWidth(900);
        Start.mainStage.setHeight(900);
        smallGame.getStyleClass().add("gameMenuButtons-style");
        mediumGame.getStyleClass().add("gameMenuButtons-style");
        bigGame.getStyleClass().add("gameMenuButtons-style");

        smallGame.setPrefSize(300,150);
        mediumGame.setPrefSize(300,150);
        bigGame.setPrefSize(300,150);

        vbox.getChildren().add(smallGame);
        vbox.getChildren().add(mediumGame);
        vbox.getChildren().add(bigGame);

        Start.mainStage.setScene(sceneGameMenu);
        Start.mainStage.show();

        smallGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                createService(5);
            }
        });

        mediumGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                createService(10);
            }
        });

        bigGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                createService(15);
            }
        });
    }

    public void createService(int size){
        Game game = new Game(size);
        try {
            CmpBattleshipService.service.createFields(size);
        } catch (IllegalGameStateException | IllegalShipStateException | IllegalPositionException e) {
            e.printStackTrace();
        }
    }
}
