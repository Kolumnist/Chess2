package de.hhn.it.devtools.javafx.text_based_labyrinth_FX;

import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.Game;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LabyrinthLauncher extends Application {




    @Override
    public void start(final Stage primaryStage) throws Exception {

        Pane pane = new Pane();
        Game game = new Game();
        GameScreenController screenController = new GameScreenController(pane);
        GameViewModel viewModel = new GameViewModel(game, screenController);
        TabPane root = new TabPane();
        Tab gameTab = new Tab("Labyrinth", pane);
        screenController.changeScreen(MenuScreen.SCREEN_NAME, MenuScreen.SCREEN_NAME);
        root.getTabs().add(gameTab);

        primaryStage.setTitle("Labyrinth Game");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.setHeight(520);
        primaryStage.setWidth(615);
        primaryStage.show();

    }
}
