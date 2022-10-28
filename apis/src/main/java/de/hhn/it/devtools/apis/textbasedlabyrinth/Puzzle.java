package de.hhn.it.devtools.apis.textbasedlabyrinth;

/**
 * Puzzle.
 */
public class Puzzle {


  //private final int puzzleId;
  private boolean isSolved;
  private boolean isBeingWorkedOn;
  private Item item;
  private String description;
  private String unlockMessage;
  private String lockedMessage;

  /**
   * Gets a Puzzle or an item to solve the puzzle.
   *
   * @param item gets an collected item
   */
  public Puzzle(Item item) {
    this.item = item;
    isSolved = false;
    isBeingWorkedOn = false;
    description = "A key is needed.";
    unlockMessage = "The key turns, the lock clicks. It is open.";
    lockedMessage = "The door does not unlock.";
  }


  public boolean checkIfSolved() {
    return isSolved;
  }

  public String getDescription() {
    return description;
  }

  /**
   * Sets the puzzle to solved after it is done right.
   *
   * @param item gets item
   * @return puzzle is solved right
   */
  public boolean setSolved(Item item) {
    if (item == this.item) {
      isSolved = true;
    }
    return isSolved;
  }

  public String getUnlockMessage() {
    return unlockMessage;
  }

  public String getLockedMessage() {
    return lockedMessage;
  }
}
