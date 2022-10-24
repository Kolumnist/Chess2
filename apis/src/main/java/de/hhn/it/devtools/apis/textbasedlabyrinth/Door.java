package de.hhn.it.devtools.apis.textbasedlabyrinth;


/**
 * A class for "doors".
 */
public class Door {


    private Puzzle puzzle;
    private boolean locked;
    private String inspectMessage;



    public Door() {
        inspectMessage = "This door is open";
        locked = false;
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
        String s = inspectMessage;
        if (locked) {
            s = s + puzzle.getDescription();
        }

        return s;
    }

    public void setPuzzle(Item key) {
        this.puzzle = new Puzzle(key);

        locked = true;
        inspectMessage = "This door is locked.";
    }

    public String open() {
        String s = inspectMessage;

        if (locked) {
            s = s + ""
                    + puzzle.getDescription();
        } else {
            s = s + ""
                    + "You open the door.";
        }
        return s;
    }

    public boolean checkIfLocked() {
        return locked;
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }

}
