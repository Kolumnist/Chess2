package de.hhn.it.devtools.javafx.text_based_labyrinth_FX;

import de.hhn.it.devtools.apis.textbasedlabyrinth.Item;
import de.hhn.it.devtools.apis.textbasedlabyrinth.OutputListener;
import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.Game;

public class GameViewModel implements OutputListener {


    private Game game;
    private GameScreenController screenController;
    private int score;

    public GameViewModel(Game game, GameScreenController screenController) {
        this.game = game;
        this.screenController = screenController;
        game.startup();
        score = 0;

    }


    public Game getGame() {
        return game;
    }

    @Override
    public void sendOutputRoom(String output) {
        screenController.getMainScreen().updateRoomField(output);
    }

    @Override
    public void sendOutputPlayer(String output) {
        screenController.getMainScreen().updatePlayerActionField(output);
    }

    @Override
    public void sendOutputInventory(String output) {
        screenController.getInventoryScreen().updateInspectField(output);
    }

    @Override
    public void sendOutputNavigation(String output) {
        screenController.getMainScreen().updateActionField(output);
    }

    @Override
    public void sendOutputPlayerInteract(String output) {
        screenController.getInteractScreen().updateInteractField(output);
    }

    @Override
    public void sendOutputPickUpItem(Item item) {
        String message = "You pick up " + item.getName() + ".";
        screenController.getRoomInventoryScreen().updateInspectField(message);
    }

    @Override
    public void sendOutputDropItem(Item item) {
        String message = "You put " + item.getName() + " on the ground.";
        screenController.getInventoryScreen().updateInspectField(message);
    }

    @Override
    public void listenerStart() {

    }

    @Override
    public void listenerMove() {

    }

    @Override
    public void outputPickUpItemInspect(String output) {

    }

    @Override
    public void outputDropItemInspect(String output) {

    }


    @Override
    public void listenerInteract() {

    }

    @Override
    public void listenerEnd() {

    }

    @Override
    public void listenerReset() {

    }

    @Override
    public void updateScore(int newScore) {
        score = newScore;
    }

    public int getScore() {
        return score;
    }
}
