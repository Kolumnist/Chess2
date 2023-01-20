package de.hhn.it.devtools.components.textbasedlabyrinth.junit;

import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.*;
import de.hhn.it.devtools.apis.textbasedlabyrinth.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DummyCallback implements OutputListener{
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(DummyCallback.class);

    private List<String> outputRoom;
    private List<String> outputPlayer;
    private List<String> outputInventory;
    private List<String> outputNavigation;
    private List<String> outputPlayerInteract;
    private List<Item> outputPickUpItems;
    private List<Item> outputDropItems;
    private List<Integer> outputScores;

    public DummyCallback(){
        outputRoom = new ArrayList<>();
        outputPlayer = new ArrayList<>();
        outputInventory = new ArrayList<>();
        outputNavigation = new ArrayList<>();
        outputPlayerInteract = new ArrayList<>();
        outputPickUpItems = new ArrayList<>();
        outputDropItems = new ArrayList<>();
        outputScores = new ArrayList<>();
    }

    @Override
    public void sendOutputRoom(String output) {
        outputRoom.add(output);
    }

    @Override
    public void sendOutputPlayer(String output) {
        outputPlayer.add(output);
    }

    @Override
    public void sendOutputInventory(String output) {
        outputInventory.add(output);
    }

    @Override
    public void sendOutputNavigation(String output) {
        outputNavigation.add(output);
    }

    @Override
    public void sendOutputPlayerInteract(String output) {
        outputPlayerInteract.add(output);
    }

    @Override
    public void sendOutputPickUpItem(Item item) {
        outputPickUpItems.add(item);
    }

    @Override
    public void sendOutputDropItem(Item item) {
        outputDropItems.add(item);
    }

    @Override
    public void listenerStart() {

    }

    @Override
    public void listenerMove() {

    }

    @Override
    public void listenerPickUpItem() {

    }

    @Override
    public void listenerDropItem() {

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
        outputScores.add(newScore);
    }
}
