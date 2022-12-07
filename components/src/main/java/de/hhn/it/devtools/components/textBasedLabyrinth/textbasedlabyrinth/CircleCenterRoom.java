package de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth;

public class CircleCenterRoom extends Room {


    /**
     * Constructor of special circle room.
     *
     * @param id ID of the room.
     * @param description the description of the rooms' appearance.
     */
    public CircleCenterRoom(int id, String description) {
        super(id, description);
    }


    @Override
    public void removeItem(int itemId) {
        if (items.get(itemId).getIsTrapActivator() == true) {
            rotate();
        }
    }


    private void rotate() {
    }
}
