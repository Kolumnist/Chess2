package de.hhn.it.devtools.apis.wordle;

public interface Wordle {

  void startGame();

  String selectWordle();

  boolean validateWordleGuess();

  void compareGuessToWorldeSolution();

  void updateUiWithGuess();

  void showVictoryScreen();

  void showDefeatScreen();

  void showRules();

  void quitGame();



}
