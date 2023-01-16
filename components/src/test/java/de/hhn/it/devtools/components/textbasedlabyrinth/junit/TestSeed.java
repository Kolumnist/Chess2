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

public class TestSeed {

    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(TestSeed.class);

    private Seed seed;

    @BeforeEach
    void setup() { }

    @Test
    @DisplayName("Test InvalidSeedException exception")
    public void testInvalidSeedExceptionToFewDigits() {
        ArrayList<Integer> integers = new ArrayList<>();
        InvalidSeedException exception = assertThrows(InvalidSeedException.class,
                () -> seed = new Seed(integers) );
    }

    @Test
    @DisplayName("Test InvalidSeedException exception")
    public void testInvalidSeedExceptionToManyDigits() {
        ArrayList<Integer> integers = new ArrayList<>();
        int i = 0;
        while(i != 10){
            integers.add(1);
            i++;
        }
        InvalidSeedException exception = assertThrows(InvalidSeedException.class,
                () -> seed = new Seed(integers) );
    }

    @Test
    @DisplayName("Test if Seed returns correct seed digits")
    public void testSeedGetSeed() throws InvalidSeedException {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(1);
        seed = new Seed(integers);
        assertEquals(integers, seed.getSeed());
    }

}
