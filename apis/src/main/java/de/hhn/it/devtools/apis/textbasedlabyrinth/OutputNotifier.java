package de.hhn.it.devtools.apis.textbasedlabyrinth;

/**
 * Callback handler, implementing Callback interface
 */
public class OutputNotifier implements OutputListener{

    public OutputNotifier(){ }


    /**
     * Output to what is happening in the current room and what state it is right now.
     * @param output String to be output.
     */
    @Override
    public void sendOutputRoom(String output) {
        System.out.println(output);
    }

    /**
     * Output about the state of the player and the actions done by him.
     * Picking items up and dropping them is currently considered a player action, not an inventory action.
     * @param output String to be output.
     */
    @Override
    public void sendOutputPlayer(String output) {
        System.out.println(output);
    }

    /**
     * Output about the state of the inventory and items inspected inside it.
     * Mainly for inspecting the items.
     * @param output String to be output.
     */
    @Override
    public void sendOutputInventory(String output) {
        System.out.println(output);
    }

    /**
     * Output about where the player can go.
     * @param output string to be output.
     */
    @Override
    public void sendOutputNavigation(String output) {
        sendOutputPlayer(output);
    }

    /**
     * Output collected when player interacts with a door or obstacle.
     * @param output string to be output.
     */
    @Override
    public void sendOutputPlayerInteract(String output) {
        sendOutputPlayer(output);
    }
}
