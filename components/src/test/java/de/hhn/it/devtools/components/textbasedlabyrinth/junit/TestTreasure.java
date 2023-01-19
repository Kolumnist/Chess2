package de.hhn.it.devtools.components.textbasedlabyrinth.junit;

import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.*;
import de.hhn.it.devtools.apis.textbasedlabyrinth.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTreasure {

    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(TestTreasure.class);

    private Treasure treasure;
    private Game gameService;

    @BeforeEach
    void setup() {
        treasure = new Treasure(100000, "TestTreasure", "ExampleText");
        gameService = new Game();
        gameService.startup();
        gameService.start();
    }

    @Test
    @DisplayName("Test if Treasure give score points")
    public void checkTreasureScore()  {
        assertEquals(1, treasure.getScorePoint());
    }
}
