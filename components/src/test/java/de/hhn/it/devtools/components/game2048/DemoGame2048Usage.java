package de.hhn.it.devtools.components.game2048;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.game2048.Game2048Listener;
import de.hhn.it.devtools.apis.game2048.Game2048Service;
import de.hhn.it.devtools.apis.game2048.MovingDirection;
import de.hhn.it.devtools.apis.game2048.State;

public class DemoGame2048Usage {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(DemoGame2048Usage.class);

  public static void main(String[] args) throws IllegalParameterException {
    State placeHolderState = null;
    Game2048Service game2048Service = new Game2048Service() {
      @Override
      public void initialisation() {
        logger.info("A new game got started");
      }

      @Override
      public void moveAllBlocks(MovingDirection direction) {
        logger.info("All Blocks got moved to the [ " + direction + " ] boarder");
      }

      @Override
      public void addCallback(Game2048Listener listener) {
        logger.info("When the Application gets started, a Listener will be added " +
                    "too ensure that the Frontend (Package javafx) gets informed, how the new " +
                    "Data (game-board, current Score and high Score) look like.");
      }

      @Override
      public void removeCallback(Game2048Listener listener) {
        logger.info("If the User closes the Application the Listener will be removed");
        logger.info("The Listener got removed");
      }
    };
    Game2048Listener game2048Listener = new Game2048Listener() {
      @Override
      public void newState(State state) {
        logger.info("The new State (information about game-board, current Score and high Score) got send to the Frontend");
      }
    };
    game2048Service.addCallback(game2048Listener);

    game2048Service.initialisation();
    game2048Listener.newState(placeHolderState);
    game2048Service.moveAllBlocks(MovingDirection.right);
    game2048Listener.newState(placeHolderState);
    game2048Service.moveAllBlocks(MovingDirection.up);
    game2048Listener.newState(placeHolderState);
    game2048Service.moveAllBlocks(MovingDirection.down);
    game2048Listener.newState(placeHolderState);
    game2048Service.moveAllBlocks(MovingDirection.left);
    game2048Listener.newState(placeHolderState);

    logger.info("A couple of moves later, the game is won and the player starts a new Game");

    game2048Service.initialisation();
    game2048Listener.newState(placeHolderState);
    logger.info("Now the prozess repeats itself until the player closes the Application");
    game2048Service.removeCallback(game2048Listener);
  }
}
