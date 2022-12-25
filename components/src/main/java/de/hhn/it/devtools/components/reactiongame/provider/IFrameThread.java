package de.hhn.it.devtools.components.reactiongame.provider;

public class IFrameThread extends Thread {

    private GameLogic logic;

    public IFrameThread(GameLogic logic) {
        this.logic = logic;
    }

    @Override
    public void run() {
        logic.setInvincible(true); // i frames start
        try {
            Thread.sleep(1500); // sleep 1.5 seconds
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logic.setInvincible(false); // i frames stop
        logic.playerHitObstacle(); // check again if player is in an obstacles
    }
}
