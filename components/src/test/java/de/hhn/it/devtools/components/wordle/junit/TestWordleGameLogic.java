package de.hhn.it.devtools.components.wordle.junit;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.wordle.IllegalGuessException;
import de.hhn.it.devtools.apis.wordle.State;
import de.hhn.it.devtools.apis.wordle.WordlePanelService;
import de.hhn.it.devtools.components.wordle.provider.WordleGameLogic;
import de.hhn.it.devtools.components.wordle.provider.WordleGuess;
import de.hhn.it.devtools.components.wordle.provider.WordlePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestWordleGameLogic {

  WordleGameLogic wordleGameLogic;
  @BeforeEach
  void init() {
    wordleGameLogic = new WordleGameLogic();
    wordleGameLogic.setWasStartGameCalled(false);
  } 

  @Test
  void startAnotherGameShouldCallStartGame() {
    wordleGameLogic.startAnotherGame();
    assertTrue(wordleGameLogic.getWasStartGameCalled());
  }

  @Test
  void startGameShouldAssignCurrentSolutionAndReturnNotNull() {
    wordleGameLogic.startGame();
    assertNotNull(wordleGameLogic.getCurrentWordleSolution());
  }

  @RepeatedTest(10)
  void startAnotherGameShouldAssignPreviousSolutionAndReturnNotNull() {
    wordleGameLogic.startGame();
    wordleGameLogic.startAnotherGame();
    assertNotNull(wordleGameLogic.getPreviousWordleSolution());
  }

  @Test
  void checkIfGuessIsLongEnoughShouldThrowIllegalGuessExceptionWithOneWhitespace()
  throws IllegalGuessException {
    WordleGuess wordleGuess = new WordleGuess("keba ");
    assertThrows(IllegalGuessException.class, () -> {
      wordleGameLogic.checkIfGuessIsLongEnough(wordleGuess);
    }
    );
  }

  @Test
  void checkIfGuessIsLongEnoughShouldThrowIllegalGuessExceptionWithFiveWhitespaces()
  throws IllegalGuessException {
    WordleGuess wordleGuess = new WordleGuess("     ");
    assertThrows(IllegalGuessException.class, () -> {
      wordleGameLogic.checkIfGuessIsLongEnough(wordleGuess);
    }
    );
  }

  @Test
  void checkIfGuessIsLongEnoughShouldNotThrowIllegalGuessException() {
    WordleGuess wordleGuess = new WordleGuess("kebab");
    assertDoesNotThrow(() -> wordleGameLogic.checkIfGuessIsLongEnough(wordleGuess));
  }

  @Test
  void guessKebabIsCorrectShouldReturnTrue() {
    wordleGameLogic.setCurrentWordleSolution("kebab");
    WordleGuess wordleGuess = new WordleGuess("kebab");
    assertTrue(wordleGameLogic.checkIfGuessIsCorrect(wordleGuess));
  }

  @Test
  void guessKeBaBIsCorrectShouldReturnTrue() {
    wordleGameLogic.setCurrentWordleSolution("kebab");
    WordleGuess wordleGuess = new WordleGuess("KeBaB");
    assertTrue(wordleGameLogic.checkIfGuessIsCorrect(wordleGuess));
  }

  @Test
  void guessNastyIsNotCorrectShouldReturnFalse() {
    wordleGameLogic.setCurrentWordleSolution("kebab");
    WordleGuess wordleGuess = new WordleGuess("nasty");
    assertFalse(wordleGameLogic.checkIfGuessIsCorrect(wordleGuess));
  }

  @Test
  void guessKebxbIsNotCorrectShouldReturnFalse() {
    wordleGameLogic.setCurrentWordleSolution("kebab");
    WordleGuess wordleGuess = new WordleGuess("kebxb");
    assertFalse(wordleGameLogic.checkIfGuessIsCorrect(wordleGuess));
  }

  @Test
  void guessKebabIsEntirelyCorrectShouldReturnTrue() {
    wordleGameLogic.setCurrentWordleSolution("kebab");
    WordleGuess wordleGuess = new WordleGuess("kebab");
    assertTrue(wordleGameLogic.checkIfGameIsFinished(wordleGuess));
  }

  @Test
  void guessKeBAbIsEntirelyCorrectShouldReturnTrue() {
    wordleGameLogic.setCurrentWordleSolution("kebab");
    WordleGuess wordleGuess = new WordleGuess("KeBAb");
    assertTrue(wordleGameLogic.checkIfGameIsFinished(wordleGuess));
  }

  @Test
  void guessKebasIsNotEntirelyCorrectShouldReturnFalse() {
    wordleGameLogic.setCurrentWordleSolution("kebab");
    WordleGuess wordleGuess = new WordleGuess("kebas");
    assertFalse(wordleGameLogic.checkIfGameIsFinished(wordleGuess));
  }

  @Test
  void testStatesAfterCheckPanelsIndividuallyCall() {
    wordleGameLogic.setCurrentWordleSolution("cargo");
    WordleGuess testGuess = new WordleGuess("grace");
    wordleGameLogic.checkPanelsIndividually(testGuess);
    WordlePanelService [] wordlePanels = testGuess.getWordleWord();
    assertTrue((wordlePanels[0].getState() == State.PARTIALLY_CORRECT) &&
        wordlePanels[1].getState() == State.PARTIALLY_CORRECT &&
        wordlePanels[2].getState() == State.PARTIALLY_CORRECT &&
        wordlePanels[3].getState() == State.PARTIALLY_CORRECT &&
        wordlePanels[4].getState() == State.FALSE);
  }

  @Test
  void testReturnValOfCheckPanelsIndividually() {
    wordleGameLogic.setCurrentWordleSolution("havoc");
    WordleGuess testGuess = new WordleGuess("lithe");
    assertFalse(wordleGameLogic.checkPanelsIndividually(testGuess));
  }

  @Test
  void testAddCallbackWithNullListener() {
    WordlePanel testPanel = new WordlePanel('j');
    Throwable exception = assertThrows(
        IllegalParameterException.class, () -> wordleGameLogic.addCallback(null, testPanel)
    );
    assertTrue(exception.getMessage().contentEquals("Listener was null reference."));
  }
  @Test
  void testAddCallbackWithAlreadyAddedListener() throws IllegalParameterException{
    WordlePanel testPanel = new WordlePanel('i');
    TestListener testListener = new TestListener();
    wordleGameLogic.addCallback(testListener, testPanel);
    Throwable exception = assertThrows(
        IllegalParameterException.class, () -> wordleGameLogic.addCallback(testListener, testPanel)
    );
    assertTrue(exception.getMessage().contentEquals("Listener already registered."));
  }

  @Test
  void testAddCallbackShouldBeSuccessful() {
    WordlePanel testPanel = new WordlePanel('n');
    TestListener testListener = new TestListener();
    assertDoesNotThrow(() -> wordleGameLogic.addCallback(testListener, testPanel));
  }

  @Test
  void testRemoveCallbackWithNullListener() {
    WordlePanel testPanel = new WordlePanel('x');
    Throwable exception = assertThrows(
        IllegalParameterException.class, () -> wordleGameLogic.removeCallback(null, testPanel)
    );
    assertTrue(exception.getMessage().contentEquals("Listener was null reference."));
  }
  @Test
  void testRemoveCallbackWithUnregisteredListener() {
    WordlePanel testPanel = new WordlePanel('e');
    TestListener testListener = new TestListener();
    Throwable exception = assertThrows(
        IllegalParameterException.class, () -> wordleGameLogic.removeCallback(testListener, testPanel)
    );
    assertTrue(exception.getMessage().contentEquals("Listener is not registered:"
        + testListener));
  }

  @Test
  void testRemoveCallbackShouldBeSuccessful() throws IllegalParameterException {
    WordlePanel testPanel = new WordlePanel('d');
    TestListener testListener = new TestListener();
    wordleGameLogic.addCallback(testListener, testPanel);
    assertDoesNotThrow(() -> wordleGameLogic.removeCallback(testListener, testPanel));
  }
}
