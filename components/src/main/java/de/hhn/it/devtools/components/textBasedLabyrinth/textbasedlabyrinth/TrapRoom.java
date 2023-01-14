package de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth;



public class TrapRoom extends Room {
    /**
     * Constructor of a room that activates a trap upon a set action.
     *
     * @param id ID of the room.
     * @param description description of the room.
     */
    public TrapRoom(int id, String description) {
        super(id, description);
    }


    /**
     * This is a method that every unique trap room will inherit
     * and then implement and overwrite on it´s own.
     */
    public void setTrap() {

    }
}
