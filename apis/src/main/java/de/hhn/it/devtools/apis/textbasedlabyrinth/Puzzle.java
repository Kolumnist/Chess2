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


  public Puzzle(Item item) {
    this.item = item;
    isSolved = false;
    isBeingWorkedOn = false;
    description = "A key is needed.";
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

}
