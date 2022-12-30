package de.hhn.it.devtools.components.wordle.junit;

import de.hhn.it.devtools.apis.wordle.IllegalGuessException;
import de.hhn.it.devtools.components.wordle.provider.WordleGame;
import de.hhn.it.devtools.components.wordle.provider.WordleGameLogic;
import de.hhn.it.devtools.components.wordle.provider.WordleGuess;
import de.hhn.it.devtools.components.wordle.provider.WordlePanel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;


public class TestWordleGame {

  @Test
  void testWordleGameConstructor() {
    WordleGameLogic testLogic = new WordleGameLogic();
    WordleGame testGame = new WordleGame("grace", testLogic);
    assertSame(testLogic, testGame.getBackend());
    assertSame("grace", testGame.getSolution());
    assertNotNull(testGame.getPlayerGuesses());
  }

  @Test
  void testTypeLetter() {
    WordleGameLogic testLogic = new WordleGameLogic();
    WordleGame testGame = new WordleGame("peace", testLogic);
    testGame.typeLetter('g');
    testGame.typeLetter('r');
    testGame.typeLetter('a');
    testGame.typeLetter('c');
    testGame.typeLetter('e');
    testGame.typeLetter('y'); // should NOT be added
    WordleGuess[] guesses = testGame.getPlayerGuesses();
    assertSame('g', guesses[0].getLetterAtIndex(0));
    assertSame('r', guesses[0].getLetterAtIndex(1));
    assertSame('a', guesses[0].getLetterAtIndex(2));
    assertSame('c', guesses[0].getLetterAtIndex(3));
    assertSame('e', guesses[0].getLetterAtIndex(4));
    assertSame(4, testGame.getWordlePanelIndex());
  }

  @Test
  void testDeleteLetter(){
    WordleGameLogic testLogic = new WordleGameLogic();
    WordleGame testGame = new WordleGame("havoc", testLogic);
    testGame.typeLetter('l');
    testGame.typeLetter('i');
    testGame.typeLetter('t');
    testGame.typeLetter('h');
    testGame.typeLetter('e');
    testGame.typeLetter('z'); // should NOT be added
    testGame.deleteLetter();
    testGame.deleteLetter();
    testGame.deleteLetter();
    testGame.deleteLetter();
    testGame.deleteLetter();
    testGame.deleteLetter(); // "additional" delete
    WordleGuess[] guesses = testGame.getPlayerGuesses();
    assertSame(' ', guesses[0].getLetterAtIndex(0));
    assertSame(' ', guesses[0].getLetterAtIndex(1));
    assertSame(' ', guesses[0].getLetterAtIndex(2));
    assertSame(' ', guesses[0].getLetterAtIndex(3));
    assertSame(' ', guesses[0].getLetterAtIndex(4));
    assertSame(0, testGame.getWordlePanelIndex());
  }
  @Test
  void testSubmitGuess() throws IllegalGuessException {
    WordleGameLogic testLogic = new WordleGameLogic();
    testLogic.setCurrentWordleSolution("swear");
    WordleGame testGame = new WordleGame("swear", testLogic);
    testGame.typeLetter('p');
    testGame.typeLetter('e');
    testGame.typeLetter('a');
    testGame.typeLetter('r');
    testGame.typeLetter('l');
    testGame.submitGuess();
    testGame.typeLetter('s');
    testGame.typeLetter('t');
    testGame.typeLetter('e');
    testGame.typeLetter('a');
    testGame.typeLetter('k');
    WordleGuess[] guesses = testGame.getPlayerGuesses();
    assertSame(1, testGame.getWordleGuessIndex());
    assertSame('p', guesses[0].getLetterAtIndex(0));
    assertSame('e', guesses[0].getLetterAtIndex(1));
    assertSame('a', guesses[0].getLetterAtIndex(2));
    assertSame('r', guesses[0].getLetterAtIndex(3));
    assertSame('l', guesses[0].getLetterAtIndex(4));
    assertSame('s', guesses[1].getLetterAtIndex(0));
    assertSame('t', guesses[1].getLetterAtIndex(1));
    assertSame('e', guesses[1].getLetterAtIndex(2));
    assertSame('a', guesses[1].getLetterAtIndex(3));
    assertSame('k', guesses[1].getLetterAtIndex(4));
    assertSame(4, testGame.getWordlePanelIndex());
  }


}
