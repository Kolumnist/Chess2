package de.hhn.it.devtools.components.wordle.provider;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.wordle.IllegalGuessException;
import de.hhn.it.devtools.apis.wordle.State;
import de.hhn.it.devtools.apis.wordle.WordleGuessService;
import de.hhn.it.devtools.apis.wordle.WordlePanelListener;
import de.hhn.it.devtools.apis.wordle.WordlePanelService;
import de.hhn.it.devtools.apis.wordle.WordleService;


public class WordleGameLogic implements WordleService {

  private String currentWordleSolution;

  private static boolean wasStartGameCalled = false;
  private WordleGame currentWordleGame;
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(WordleGameLogic.class);
  @Override
  public void startGame() {
    String currentSolution = WordleSolutionSelector.selectWordle();
    setCurrentWordleSolution(currentSolution);
    currentWordleGame = new WordleGame();
    wasStartGameCalled = true;
    logger.info("Started new game, solution is: " + currentSolution);
  }

  @Override
  public void startAnotherGame() {
    if(!wasStartGameCalled) {
      startGame();
      logger.info("Method startAnotherGame was called before startGame");
    }
    else {
      String prevSolution = getCurrentWordleSolution();
      String newSolution = WordleSolutionSelector.selectWordle();
      if (!newSolution.equals(prevSolution)) {
        setCurrentWordleSolution(newSolution);
        currentWordleGame = new WordleGame();
        logger.info("Started another game, solution is: " + newSolution);
      } else {
        startAnotherGame();
      }
    }
  }

  /**
   * A method that is supposed to receive a String from the Frontend, enter said String into the
   * Array of WordleGuesses and compute whether the guess is correct. In any case, this method will
   * update the states of the WordlePanels regardless.
   *
   * @param stringGuess the String that the Frontend will deliver to the Backend
   * @return the current Array of WordleGuesses
   * @throws IllegalGuessException is thrown should the given guess not be long enough
   */
  public WordleGuess[] receiveAndComputeGuess(String stringGuess) throws IllegalGuessException {
    WordleGuess playersGuess = new WordleGuess(stringGuess);
    checkIfGuessIsLongEnough(playersGuess);
    WordleGuess[] currentWordleGuessArray = currentWordleGame.getPlayerGuesses();
    int currentWordleGameIndex = currentWordleGame.getWordleGuessIndex();
    WordleGuess currentWordleGuess = currentWordleGuessArray[currentWordleGameIndex];
    currentWordleGuess.changeContentsOfWordlePanels(stringGuess);
    checkIfGameIsFinished(currentWordleGuess);
    currentWordleGame.incrementWordleGuessIndex();
    logger.info("receiveAndComputeGuess ran without throwing an exception");
    return currentWordleGame.getPlayerGuesses();
  }

  /**
   * A method that checks whether the guess is entirely correct or only partially.
   *
   * @param guess The WordleGuess entered by the player
   * @return true if guess is equal to solution and false otherwise
   */
  public boolean checkIfGameIsFinished(WordleGuess guess){
    logger.info("checkIfGuessIsCorrect returned:" + checkIfGuessIsCorrect(guess));
    return checkIfGuessIsCorrect(guess) ||
        checkPanelsIndividually(guess);
  }


  /**
   * A method that checks if the WordleGuess is equal to the solution.
   *
   * @param guess The WordleGuess that the player entered
   * @return true if guess is equal to solution and false otherwise
   */
  public boolean checkIfGuessIsCorrect(WordleGuess guess) {
    if (guess.getWordleGuessAsString().equals(currentWordleSolution.toLowerCase())) {
      for (WordlePanelService panel : guess.getWordleWord()) {
        panel.setState(State.CORRECT);
        panel.notifyListeners(State.CORRECT);
        logger.info("checkIfGuessIsCorrect returns true");
        return true;
      }
    }
    logger.info("checkIfGuessIsCorrect returns false");
    return false;
  }

  /**
   * A method that checks if the given guess is long enough.
   *
   * @param guess The guess entered by the player
   * @throws IllegalGuessException is thrown if guess is not long enough
   */
  public void checkIfGuessIsLongEnough (WordleGuessService guess) throws IllegalGuessException { // try catch Block will be implemented in the Controller class
    for (WordlePanelService panel : guess.getWordleWord()) {
      if (panel.getLetter() == ' '){
        logger.info("checkIfGuessIsLongEnough will throw IllegalGuessException");
        throw new IllegalGuessException("Wordle guess does not contain five valid characters!");
      }

    }
  }

  /**
   * A method that checks and updates each WordlePanel within the players guess individually
   * according to game rules.
   *
   * @param guess The WordleGuess made by the player
   * @return always returns false since given guess is not the solution
   */
  public boolean checkPanelsIndividually(WordleGuess guess) {
    WordlePanelService[] wordlePanels = guess.getWordleWord();
    String enteredWordleGuess = guess.getWordleGuessAsString();
    for (int i = 0; i < guess.getWordleWord().length; i++) {
      if (enteredWordleGuess.charAt(i) == currentWordleSolution.charAt(i)) {
        wordlePanels[i].setState(State.CORRECT);
        wordlePanels[i].notifyListeners(State.CORRECT);
        logger.info("Panel is correct:" + wordlePanels[i].getLetter());
      } else if (currentWordleSolution.contains(Character.toString(enteredWordleGuess.charAt(i)))) {
        wordlePanels[i].setState(State.PARTIALLY_CORRECT);
        wordlePanels[i].notifyListeners(State.PARTIALLY_CORRECT);
        logger.info("Panel is partially correct:" + wordlePanels[i].getLetter());
      } else {
        wordlePanels[i].setState(State.FALSE);
        wordlePanels[i].notifyListeners(State.FALSE);
        logger.info("Panel is false:" + wordlePanels[i].getLetter());
      }
    }
    return false;
  }

  @Override
  public void quitGame() {
  }

  @Override
  public void addCallback(WordlePanelListener listener, WordlePanelService panel)
      throws IllegalParameterException {
    logger.info("addCallback: id = {}, listener = {}", panel.getId(), listener);
    panel.addCallback(listener);
  }

  @Override
  public void removeCallback(WordlePanelListener listener, WordlePanelService panel)
      throws IllegalParameterException {
    logger.info("removeCallback: id = {}, listener = {}", panel.getId(), listener);
    panel.removeCallback(listener);
  }

  public void setCurrentWordleSolution(String currentWordleSolution) {
    this.currentWordleSolution = currentWordleSolution;
    logger.debug("CurrentWordleSolution is now: " + currentWordleSolution);
  }
  public String getCurrentWordleSolution() {
    logger.debug("getCurrentWordleSolution returns: " + currentWordleSolution);
    return currentWordleSolution;
  }

  public boolean getWasStartGameCalled() {
    return wasStartGameCalled;
  }
  public void setWasStartGameCalled(boolean wasStartGameCalled) {
    WordleGameLogic.wasStartGameCalled = wasStartGameCalled;
  }

  public WordleGame getCurrentWordleGame() {
    return currentWordleGame;
  }
}
