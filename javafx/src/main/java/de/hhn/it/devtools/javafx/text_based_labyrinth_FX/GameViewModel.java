package de.hhn.it.devtools.javafx.text_based_labyrinth_FX;

import de.hhn.it.devtools.apis.textbasedlabyrinth.Item;
import de.hhn.it.devtools.apis.textbasedlabyrinth.OutputListener;
import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.Game;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class GameViewModel implements OutputListener {


    private Game game;
    private GameScreenController screenController;
    private IntegerProperty score = new SimpleIntegerProperty();

    public GameViewModel(Game game, GameScreenController screenController) {
        this.game = game;
        this.screenController = screenController;
        this.score.setValue(0);

        game.startup();

        screenController.getMenuScreen().setViewModel(this);
        screenController.getMainScreen().setViewModel(this);
        screenController.getRoomInventoryScreen().setViewModel(this);
        screenController.getInteractScreen().setViewModel(this);
        screenController.getInventoryScreen().setViewModel(this);
        screenController.getHelpScreen().setViewModel(this);


        game.addListener(this);

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
    public void sendOutputInteractItemName(String output) {
        screenController.getInteractScreen().updateItemNameField(output);
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
        screenController.getMainScreen().update();
    }

    @Override
    public void listenerMove() {
        screenController.getMainScreen().update();
    }

    @Override
    public void outputInventoryItemInspect(String output) {
        screenController.getInventoryScreen().updateInspectField(output);
    }

    @Override
    public void outputInventoryItemName(String output) {
        screenController.getInventoryScreen().updateItemNameField(output);
    }

    @Override
    public void outputRoomItemInspect(String output) {
        screenController.getRoomInventoryScreen().updateInspectField(output);
    }

    @Override
    public void outputRoomItemName(String output) {
        screenController.getRoomInventoryScreen().updateItemNameField(output);
    }


    @Override
    public void listenerInteract() {

    }

    @Override
    public void listenerEnd() {

    }

    @Override
    public void listenerReset() {
        screenController.getMainScreen().update();
        screenController.getInteractScreen().update();
        screenController.getInventoryScreen().update();
        screenController.getRoomInventoryScreen().update();
    }

    @Override
    public void updateScore(int newScore) {
        score.setValue(newScore);
    }

    public IntegerProperty getScore() {
        return score;
    }
}
