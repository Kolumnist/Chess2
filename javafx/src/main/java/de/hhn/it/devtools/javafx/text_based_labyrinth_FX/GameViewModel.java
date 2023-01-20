package de.hhn.it.devtools.javafx.text_based_labyrinth_FX;

import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.Game;

public class GameViewModel {


    private Game game;
    private GameScreenController screenController;

    public GameViewModel(Game game, GameScreenController screenController) {
        this.game = game;
        this.screenController = screenController;



    }


    public Game getGame() {
        return game;
    }
}
