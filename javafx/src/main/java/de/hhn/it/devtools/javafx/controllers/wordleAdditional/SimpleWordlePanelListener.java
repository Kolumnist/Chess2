package de.hhn.it.devtools.javafx.controllers.wordleAdditional;

import de.hhn.it.devtools.apis.wordle.State;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class SimpleWordlePanelListener implements de.hhn.it.devtools.apis.wordle.WordlePanelListener {

    @FXML
    private Label label;

    public SimpleWordlePanelListener(Label label) {
        this.label = label;
    }

    @Override
    public void newState(State state) {
        executeListenerTasks(state);
    }

    public void executeListenerTasks(State state) {
        switch (state) {
            case CORRECT ->  label.setBackground(new Background(new BackgroundFill(Color.rgb(83,141,78), CornerRadii.EMPTY, Insets.EMPTY)));
            case PARTIALLY_CORRECT -> label.setBackground(new Background(new BackgroundFill(Color.rgb(181,159,59), CornerRadii.EMPTY, Insets.EMPTY)));
            case FALSE -> label.setBackground(new Background(new BackgroundFill(Color.rgb(102,102,102), CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }


}
