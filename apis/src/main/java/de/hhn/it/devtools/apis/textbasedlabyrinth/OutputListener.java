package de.hhn.it.devtools.apis.textbasedlabyrinth;

import java.util.List;

/**
 * Interface for callback method
 */
public interface OutputListener {

    /**
     * Output to what is happening in the current room and what state it is right now.
     * @param output String to be output.
     */
    void sendOutputRoom(String output);

    /**
     * Output about the state of the player and the actions done by him.
     * Picking items up and dropping them is currently considered a player action, not an inventory action.
     * @param output String to be output.
     */
    void sendOutputPlayer(String output);

    /**
     * Output about the state of the inventory and items inspected inside it.
     * Mainly for inspecting the items.
     * @param output String to be output.
     */
    void sendOutputInventory(String output);

    /**
     * Output about where the player can go.
     * @param output string to be output.
     */
    void sendOutputNavigation(String output);

    /**
     * Output collected when player interacts with a door or obstacle.
     * @param output string to be output.
     */
    void sendOutputPlayerInteract(String output);

    /**
     * Output collected when player interacts with an item.
     * @param output string to be output.
     */
    void sendOutputInteractItemName(String output);


    /**
     * Output collected when player picks up an item.
     * @param item item that can give output.
     */
    void sendOutputPickUpItem(Item item);

    /**
     * Output collected when player drops an item.
     * @param item item that can give output.
     */
    void sendOutputDropItem(Item item);

    void listenerStart();

    void listenerMove();

    /**
     * Output collected when player inspects item in inventory.
     * @param output that can give output.
     */
    void outputInventoryItemInspect(String output);

    /**
     * Output collected when player inspects item in inventory.
     * @param output that can give output.
     */
    void outputInventoryItemName(String output);

    /**
     * Output collected when player inspects item lying in a room.
     * @param output that can give output.
     */
    void outputRoomItemInspect(String output);

    /**
     * Output collected when player inspects item name lying in a room.
     * @param output that can give output.
     */
    void outputRoomItemName(String output);

    void listenerInteract();

    void listenerEnd();

    void listenerReset();

    void updateScore(int newScore);

    List<String> getOutputRoom();

    List<String> getOutputPlayer();

    List<String> getOutputInventory();

    List<String> getOutputNavigation();

    List<String> getOutputPlayerInteract();

    List<String> getOutputInteractItemName();

    List<String> getOutputRoomItemName();

    List<String> getOutputRoomItemInspect();

    List<String> getOutputInventoryItemInspect();

    List<String> getOutputInventoryItemName();

    List<Item> getOutputPickUpItems();

    List<Item> getOutputDropItems();

    List<Integer> getOutputScores();
}
