package de.hhn.it.devtools.components.reactiongame.provider;

import de.hhn.it.devtools.apis.examples.coffeemakerservice.CoffeeMakerDescriptor;
import de.hhn.it.devtools.apis.reactiongame.Difficulty;
import de.hhn.it.devtools.apis.reactiongame.GameState;
import de.hhn.it.devtools.apis.reactiongame.ReactiongameListener;


import javax.swing.Timer;
import java.util.ArrayList;

public class GameLogic {

    private static final org.slf4j.Logger logger =
        org.slf4j.LoggerFactory.getLogger(GameLogic.class);

    private ArrayList<ReactiongameListener> callbacks = new ArrayList<>();
    private RgcGameField gameField = new RgcGameField();
    private RgcPlayer player;
    private Timer timer; // 2 verschiedene Timer für front und backend?

    private Difficulty difficulty;
    private GameState state;

    private RgcObstacle pObstacle; // player is in this obstacle
    private RgcAimTarget pAimTarget; // player is in this aimtarget
    private char pKey;
    private int score;
    private boolean isInvincible = false;

    private IFrameThread iFrameThread;
    int timePlayed;


    public GameLogic(Difficulty difficulty) {
        this.difficulty = difficulty;
        player = new RgcPlayer("");
        timer = new Timer(1000, new TaskPerformer(timer, this)); // Every second: timeplayed++
        iFrameThread = new IFrameThread(this);
    }

    public ArrayList<ReactiongameListener> getCallbacks() {
        return callbacks;
    }

    public RgcGameField getGameField() {
        return gameField;
    }

    public RgcPlayer getPlayer() {
        return player;
    }

    public Timer getTimer() {
        return timer;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public void setpObstacle(RgcObstacle pObstacle) {
        this.pObstacle = pObstacle;
    }

    public void setpAimTarget(RgcAimTarget pAimTarget) {
        this.pAimTarget = pAimTarget;
    }

    public void setpKey(char pKey) {
        this.pKey = pKey;
    }

    public void setInvincible(boolean invincible) {
        isInvincible = invincible;
        logger.info("invis state = " + isInvincible);
    }

    /**
     * Methods gets called when player runs into an obstacle or after his iframes end.
     */
    public void playerHitObstacle() {
        if (isInvincible || pObstacle == null) return; // if player is not in an object or invincible - do nothing
        // player is in iFrames OR no longer in an obstacle

        logger.info("Player hit obstacle");

        isInvincible = true;
        new Thread(iFrameThread).start();

        player.setCurrentLife(player.getCurrentLife() - 1);


        if (player.getCurrentLife() > 1) { // is player game over?
            for (ReactiongameListener callback :
                    callbacks) {
                callback.gameOver();

                logger.info("!!!GAME OVER!!!");
            }
            return;
        }

        for (ReactiongameListener callback :
                callbacks) { // player loses a life
            callback.currentLife(player.getCurrentLife());

            logger.info("Current life updated: " + player.getCurrentLife());
        }
    }

    /**
     * Methods checks if the player is in an aimtarget and pressed the right key.
     */
    public void checkForTargetHit() {
        if (pAimTarget != null && pAimTarget.getKey() == pKey) {
            score += 100;

            for (ReactiongameListener callback :
                    callbacks) { // raise score
                callback.changeScore(score);

                logger.info("Score updated: " + score);
            }
        }
    }


    public void addObstacle() {
        gameField.addRandomObstacle(gameField.getObstacles().size());

        for (ReactiongameListener callback :
            callbacks) {

            callback.addObstacle(RgcObstacle.toObstacleDescriptor(gameField.getObstacles()
                .get(gameField.getObstacles().size())));

            logger.info("Added new obstacle (" + gameField.getObstacles()
                .get(gameField.getObstacles().size()).getId() + ") ");
        }

    }
}
