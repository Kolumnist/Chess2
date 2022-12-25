package de.hhn.it.devtools.components.reactiongame.provider;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Task for ingame timer. Present task: count time.
 */
public class TaskPerformer implements ActionListener {

    private final Timer timer;
    private final GameLogic gameLogic;

    public TaskPerformer(Timer timer, GameLogic gameLogic) {
        this.timer = timer;
        this.gameLogic = gameLogic;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameLogic.timePlayed++;
    }
}
