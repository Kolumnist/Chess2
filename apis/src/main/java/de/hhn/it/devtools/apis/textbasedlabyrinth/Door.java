package de.hhn.it.devtools.apis.textbasedlabyrinth;


/**
 * A class for "doors".
 */
public class Door {


    private Puzzle puzzle;
    private Item key;
    private boolean locked;
    private String inspectMessage;



    public Door(boolean hasPuzzle, Item key) {
        if (hasPuzzle) {
            this.puzzle = new Puzzle(key);
            locked = true;
            inspectMessage = "This door is locked. ";
        } else {
            locked = false;
            inspectMessage = "This door is open. ";

        }
    }


    public boolean unlock(Item item) {
        boolean isSolved = puzzle.setSolved(item);
        if (isSolved) {
            locked = false;
            inspectMessage = "This door is open. ";
        }
        return isSolved;
    }

    public String getInspectMessage() {
        return inspectMessage;
    }

    public String open() {
        String s = inspectMessage;

        if (locked) {
            s = s + "You need to open it";
        } else {
            s = s + "You open the door.";
        }
        return s;
    }

}
