package de.hhn.it.devtools.apis.textbasedlabyrinth;

/**
 * Direction enum.
 */
public enum Direction {

    NORTH,

    SOUTH,

    WEST,

    EAST;


    private Direction opposite;

    static {
        NORTH.opposite = SOUTH;
        SOUTH.opposite = NORTH;
        WEST.opposite = EAST;
        EAST.opposite = WEST;
    }


    public Direction getOpposite() {
        return opposite;
    }

}
