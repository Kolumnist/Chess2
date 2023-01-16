package de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth;

public class Hallway extends Room {



    /**
     * Constructor of a hallway.
     * A hallway should only connect to two doors, and should be straight only.
     *
     * @param id ID of the room.
     * @param description the description of the rooms' appearance.
     */
    public Hallway(int id, String description) {
        super(id, description);
    }


    public Door getOppositeDoor(Direction direction) throws IllegalArgumentException {
        if (direction == null) {
            throw new IllegalArgumentException("Direction was null.");
        }
        if (!doorMap.containsKey(direction.getOpposite())) {
            throw new IllegalArgumentException("Something went wrong. Target direction was valid," +
                    "but door was null.");
        }

        Door target = null;

        target = doorMap.get(direction.getOpposite());

        return target;


    }
}
