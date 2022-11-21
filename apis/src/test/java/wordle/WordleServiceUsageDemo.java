package wordle;

import de.hhn.it.devtools.apis.wordle.IllegalGuessException;
import de.hhn.it.devtools.apis.wordle.WordleService;
import de.hhn.it.devtools.apis.wordle.WordleGuess;


public class WordleServiceUsageDemo {

  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(WordleServiceUsageDemo.class);

  public static void main(String[] args) throws IllegalGuessException {

    WordleService demo = null;

    demo.startGame(); // Player starts a new game

    demo.selectWordle(); // System selects a Wordle for the user to guess

    WordleGuess solution = new WordleGuess("Grace");
    logger.info("create WordleGuess with parameter: Grace");

    WordleGuess firstGuess = new WordleGuess("Cargo");
    logger.info("create WordleGuess with parameter: Cargo");

    demo.validateWordleGuess(firstGuess); // System validates the user's guess

    WordleGuess secondGuess = new WordleGuess("Grac");
    logger.info("create WordleGuess with parameter: Grac");

    demo.validateWordleGuess(secondGuess); // throws IllegalGuessException, because Guess is too short

    demo.validateWordleGuess(secondGuess); // Player adds 'e' to his Guess "Grace"
    logger.info("GuessWord is equal to solution"); // Player wins Game

    demo.startAnotherGame();

    demo.selectWordle(); // a new Wordle is selected for the user to guess

    WordleGuess secondSolution = new WordleGuess("Fairy");
    logger.info("New Solution is created");

    WordleGuess newGuess = new WordleGuess("Fairy");
    logger.info("create WordleGuess with parameter: Fairy");

    demo.validateWordleGuess(newGuess);
    logger.info("GuessWord is equal to solution"); // Player wins Game

    demo.quitGame();
  }
}
