package de.hhn.it.devtools.apis.game2048;

import java.util.Arrays;

/**
 * This Class contains all Data that can change in the frontend.
 */
public class State {
  /**
   * Contains all blocks on the field after something changed in components.
   */
  private final Block[] blocksOnGameboard;

  /**
   * contains the new Currentscore.
   */
  private final int newCurrentScore;

  /**
   * True if the Player merged two Blocks with {Value >= 2048}
   */
  private final boolean gameWon;

  /**
   *True if there are no free spaces to place a Block and
   * the Player made a move and did not free up space.
   */
  private final boolean gameLost;

  public State(Block[] blocksOnGameboard, int newCurrentScore, boolean gameWon, boolean gameLost) {
    this.blocksOnGameboard = blocksOnGameboard;
    this.newCurrentScore = newCurrentScore;
    this.gameWon = gameWon;
    this.gameLost = gameLost;
  }

  public Block[] getBlocksOnGameboard() {
    return blocksOnGameboard;
  }

  public int getCurrentScore() {
    return newCurrentScore;
  }

  public boolean isGameLost() {
    return gameLost;
  }

  public boolean isGameWon() {
    return gameWon;
  }

  @Override
  public String toString() {
    return "State{" +
           "blocksOnGameboard=" + Arrays.toString(blocksOnGameboard) +
           ", newCurrentHighscore=" + newCurrentScore +
           ", gameWon=" + gameWon +
           ", gameLost=" + gameLost +
           '}';
  }
}
