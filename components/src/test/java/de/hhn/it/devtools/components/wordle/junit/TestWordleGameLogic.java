package de.hhn.it.devtools.components.wordle.junit;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.wordle.IllegalGuessException;
import de.hhn.it.devtools.components.wordle.provider.WordleGameLogic;
import de.hhn.it.devtools.components.wordle.provider.WordleGuess;
import de.hhn.it.devtools.components.wordle.provider.WordlePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestWordleGameLogic {

  WordleGameLogic wordleGameLogic;
  @BeforeEach
  void init() {
    wordleGameLogic = new WordleGameLogic();
  }

  @RepeatedTest(10)
  void startAnotherGameShouldAssignPreviousSolutionAndReturnNotNull() {
    wordleGameLogic.startGame();
    wordleGameLogic.startAnotherGame();
    assertNotNull(wordleGameLogic.getCurrentWordleGame());
  }

  @Test
  void receiveAndComputeGuessShouldThrowExceptionWithOneWhitespace() throws IllegalGuessException {
    assertThrows(IllegalGuessException.class, () -> {
      wordleGameLogic.receiveAndComputeGuess("grac ");
    }
    );
  }

  @Test
  void receiveAndComputeGuessShouldRunNormally() throws IllegalGuessException {
    wordleGameLogic.startGame();
    WordleGuess expectedResult = new WordleGuess("grace");
    wordleGameLogic.receiveAndComputeGuess("grace");
    assertEquals(wordleGameLogic.getCurrentWordleGame().getPlayerGuesses()[0]
        .getWordleGuessAsString(), expectedResult.getWordleGuessAsString());
  }

  @Test
  void receiveAndComputeGuessShouldRunNormallyInAllCaps() throws IllegalGuessException {
    wordleGameLogic.startGame();
    WordleGuess expectedResult = new WordleGuess("grace");
    wordleGameLogic.receiveAndComputeGuess("GRACE");
    assertEquals(wordleGameLogic.getCurrentWordleGame().getPlayerGuesses()[0]
        .getWordleGuessAsString(), expectedResult.getWordleGuessAsString());
  }

  @Test
  void receiveAndComputeGuessShouldRunNormallyWithThreeCaps() throws IllegalGuessException {
    wordleGameLogic.startGame();
    WordleGuess expectedResult = new WordleGuess("grace");
    wordleGameLogic.receiveAndComputeGuess("gRaCE");
    assertEquals(wordleGameLogic.getCurrentWordleGame().getPlayerGuesses()[0]
        .getWordleGuessAsString(), expectedResult.getWordleGuessAsString());
  }

  @Test
  void receiveAndComputeGuessShouldThrowExceptionWithTwoWhitespaces() throws IllegalGuessException {
    assertThrows(IllegalGuessException.class, () -> {
          wordleGameLogic.receiveAndComputeGuess(" rac ");
        }
    );
  }

  @Test
  void receiveAndComputeGuessShouldThrowExceptionWithFiveWhitespaces()
      throws IllegalGuessException {
    assertThrows(IllegalGuessException.class, () -> {
          wordleGameLogic.receiveAndComputeGuess("     ");
        }
    );
  }

  @Test
  void guessNastyIsNotCorrectShouldReturnFalse() throws IllegalGuessException {
    wordleGameLogic.startGame();
    String mockSolution = "kebab";
    wordleGameLogic.receiveAndComputeGuess("nasty");
    assertNotEquals(mockSolution,
        wordleGameLogic.getCurrentWordleGame().getPlayerGuesses()[0]
        .getWordleGuessAsString());
  }

  @Test
  void guessKebabIsCorrectShouldReturnTrue() throws IllegalGuessException {
    wordleGameLogic.startGame();
    String mockSolution = "kebab";
    wordleGameLogic.receiveAndComputeGuess("kebab");
    assertEquals(mockSolution,
        wordleGameLogic.getCurrentWordleGame().getPlayerGuesses()[0]
            .getWordleGuessAsString());
  }
  @Test
  void guessKebabInAllCapsIsCorrectShouldReturnTrue() throws IllegalGuessException {
    wordleGameLogic.startGame();
    String mockSolution = "kebab";
    wordleGameLogic.receiveAndComputeGuess("KEBAB");
    assertEquals(mockSolution,
        wordleGameLogic.getCurrentWordleGame().getPlayerGuesses()[0]
            .getWordleGuessAsString());
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

  @Test
  void testGetPanelById() throws IllegalParameterException {
    wordleGameLogic.startGame();
    assertEquals(2, wordleGameLogic.getPanelById(2).getId());
  }
}
