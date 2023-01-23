package de.hhn.it.devtools.components.wordle.junit;

import de.hhn.it.devtools.components.wordle.provider.WordleGuess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWordleGuess {

  @Test
  void shouldReturnStringWhack() {
    WordleGuess wordleGuess = new WordleGuess("whack");
    assertEquals("whack", wordleGuess.getWordleGuessAsString());
  }

  @Test
  void shouldReturnStringLegal() {
    WordleGuess wordleGuess = new WordleGuess("LeGaL");
    assertEquals("legal", wordleGuess.getWordleGuessAsString());
  }

  @Test
  void shouldReturnCharB() {
    WordleGuess wordleGuess = new WordleGuess("zebra");
    assertEquals('b', wordleGuess.getLetterAtIndex(2));
  }

  @Test
  void shouldReturnStringKittyAfterAlteredWordleGuess() {
    WordleGuess wordleGuess = new WordleGuess("kebab");
    wordleGuess.changeContentsOfWordlePanels("kitty");
    assertEquals("kitty", wordleGuess.getWordleGuessAsString());
  }

  @Test
  void shouldReturnCharE() {
    WordleGuess wordleGuess = new WordleGuess("zEbra");
    assertEquals('e', wordleGuess.getLetterAtIndex(1));
  }

  @Test
  void setCharAtIndex3ToCharG() {
    WordleGuess wordleGuess = new WordleGuess("zebra");
    wordleGuess.setLetterAtIndex(3,'g');
    assertEquals('g', wordleGuess.getLetterAtIndex(3));
  }

  @Test
  void deleteCharAtIndex4() {
    WordleGuess wordleGuess = new WordleGuess("zebra");
    wordleGuess.deleteLetterAtIndex(4);
    assertEquals(' ',wordleGuess.getLetterAtIndex(4));
  }
}
