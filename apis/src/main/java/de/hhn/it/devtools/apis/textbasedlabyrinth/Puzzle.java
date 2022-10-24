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
