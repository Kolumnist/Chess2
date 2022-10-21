package wordle;

import de.hhn.it.devtools.apis.wordle.IllegalGuessException;
import de.hhn.it.devtools.apis.wordle.WordleKeyListener;
import de.hhn.it.devtools.apis.wordle.WordleService;
import de.hhn.it.devtools.apis.wordle.WordlePanel;
import de.hhn.it.devtools.apis.wordle.WordleGuess;


public class WordleServiceUsageDemo {
  public static void main(String[] args) throws IllegalGuessException {

    WordleService demo = null;
    WordleKeyListener demoListener = null;

    demo.startGame(); // Player starts a new game

    demo.selectWordle(); // System selects a Wordle for the user to guess

    demo.validateWordleGuess(); // System validates the user's guess

    demo.validateWordleGuess(); // throws IllegalGuessException

    demo.validateWordleGuess();

    demo.startAnotherGame();

    demo.selectWordle(); // a new Wordle is selected for the user to guess

    demo.validateWordleGuess();
  }
}
