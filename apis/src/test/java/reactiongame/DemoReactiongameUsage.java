package reactiongame;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.reactiongame.AimTargetDescriptor;
import de.hhn.it.devtools.apis.reactiongame.Difficulty;
import de.hhn.it.devtools.apis.reactiongame.ObstacleDescriptor;
import de.hhn.it.devtools.apis.reactiongame.ReactiongameListener;
import de.hhn.it.devtools.apis.reactiongame.ReactiongameService;
import java.io.IOException;

/**
 * This is a usage demo for the ReactiongameService. It is not runnable in this module.
 */
public class DemoReactiongameUsage {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(DemoReactiongameUsage.class);

    public static void main(String[] args) throws IllegalParameterException, IOException {

        ReactiongameService reactiongameService = null;
        ReactiongameListener reactiongameListener = null;



        logger.info("> Player starts a run");
        reactiongameService.newRun(Difficulty.HARD);
        reactiongameListener.addObstacle(new ObstacleDescriptor(100, 100, 200, 200));
        reactiongameListener.addAimTarget(new AimTargetDescriptor(400, 400, 50, 'W'));

        logger.info("> Player pauses the run");
        reactiongameService.pauseRun();

        logger.info("> Player continues the run");
        reactiongameService.continueRun();


        logger.info("> Player hits obstacle 0 AND loses a life");
        reactiongameListener.hitObstacle(0);
        reactiongameListener.currentLife(2);

        logger.info("> Player gets to aim target");
        reactiongameListener.aimTargetHit('W', 0);

        logger.info("> Current courser position is x, y");
        reactiongameService.presentCourserPosition(0,1);

        logger.info("> Player pressed the Button: a");
        reactiongameService.keyPressed('a');

        logger.info("> Loaded the highscoretable with the format: player - score");
        reactiongameService.loadHighscoreTable();

        logger.info("> Set the playername for the highscore");
        reactiongameService.setCurrentPlayerName("Player");

        logger.info("> Saved the current highscoretable");
        reactiongameService.saveHighscoreTable();

        logger.info("> Player ended the run");
        reactiongameService.endRun();
    }
}
