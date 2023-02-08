package de.hhn.it.devtools.apis.textbasedlabyrinth;


/**
 * Class for Doors. They will be the obstacle or entrance to the next room
 */
public class Door {

  private Puzzle puzzle;
  private boolean locked;
  private boolean fake;
  private boolean hasPuzzle;
  private String inspectMessage;


  /**
   * Door constructor.
   */
  public Door() {
    inspectMessage = "This is an open path. You could just walk through.";
    locked = false;
    fake = false;
    hasPuzzle = false;
  }

  /**
   * Method to unlock the Door. if there is no Puzzle on the door, nothing will happen.
   * If there is a puzzle, the Door will check if the Key item is the fitting one and
   * if true will unlock the door.
   *
   * @param item key item that will be tested if it fits for the current puzzle.
   * @return true if the item is the correct key item for the puzzle of the door.
   */
  public boolean unlock(Item item) {
    if (!hasPuzzle) {
      return false;
    } else {
      boolean isSolved = puzzle.setSolved(item.getItemId());
      if (isSolved) {
        locked = false;
        inspectMessage = "This door is open. ";
      }
      return isSolved;
    }
  }

  /**
   * Returns the Inspect message of the door or the puzzle description if available.
   *
   * @return Inspect message of door.
   */
  public String getInspectMessage() {
    String s = inspectMessage;
    if (hasPuzzle) {
      if (locked) {
        s = s + puzzle.getDescription();
      }
    }

    return s;
  }

  /**
   * sets Puzzle for door and assignees item as a key for it.
   *
   * @param key item object used to solve this puzzle.
   */
  public void setPuzzle(Item key) {
    this.puzzle = new Puzzle(key.getItemId());
    locked = true;
    hasPuzzle = true;
    inspectMessage = "There is a door in front of you. It seems to be locked.";
  }

  /**
   * Opens the door.
   *
   * @return message that varies, depending upon the locked status of the door .
   */
  public String open() {
    String s = inspectMessage;

    if (locked) {
      s = s + ""
              + puzzle.getDescription();
    } else if (hasPuzzle) {
      s = s + ""
              + "You open the door. And step into the next room.";
    } else {
      s = "You step into the next room through the opening.";
    }
    return s;
  }

  public boolean checkIfLocked() {
    return locked;
  }

  public boolean checkIfFake() {
    return fake;
  }

  public boolean hasPuzzle() {
    return hasPuzzle;
  }

  public void isFake() {
    fake = true;
  }

  public Puzzle getPuzzle() {
    return puzzle;
  }

  public void setInspectMessage(String inspectMessage) {
    this.inspectMessage = inspectMessage;
  }
}
