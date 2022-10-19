package de.hhn.it.devtools.apis.text_based_labyrinth;

/**
 * Puzzle.
 */
public class Puzzle {


  private final int puzzleId;
  private boolean isSolved;
  private boolean isBeingWorkedOn;

  /**
   * Javadoc.
   *
   * @param id newID
   *
   */
  public Puzzle(int id) {
    puzzleId = id;
    isSolved = false;
    isBeingWorkedOn = false;
  }


  public boolean checkIfSolved() {
    return isSolved;
  }

  /**
   * Javadoc.
   */
  public void execute() {
    isBeingWorkedOn = true;
    boolean correctSolutionGiven = false;

    //
    //Code for execution.
    //
    // Wow, we found the correct solution!
    correctSolutionGiven = true;

    if (correctSolutionGiven) {
      isBeingWorkedOn = false;
      isSolved = true;
    }
  }

  public void setSolved(boolean solution) {
    isSolved = solution;
  }

  public int getPuzzleId() {
    return puzzleId;
  }
}
