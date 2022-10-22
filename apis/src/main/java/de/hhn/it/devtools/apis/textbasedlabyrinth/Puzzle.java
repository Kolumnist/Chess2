package de.hhn.it.devtools.apis.textbasedlabyrinth;

/**
 * Puzzle.
 */
public class Puzzle {


  //private final int puzzleId;
  private boolean isSolved;
  private boolean isBeingWorkedOn;
  private Item item;


  public Puzzle(Item item) {
    this.item = item;
    isSolved = false;
    isBeingWorkedOn = false;
  }


  public boolean checkIfSolved() {
    return isSolved;
  }




  public boolean setSolved(Item item) {
    if (item == this.item) {
      isSolved = true;
    }
    return isSolved;
  }

}
