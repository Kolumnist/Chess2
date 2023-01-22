package de.hhn.it.devtools.apis.textbasedlabyrinth;


/**
 * A class for "doors".
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
   * Open door.
   *
   * @param item using item
   * @return door is open
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
   * Get info about the puzzle.
   *
   * @return puzzle
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
   * Item needs to be used for the door.
   *
   * @param key get item
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

  public void isNotFakeAnymore() {
    fake = false;
  }

  public Puzzle getPuzzle() {
    return puzzle;
  }


  public void setInspectMessage(String inspectMessage) {
    this.inspectMessage = inspectMessage;
  }
}
