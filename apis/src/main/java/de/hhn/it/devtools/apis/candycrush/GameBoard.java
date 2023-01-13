package de.hhn.it.devtools.apis.candycrush;

public class GameBoard {

  private Profile profile;


  private final GameMode gameMode;
  private BlockGrid blockGrid;

  public GameBoard(Profile profile, GameMode gameMode) {
    this.profile = profile;
    this.gameMode = gameMode;
    blockGrid = new BlockGrid();
    generateCandyCrush();
  }

  private void generateCandyCrush() {
  }


  GameBoard createGame(Profile profile, GameMode gameMode)
      throws Exception {
    return null;
  }
}
