package de.hhn.it.devtools.components.wordle.junit;

import de.hhn.it.devtools.apis.wordle.IllegalGuessException;
import de.hhn.it.devtools.components.wordle.provider.WordleGameLogic;
import de.hhn.it.devtools.components.wordle.provider.WordleGuess;
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
    assertTrue(wordleGameLogic.checkIfGuessIsEqualToSolution(wordleGuess));
  }

  @Test
  void guess_KeBAb_IsEntirelyCorrectShouldReturnTrue() {
    WordleGameLogic wordleGameLogic = new WordleGameLogic();
    wordleGameLogic.setCurrentWordleSolution("kebab");
    WordleGuess wordleGuess = new WordleGuess("KeBAb");
    assertTrue(wordleGameLogic.checkIfGuessIsEqualToSolution(wordleGuess));
  }

  @Test
  void guess_kebas_IsNotEntirelyCorrectShouldReturnFalse() {
    WordleGameLogic wordleGameLogic = new WordleGameLogic();
    wordleGameLogic.setCurrentWordleSolution("kebab");
    WordleGuess wordleGuess = new WordleGuess("kebas");
    assertFalse(wordleGameLogic.checkIfGuessIsEqualToSolution(wordleGuess));
  }
}
