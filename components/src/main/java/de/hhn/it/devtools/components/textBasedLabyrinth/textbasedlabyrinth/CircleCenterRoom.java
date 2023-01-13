package de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth;

public class CircleCenterRoom extends TrapRoom {


    private Room rotateTarget;
    private Hallway rotatingHallway;
    private Direction futureDirectionOfHallway;
    private Direction directionOfFakeDoorInTargetRoom;

    /**
     * Constructor of special circle room.
     *
     * @param id ID of the room.
     * @param description the description of the rooms' appearance.
     */
    public CircleCenterRoom(int id, String description) {
        super(id, description);

    }


    /**
     * This room uses this method to create the rest of it´s doors and the target door of the target
     * of the rotation as fake doors.
     * It must be used after the doors have been set as normal,
     * therefore after all the rooms have been created and set.
     */
    @Override
    public void setTrap() {

    }

    @Override
    public void removeItem(int itemId) {
        super.removeItem(itemId);
        if (items.get(itemId).getIsTrapActivator() == true) {
            rotate();
        }
    }


    private void rotate() {

    }


    public Room getRotateTarget() {
        return rotateTarget;
    }

    public void setRotateTarget(Room rotateTarget) {
        this.rotateTarget = rotateTarget;
    }


    public void setRotatingHallway(Hallway rotatingHallway) {
        this.rotatingHallway = rotatingHallway;
    }

    public Hallway getRotatingHallway() {
        return rotatingHallway;
    }

    public Direction getFutureDirectionOfHallway() {
        return futureDirectionOfHallway;
    }

    public void setFutureDirectionOfHallway(Direction futureDirectionOfHallway) {
        this.futureDirectionOfHallway = futureDirectionOfHallway;
    }
}
