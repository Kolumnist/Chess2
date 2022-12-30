package de.hhn.it.devtools.components.wordle.junit;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.wordle.IllegalGuessException;
import de.hhn.it.devtools.apis.wordle.State;
import de.hhn.it.devtools.apis.wordle.WordlePanelService;
import de.hhn.it.devtools.components.wordle.provider.WordleGameLogic;
import de.hhn.it.devtools.components.wordle.provider.WordleGuess;
import de.hhn.it.devtools.components.wordle.provider.WordlePanel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestWordleGameLogic {

  @Test
  void startGameShouldAssignCurrentSolutionAndReturnNotNull() {
    WordleGameLogic wordleGameLogic = new WordleGameLogic();
    wordleGameLogic.startGame();
    assertNotNull(wordleGameLogic.getCurrentWordleSolution());
  }

  @Test
  void startAnotherGameShouldAssignPreviousSolutionAndReturnNotNull() {
    WordleGameLogic wordleGameLogic = new WordleGameLogic();
    wordleGameLogic.startGame();
    wordleGameLogic.startAnotherGame();
    assertNotNull(wordleGameLogic.getPreviousWordleSolution());
  }

  @Test
  void startAnotherGameShouldThrowNullPointerException() {
    WordleGameLogic wordleGameLogic = new WordleGameLogic();
    NullPointerException exception = assertThrows(NullPointerException.class,
        wordleGameLogic::startAnotherGame);
  }
  @Test
  void checkIfGuessIsLongEnoughShouldThrowIllegalGuessExceptionWithOneWhitespace() throws IllegalGuessException {
    WordleGameLogic wordleGameLogic = new WordleGameLogic();
    WordleGuess wordleGuess = new WordleGuess("keba ");
    Throwable exception = assertThrows(
       IllegalGuessException.class, () -> {
          wordleGameLogic.checkIfGuessIsLongEnough(wordleGuess);
        }
    );
  }

  @Test
  void checkIfGuessIsLongEnoughShouldThrowIllegalGuessExceptionWithFiveWhitespaces() throws IllegalGuessException {
    WordleGameLogic wordleGameLogic = new WordleGameLogic();
    WordleGuess wordleGuess = new WordleGuess("     ");
    Throwable exception = assertThrows(
        IllegalGuessException.class, () -> {
          wordleGameLogic.checkIfGuessIsLongEnough(wordleGuess);
        }
    );
  }

  @Test
  void checkIfGuessIsLongEnoughShouldNotThrowIllegalGuessException() {
    WordleGameLogic wordleGameLogic = new WordleGameLogic();
    WordleGuess wordleGuess = new WordleGuess("kebab");
    assertDoesNotThrow(() -> wordleGameLogic.checkIfGuessIsLongEnough(wordleGuess));
  }

  @Test
  void guess_kebab_IsCorrectShouldReturnTrue() {
    WordleGameLogic wordleGameLogic = new WordleGameLogic();
    wordleGameLogic.setCurrentWordleSolution("kebab");
    WordleGuess wordleGuess = new WordleGuess("kebab");
    assertTrue(wordleGameLogic.checkIfGuessIsCorrect(wordleGuess.getWordleGuessAsString(), wordleGuess));
  }

  @Test
  void guess_KeBaB_IsCorrectShouldReturnTrue() {
    WordleGameLogic wordleGameLogic = new WordleGameLogic();
    wordleGameLogic.setCurrentWordleSolution("kebab");
    WordleGuess wordleGuess = new WordleGuess("KeBaB");
    assertTrue(wordleGameLogic.checkIfGuessIsCorrect(wordleGuess.getWordleGuessAsString(), wordleGuess));
  }

  @Test
  void guess_nasty_IsNotCorrectShouldReturnFalse() {
    WordleGameLogic wordleGameLogic = new WordleGameLogic();
    wordleGameLogic.setCurrentWordleSolution("kebab");
    WordleGuess wordleGuess = new WordleGuess("nasty");
    assertFalse(wordleGameLogic.checkIfGuessIsCorrect(wordleGuess.getWordleGuessAsString(), wordleGuess));
  }

  @Test
  void guess_kebxb_IsNotCorrectShouldReturnFalse() {
    WordleGameLogic wordleGameLogic = new WordleGameLogic();
    wordleGameLogic.setCurrentWordleSolution("kebab");
    WordleGuess wordleGuess = new WordleGuess("kebxb");
    assertFalse(wordleGameLogic.checkIfGuessIsCorrect(wordleGuess.getWordleGuessAsString(), wordleGuess));
  }

  @Test
  void guess_kebab_IsEntirelyCorrectShouldReturnTrue() {
    WordleGameLogic wordleGameLogic = new WordleGameLogic();
    wordleGameLogic.setCurrentWordleSolution("kebab");
    WordleGuess wordleGuess = new WordleGuess("kebab");
    assertTrue(wordleGameLogic.checkIfGameIsFinished(wordleGuess));
  }

  @Test
  void guess_KeBAb_IsEntirelyCorrectShouldReturnTrue() {
    WordleGameLogic wordleGameLogic = new WordleGameLogic();
    wordleGameLogic.setCurrentWordleSolution("kebab");
    WordleGuess wordleGuess = new WordleGuess("KeBAb");
    assertTrue(wordleGameLogic.checkIfGameIsFinished(wordleGuess));
  }

  @Test
  void guess_kebas_IsNotEntirelyCorrectShouldReturnFalse() {
    WordleGameLogic wordleGameLogic = new WordleGameLogic();
    wordleGameLogic.setCurrentWordleSolution("kebab");
    WordleGuess wordleGuess = new WordleGuess("kebas");
    assertFalse(wordleGameLogic.checkIfGameIsFinished(wordleGuess));
  }

  @Test
  void testStatesAfterCheckPanelsIndividuallyCall() {
    WordleGameLogic testLogic = new WordleGameLogic();
    testLogic.setCurrentWordleSolution("cargo");
    WordleGuess testGuess = new WordleGuess("grace");
    testLogic.checkPanelsIndividually(testGuess);
    WordlePanelService [] wordlePanels = testGuess.getWordleWord();
    assertTrue((wordlePanels[0].getState() == State.PARTIALLY_CORRECT) &&
        wordlePanels[1].getState() == State.PARTIALLY_CORRECT &&
        wordlePanels[2].getState() == State.PARTIALLY_CORRECT &&
        wordlePanels[3].getState() == State.PARTIALLY_CORRECT &&
        wordlePanels[4].getState() == State.FALSE);
  }

  @Test
  void testReturnValOfCheckPanelsIndividually() {
    WordleGameLogic testLogic = new WordleGameLogic();
    testLogic.setCurrentWordleSolution("havoc");
    WordleGuess testGuess = new WordleGuess("lithe");
    assertFalse(testLogic.checkPanelsIndividually(testGuess));
  }

  @Test
  void testAddCallbackWithNullListener() {
    WordleGameLogic testLogic = new WordleGameLogic();
    WordlePanel testPanel = new WordlePanel('j');
    Throwable exception = assertThrows(
        IllegalParameterException.class, () -> testLogic.addCallback(null, testPanel)
    );
    assertTrue(exception.getMessage().contentEquals("Listener was null reference."));
  }
  @Test
  void testAddCallbackWithAlreadyAddedListener() throws IllegalParameterException{
    WordleGameLogic testLogic = new WordleGameLogic();
    WordlePanel testPanel = new WordlePanel('i');
    TestListener testListener = new TestListener();
    testLogic.addCallback(testListener, testPanel);
    Throwable exception = assertThrows(
        IllegalParameterException.class, () -> testLogic.addCallback(testListener, testPanel)
    );
    assertTrue(exception.getMessage().contentEquals("Listener already registered."));
  }

  @Test
  void testAddCallbackShouldBeSuccessful() {
    WordleGameLogic testLogic = new WordleGameLogic();
    WordlePanel testPanel = new WordlePanel('n');
    TestListener testListener = new TestListener();
    assertDoesNotThrow(() -> testLogic.addCallback(testListener, testPanel));
  }

  @Test
  void testRemoveCallbackWithNullListener() {
    WordleGameLogic testLogic = new WordleGameLogic();
    WordlePanel testPanel = new WordlePanel('x');
    Throwable exception = assertThrows(
        IllegalParameterException.class, () -> testLogic.removeCallback(null, testPanel)
    );
    assertTrue(exception.getMessage().contentEquals("Listener was null reference."));
  }
  @Test
  void testRemoveCallbackWithUnregisteredListener() {
    WordleGameLogic testLogic = new WordleGameLogic();
    WordlePanel testPanel = new WordlePanel('e');
    TestListener testListener = new TestListener();
    Throwable exception = assertThrows(
        IllegalParameterException.class, () -> testLogic.removeCallback(testListener, testPanel)
    );
    assertTrue(exception.getMessage().contentEquals("Listener is not registered:"
        + testListener));
  }

  @Test
  void testRemoveCallbackShouldBeSuccessful() throws IllegalParameterException {
    WordleGameLogic testLogic = new WordleGameLogic();
    WordlePanel testPanel = new WordlePanel('d');
    TestListener testListener = new TestListener();
    testLogic.addCallback(testListener, testPanel);
    assertDoesNotThrow(() -> testLogic.removeCallback(testListener, testPanel));
  }

  @Test
  void testGetterAndSetterForPreviousWordleSolution() {
    WordleGameLogic testLogic = new WordleGameLogic();
    testLogic.setPreviousWordleSolution("grace");
    assertSame("grace", testLogic.getPreviousWordleSolution());
  }

  @Test
  void testGetterAndSetterForCurrentWordleSolution() {
    WordleGameLogic testLogic = new WordleGameLogic();
    testLogic.setCurrentWordleSolution("havoc");
    assertSame("havoc", testLogic.getCurrentWordleSolution());
  }
}
