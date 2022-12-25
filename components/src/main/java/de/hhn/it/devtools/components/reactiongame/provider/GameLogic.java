package de.hhn.it.devtools.components.reactiongame.provider;

import de.hhn.it.devtools.apis.reactiongame.Difficulty;
import de.hhn.it.devtools.apis.reactiongame.GameState;
import de.hhn.it.devtools.apis.reactiongame.ReactiongameListener;


import javax.swing.Timer;
import java.util.ArrayList;

public class GameLogic {

    private ArrayList<ReactiongameListener> callbacks = new ArrayList<>();
    private RelGameField gameField = new RelGameField();
    private RelPlayer player;
    private Timer timer; // 2 verschiedene Timer für front und backend?

    private Difficulty difficulty;
    private GameState state;

    private RelObstacle pObstacle; // player is in this obstacle
    private RelAimTarget pAimTarget; // player is in this aimtarget
    private char pKey;
    private int score;
    private boolean isInvincible = false;

    int timePlayed;


    public GameLogic(Difficulty difficulty) {
        this.difficulty = difficulty;
        player = new RelPlayer("");
        timer = new Timer(1000, new TaskPerformer(timer, this));
    }

    public ArrayList<ReactiongameListener> getCallbacks() {
        return callbacks;
    }

    public RelGameField getGameField() {
        return gameField;
    }

    public RelPlayer getPlayer() {
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

    public void setpObstacle(RelObstacle pObstacle) {
        this.pObstacle = pObstacle;
    }

    public void setpAimTarget(RelAimTarget pAimTarget) {
        this.pAimTarget = pAimTarget;
    }

    public void setpKey(char pKey) {
        this.pKey = pKey;
    }

    public void setInvincible(boolean invincible) {
        isInvincible = invincible;
    }

    /**
     * Methods gets called when player runs into an obstacle or after his iframes end.
     */
    public void playerHitObstacle() {
        if (isInvincible || pObstacle == null) return;
        // player is in iFrames OR no longer in an obstacle

        new IFrameThread(this).start();
        player.setCurrentLife(player.getCurrentLife() - 1);


        if (player.getCurrentLife() > 0) { // is player game over?
            for (ReactiongameListener callback :
                    callbacks) {
                callback.gameOver();
            }
            return;
        }

        for (ReactiongameListener callback :
                callbacks) { // player loses a life
            callback.currentLife(player.getCurrentLife());
        }
    }

    /**
     * Methods checks if the player is in an aimtarget and pressed the right key.
     */
    public void checkForTargetHit() {
        if (pAimTarget.getKey() == pKey) {
            score += 100;

            for (ReactiongameListener callback :
                    callbacks) { // player loses a life
                callback.changeScore(score);
            }
        }
    }




}
