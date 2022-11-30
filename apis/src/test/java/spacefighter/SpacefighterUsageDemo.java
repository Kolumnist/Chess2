package spacefighter;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.spacefighter.Exception.MissTheEnemieException;
import de.hhn.it.devtools.apis.spacefighter.Exception.NoMoreRoomException;
import de.hhn.it.devtools.apis.spacefighter.Exception.NotReachedScoreException;
import de.hhn.it.devtools.apis.spacefighter.SpaceFighterListener;

public class SpacefighterUsageDemo {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(SpacefighterUsageDemo.class);

    public static void main(String[] args) throws IllegalParameterException, MissTheEnemieException, NoMoreRoomException, NotReachedScoreException {
        de.hhn.it.devtools.apis.spacefighter.SpacefighterInterface spacefighter = null;
        de.hhn.it.devtools.apis.spacefighter.SpaceFighterListener spaceFighterListener = null;

        spacefighter.initializeGame();
        logger.info("New game started");

        spacefighter.pauseGame();
        logger.info("Game paused");

        spaceFighterListener.spwaningEnemies(100);
        logger.info("Enemy spawned");

        spacefighter.killEnemies(10);
        logger.info("Enemy killed");

        spaceFighterListener.score();
        logger.info("score up");

        spacefighter.move(1);
        logger.info("Player moved");

        spaceFighterListener.gameOver();
        logger.info("Game Over...");

        spacefighter.endGame();
        logger.info("Game closing");
    }


}
