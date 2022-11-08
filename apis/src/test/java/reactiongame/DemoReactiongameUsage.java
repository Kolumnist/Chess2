package reactiongame;

import de.hhn.it.devtools.apis.reactiongame.Difficulty;
import de.hhn.it.devtools.apis.reactiongame.ReactiongameService;

/**
 * This is a usage demo for the ReactiongameService. It is not runnable in this module.
 */
public class DemoReactiongameUsage {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(DemoReactiongameUsage.class);

    public static void main(String[] args) {

        ReactiongameService reactiongameService = null;


        logger.info("> Player starts a run");
        reactiongameService.newRun(Difficulty.HARD);

        logger.info("> Player pauses the run");
        reactiongameService.pauseRun();

        logger.info("> Player continues the run");
        reactiongameService.continueRun();

        logger.info("> Player finishes a level");
        logger.info("> System loads new level");
        reactiongameService.pauseTimer();

        logger.info("> System finished loading");
        reactiongameService.startTimer();

        logger.info("> Time is up");
        reactiongameService.endRun();




    }

}
