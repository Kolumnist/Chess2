package de.hhn.it.devtools.components.textbasedlabyrinth.junit;

import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.Map;
import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.Game;
import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.GameService;
import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.Seed;
import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.OutputListener;
import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.*;
import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.exceptions.RoomFailedException;
import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.exceptions.NoSuchItemFoundException;
import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.exceptions.InvalidSeedException;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTreasure {

    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(TestTreasure.class);

    private Treasure treasure;
    private GameService gameService;

    @BeforeEach
    void setup() {
        treasure = new Treasure(0, "TestTreasure", "ExampleText");
        gameService = new Game();
        gameService.startup();
        gameService.start();
    }

    @Test
    @DisplayName("Test if the Score goes up if Player picks up a Treasure")
    public void testTreasureScore() throws NoSuchItemFoundException {
        gameService.getCurrentRoom().addItem(treasure);
        gameService.pickUpItem(0);
        assertEquals(1, gameService.getScore());
    }

    @Test
    @DisplayName("Test NoSuchItemFoundException Exception")
    public void testRoomFailedException()  {
        gameService.getCurrentRoom().addItem(treasure);
        NoSuchItemFoundException exception = assertThrows(NoSuchItemFoundException.class,
                () -> gameService.searchRoom());
    }
}
