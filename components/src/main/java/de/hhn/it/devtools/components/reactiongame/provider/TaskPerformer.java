package de.hhn.it.devtools.components.reactiongame.provider;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StopListener implements ActionListener {

    private final Timer timer;
    private final RelReactionGameService service;

    public StopListener(Timer timer, RelReactionGameService service) {
        this.timer = timer;
        this.service = service;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
