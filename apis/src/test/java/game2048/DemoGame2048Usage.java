package game2048;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.game2048.Game2048Listener;
import de.hhn.it.devtools.apis.game2048.Game2048Service;
import de.hhn.it.devtools.apis.game2048.MovingDirection;
import de.hhn.it.devtools.apis.game2048.State;

/**
 * This usage demo is not runnable because in this module there is no possibility to access the
 * implementation. The runnable demo is accessible in the component's module.
 */
public class DemoGame2048Usage {

    public static void main(String[] args) throws IllegalParameterException {
        State placeHolderState = null;
        Game2048Service game2048Service = new Game2048Service() {
            @Override
            public void initialisation() {
                System.out.println("A new game got started");
            }

            @Override
            public void moveBlocks(MovingDirection direction) throws IllegalParameterException {
                System.out.println("All Blocks got moved to the [ " + direction + " ] boarder");
            }

            @Override
            public void addCallback(Game2048Listener listener) throws IllegalParameterException {
                System.out.println("When the Application gets started, a Listener will be added " +
                        "too ensure that the Frontend (Package javafx) gets informed, how the new " +
                        "Data (game-board, current Score and high Score) look like.");
            }

            @Override
            public void removeCallback(Game2048Listener listener) throws IllegalParameterException {
                System.out.println("If the User closes the Application the Listener will be removed");
                System.out.println("The Listener got removed");
            }
        };
        Game2048Listener game2048Listener = new Game2048Listener() {
            @Override
            public void newState(State state) {
                System.out.println("The new State (information about game-board, current Score and high Score) got send to the Frontend");
            }
        };
        game2048Service.addCallback(game2048Listener);

        game2048Service.initialisation();
        game2048Listener.newState(placeHolderState);
        game2048Service.moveBlocks(MovingDirection.right);
        game2048Listener.newState(placeHolderState);
        game2048Service.moveBlocks(MovingDirection.up);
        game2048Listener.newState(placeHolderState);
        game2048Service.moveBlocks(MovingDirection.down);
        game2048Listener.newState(placeHolderState);
        game2048Service.moveBlocks(MovingDirection.left);
        game2048Listener.newState(placeHolderState);

        System.out.println("A couple of moves later, the game is won and the player starts a new Game");

        game2048Service.initialisation();
        game2048Listener.newState(placeHolderState);
        System.out.println("Now the prozess repeats itself until the player closes the Application");
        game2048Service.removeCallback(game2048Listener);
        //        Game2048Service game2048Service = null;
//        Block placeholderBlock1 = null;
//        Block placeholderBlock2 = null;
//
//        game2048Service.addBlock();
//        game2048Service.addBlock();
//        game2048Service.updateCurrentScore();
//        game2048Service.calculateSumOfAllBlockValues();
//
//        game2048Service.moveBlocks(MovingDirection.left);
//        game2048Service.addBlock();
//        game2048Service.updateCurrentScore();
//        game2048Service.calculateSumOfAllBlockValues();
//
//        game2048Service.moveBlocks(MovingDirection.up);
//        game2048Service.merge(placeholderBlock1, placeholderBlock2);
//        game2048Service.doubleValueOfBlock(placeholderBlock1);
//        game2048Service.deleteBlock(placeholderBlock2);
//        game2048Service.addBlock();
//        game2048Service.updateCurrentScore();
//        game2048Service.calculateSumOfAllBlockValues();
//
//         /*
//         Continues until two 1024 blocks merge
//         */
//        game2048Service.moveBlocks(MovingDirection.down);
//        game2048Service.merge(placeholderBlock1, placeholderBlock2);
//        game2048Service.doubleValueOfBlock(placeholderBlock1);
//        game2048Service.deleteBlock(placeholderBlock2);
//
//        game2048Service.gameWon();
//        game2048Service.makeBlocksImmovable();
//        game2048Service.updateHighScore();
    }

}