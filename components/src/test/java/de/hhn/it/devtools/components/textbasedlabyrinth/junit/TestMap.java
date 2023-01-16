package de.hhn.it.devtools.components.textbasedlabyrinth.junit;

import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.Map;
import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.Game;
import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.GameService;
import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.Seed;
import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.exceptions.InvalidSeedException;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test Map")
public class TestMap {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(TestMap.class);
    private GameService gameService;

    private Seed seed;

    @BeforeEach
    void setup() throws InvalidSeedException {
        gameService = new Game();
        gameService.startup();
        gameService.start();
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(1);
        seed = new Seed(integers);
    }

    @Test
    @DisplayName("Test bad creation of Layout with null reference")
    public void createLayoutWithNullMap() {
        IllegalParameterException exception = assertThrows(IllegalParameterException.class,
                () -> gameService.setCurrentLayout(Map.Ancient_Dungeon, seed));
    }

    @Test
    @DisplayName("Test if correct Map is created")
    public void checkCorrectMap() {
        gameService.setCurrentLayout(Map.Unknown_Sewers, seed);
        assertEquals(Map.Unknown_Sewers, gameService.getMap());
    }
}
