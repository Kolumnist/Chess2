package de.hhn.it.devtools.components.reactiongame;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.reactiongame.Difficulty;
import de.hhn.it.devtools.apis.reactiongame.ReactiongameListener;
import de.hhn.it.devtools.components.reactiongame.provider.RgcService;

/**
 * This is a usage demo for the ReactiongameService. It is not runnable in this module.
 */
public class DemoReactiongameUsage {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(DemoReactiongameUsage.class);

    public static void main(String[] args) throws IllegalParameterException {

        RgcService reactiongameService = new RgcService();



        logger.info("> Player starts a run");
        reactiongameService.newRun(Difficulty.HARD);

        logger.info("> GameLogic adds an obstacle");
        reactiongameService.getRun().addObstacle(0);

        logger.info("> Player pauses the run");
        reactiongameService.pauseRun();

        logger.info("> Player continues the run");
        reactiongameService.continueRun();

        logger.info("> Player enters obstacle");
        reactiongameService.playerEnteredObstacle(0);

        logger.info("> Player leaves game object");
        reactiongameService.playerLeftGameObject();


        logger.info("> Player gets to aim target");

        logger.info("> Player pressed the Button: a");
        reactiongameService.keyPressed('a');

        logger.info("> Loaded the highscoretable with the format: player - score");
        reactiongameService.loadHighscoreTable(null);

        logger.info("> Set the playername for the highscore");
        reactiongameService.setCurrentPlayerName("Player");

        logger.info("> Saved the current highscoretable");
        reactiongameService.saveHighscoreTable();

        logger.info("> Player ended the run");
        reactiongameService.endRun();
    }
}
