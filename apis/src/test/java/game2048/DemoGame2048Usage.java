package game2048;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.game2048.Block;
import de.hhn.it.devtools.apis.game2048.Game2048Service;
import de.hhn.it.devtools.apis.game2048.MovingDirection;

/**
 * This usage demo is not runnable because in this module there is no possibility to access the
 * implementation. The runnable demo is accessible in the component's module.
 */
public class DemoGame2048Usage {

    public static void main(String[] args) throws IllegalParameterException {
        Game2048Service game2048Service = null;
        Block placeholderBlock1 = null;
        Block placeholderBlock2 = null;

        game2048Service.addBlock();
        game2048Service.addBlock();
        game2048Service.updateCurrentScore();
        game2048Service.calculateSumOfAllBlockValues();

        game2048Service.moveBlocks(MovingDirection.left);
        game2048Service.addBlock();
        game2048Service.updateCurrentScore();
        game2048Service.calculateSumOfAllBlockValues();

        game2048Service.moveBlocks(MovingDirection.up);
        game2048Service.merge(placeholderBlock1, placeholderBlock2);
        game2048Service.doubleValueOfBlock(placeholderBlock1);
        game2048Service.deleteBlock(placeholderBlock2);
        game2048Service.addBlock();
        game2048Service.updateCurrentScore();
        game2048Service.calculateSumOfAllBlockValues();

         /*
         Continues until two 1024 blocks merge
         */
        game2048Service.moveBlocks(MovingDirection.down);
        game2048Service.merge(placeholderBlock1, placeholderBlock2);
        game2048Service.doubleValueOfBlock(placeholderBlock1);
        game2048Service.deleteBlock(placeholderBlock2);

        game2048Service.gameWon();
        game2048Service.makeBlocksImmovable();
        game2048Service.updateHighScore();
    }

}