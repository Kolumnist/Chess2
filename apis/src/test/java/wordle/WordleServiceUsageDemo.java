package wordle;

import de.hhn.it.devtools.apis.wordle.IllegalGuessException;
import de.hhn.it.devtools.apis.wordle.WordleGuessService;
import de.hhn.it.devtools.apis.wordle.WordlePanelService;
import de.hhn.it.devtools.apis.wordle.WordleService;



public class WordleServiceUsageDemo {

  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(WordleServiceUsageDemo.class);

  public static void main(String[] args) throws IllegalGuessException {

    WordleGuessService wordleGuessService = null;
    WordlePanelService wordlePanelService = null;
    WordleService demo = null;

    demo.startGame(); // Player starts a new game

    // Static component selects a solution for the user to guess

    WordleGuessService solution = null;
    solution.setLetterAtIndex(0, 'G'); // repeated until grace is entered

    logger.info("create WordleGuess with parameter: Grace");

    WordleGuessService firstGuess = null;
    firstGuess.setLetterAtIndex(0, 'C'); // repeated until grace is entered
    logger.info("create WordleGuess with parameter: Cargo");

    demo.validateWordleGuess(firstGuess); // System validates the user's guess

    WordleGuessService secondGuess = null; // repeated until grac is entered
    secondGuess.setLetterAtIndex(0, 'G');
    logger.info("create WordleGuess with parameter: Grac");

    demo.validateWordleGuess(secondGuess); // throws IllegalGuessException, because Guess is too short

    demo.validateWordleGuess(secondGuess); // Player adds 'e' to his Guess "Grace"
    logger.info("GuessWord is equal to solution"); // Player wins Game

    demo.startAnotherGame();

    // Static component selects a new solution for the user to guess

    WordleGuessService secondSolution = null;
    secondSolution.setLetterAtIndex(0, 'F'); // repeated until Fairy is entered
    logger.info("New Solution is created");

    WordleGuessService newGuess = null;
    newGuess.setLetterAtIndex(0, 'F'); // repeated until Fairy is entered
    logger.info("create WordleGuess with parameter: Fairy");

    demo.validateWordleGuess(newGuess);
    logger.info("GuessWord is equal to solution"); // Player wins Game

    demo.quitGame();
  }
}
