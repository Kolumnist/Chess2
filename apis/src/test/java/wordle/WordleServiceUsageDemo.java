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

    demo.showRules();

    demo.enterLetter(demoListener); // repeated until five letters are entered

    demo.validateWordleGuess(); // System validates the user's guess

    demo.compareGuessToWordleSolution(); // returns false

    demo.updateUiWithColours();

    demo.enterLetter(demoListener); // repeated four times

    demo.validateWordleGuess(); // throws IllegalGuessException

    demo.enterLetter(demoListener); // a fifth letter is entered

    demo.deleteLetter(demoListener); // fifth letter is deleted

    demo.enterLetter(demoListener); // another fifth letter is entered

    demo.validateWordleGuess();

    demo.compareGuessToWordleSolution(); //returns true

    demo.updateUiWithColours(); // all WordlePanels turn green

    demo.showVictoryScreen();

    demo.startAnotherGame();

    demo.selectWordle(); // a new Wordle is selected for the user to guess

    demo.enterLetter(demoListener); // repeated five times

    demo.validateWordleGuess();

    demo.compareGuessToWordleSolution(); //returns true

    demo.updateUiWithColours(); // all WordlePanels turn green

    demo.showVictoryScreen();

  }
}
