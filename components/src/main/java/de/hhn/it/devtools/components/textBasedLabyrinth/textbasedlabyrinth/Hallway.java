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


    public Door getOppositeDoor(Direction direction) {
        Door target = null;
        if (direction.equals(Direction.NORTH)) {
            target = getSouthDoor();
        } else if (direction.equals(Direction.SOUTH)) {
            target = getNorthDoor();
        } else if (direction.equals(Direction.EAST)) {
            target = getWestDoor();
        } else if (direction.equals(Direction.WEST)) {
            target = getEastDoor();
        } else {
            throw new IllegalArgumentException("Direction invalid");
        }

        if (target == null) {
            throw new IllegalArgumentException("Something went wrong. Target direction was valid," +
                    "but door was null.");
        }

        return target;


    }
}
