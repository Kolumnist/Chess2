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
    WordleGame testGame = new WordleGame();
    assertNotNull(testGame.getPlayerGuesses());
  }
}
