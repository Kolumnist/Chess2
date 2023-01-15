package de.hhn.it.devtools.components.wordle.junit;

import de.hhn.it.devtools.components.wordle.provider.WordleGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class TestWordleGame {

  WordleGame testGame;

  @BeforeEach
  void init(){
    testGame = new WordleGame();
  }

  @Test
  void testWordleGameConstructor() {
    assertNotNull(testGame.getPlayerGuesses());
  }

  @Test
  void testIncrementGuessIndex() {
    testGame.incrementWordleGuessIndex();
    assertEquals(1,testGame.getWordleGuessIndex());
  }

}
