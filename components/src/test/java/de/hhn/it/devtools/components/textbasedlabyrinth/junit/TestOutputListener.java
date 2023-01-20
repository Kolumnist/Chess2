package de.hhn.it.devtools.components.textbasedlabyrinth.junit;

import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.*;
import de.hhn.it.devtools.apis.textbasedlabyrinth.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test addOutputListener + removeOutputListener")
public class TestOutputListener {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(TestOutputListener.class);

    private Game game;


    @BeforeEach
    void setup(){
        game = new Game();
        game.startup();
    }

    @Test
    @DisplayName("Test add Callback in Game")
    public void checkAddOutputListener(){
        OutputListener listener = new DummyCallback();
        game.addListener(listener);
        assertEquals(1, game.getListeners().size());
    }

    @Test
    @DisplayName("Test add Callback in Game")
    public void checkRemoveOutputListener(){
        OutputListener listener = new DummyCallback();
        game.addListener(listener);
        assertEquals(1, game.getListeners().size());
        game.removeListener(listener);
        assertEquals(0, game.getListeners().size());
    }
}
