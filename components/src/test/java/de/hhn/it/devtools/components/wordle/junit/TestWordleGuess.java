package de.hhn.it.devtools.components.wordle.junit;

import de.hhn.it.devtools.components.wordle.provider.WordleGuess;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWordleGuess {

  @Test
  void shouldReturnString_whack() {
    WordleGuess wordleGuess = new WordleGuess("whack");
    assertEquals("whack", wordleGuess.getWordleGuessAsString());
  }

  @Test
  void shouldReturnString_legal() {
    WordleGuess wordleGuess = new WordleGuess("LeGaL");
    assertEquals("legal", wordleGuess.getWordleGuessAsString());
  }

  @Test
  void shouldReturnChar_b() {
    WordleGuess wordleGuess = new WordleGuess("zebra");
    assertEquals('b', wordleGuess.getLetterAtIndex(2));
  }

  @Test
  void shouldReturn_kitty() {
    WordleGuess wordleGuess = new WordleGuess("kebab");
    WordleGuess wordleGuess2 = new WordleGuess("kitty");
    wordleGuess.setWordleWord(wordleGuess2.getWordleWord());
    assertEquals("kitty", wordleGuess.getWordleGuessAsString());
  }

  @Test
  void shouldReturnChar_e() {
    WordleGuess wordleGuess = new WordleGuess("zEbra");
    assertEquals('e', wordleGuess.getLetterAtIndex(1));
  }

  @Test
  void setCharAtIndex3ToChar_g() {
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
