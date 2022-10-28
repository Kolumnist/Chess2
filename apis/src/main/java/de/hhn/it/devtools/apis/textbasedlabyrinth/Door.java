package de.hhn.it.devtools.apis.textbasedlabyrinth;


/**
 * A class for "doors".
 */
public class Door {


  private Puzzle puzzle;
  private boolean locked;
  private String inspectMessage;

  /**
   * Creates unlocked door.
   */

  public Door() {
    inspectMessage = "This door is open";
    locked = false;
  }

  /**
   * how to unlock the door.
   *
   * @param item is there an item.
   * @return if puzzle is solved it opens door.
   *
   */

  public boolean unlock(Item item) {
    boolean isSolved = puzzle.setSolved(item);
    if (isSolved) {
      locked = false;
      inspectMessage = "This door is open. ";
    }
    return isSolved;
  }

  /**
   * checks if door is locked and gives informations.
   *
   * @return gives back the message how to open the door.
   *
   */
  public String getInspectMessage() {
    String s = inspectMessage;
    if (locked) {
      s = s + puzzle.getDescription();
    }

    return s;
  }

  /**
   * Gives the Player a puzzle to open the door.
   *
   * @param key gets item to open door.
   *
   */
  public void setPuzzle(Item key) {
    this.puzzle = new Puzzle(key);

    locked = true;
    inspectMessage = "This door is locked.";
  }

  /**
   * Opens the door so the player can get thru.
   *
   * @return opens the door.
   *
   */
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
