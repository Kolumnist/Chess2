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
  private final int newCurrentHighscore;

  /**
   * True if the Player merged two Blocks with {Value >= 2048}
   */
  private final boolean gameWon;

  /**
   *True if there are no free spaces to place a Block and
   * the Player made a move and did not free up space.
   */
  private final boolean gameLost;

  /**
   * contains the new Highscore.
   */
  private final int newHighscore;

  public State(Block[] blocksOnGameboard, int newCurrentHighscore, int newHighscore, boolean gameWon, boolean gameLost) {
    this.blocksOnGameboard = blocksOnGameboard;
    this.newCurrentHighscore = newCurrentHighscore;
    this.newHighscore = newHighscore;
    this.gameWon = gameWon;
    this.gameLost = gameLost;
  }

  public Block[] getBlocksOnGameboard() {
    return blocksOnGameboard;
  }

  public int getNewCurrentHighscore() {
    return newCurrentHighscore;
  }

  public int getNewHighscore() {
    return newHighscore;
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
           ", newCurrentHighscore=" + newCurrentHighscore +
           ", gameWon=" + gameWon +
           ", gameLost=" + gameLost +
           ", newHighscore=" + newHighscore +
           '}';
  }
}
