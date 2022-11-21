package de.hhn.it.devtools.apis.game2048;

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
   * contains the new Highscore.
   */
  private final int newHighscore;

  public State(Block[] blocksOnGameboard, int newCurrentHighscore, int newHighscore) {
    this.blocksOnGameboard = blocksOnGameboard;
    this.newCurrentHighscore = newCurrentHighscore;
    this.newHighscore = newHighscore;
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
}
