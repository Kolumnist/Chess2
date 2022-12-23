package de.hhn.it.devtools.apis.candycrush;

public interface CandyCrushListener {



  void updateBlockGrid(BlockGrid blockGrid);

  void updateGameState(GameState gameState);

  void updateProfile(Profile profile);

  void showStats(Profile profile);




}
