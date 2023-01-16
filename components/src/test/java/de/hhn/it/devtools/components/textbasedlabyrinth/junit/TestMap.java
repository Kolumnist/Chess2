package de.hhn.it.devtools.components.textbasedlabyrinth.junit;

import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.*;
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



    private LayoutGenerator layoutGenerator;

    @BeforeEach
    void setup() throws InvalidSeedException {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(1);
        Seed seed = new Seed(integers);
        layoutGenerator = new LayoutGenerator(Map.Unknown_Sewers, seed);
    }

    @Test
    @DisplayName("Test if correct Map is created")
    public void checkCorrectMap() {
        assertEquals(Map.Unknown_Sewers, layoutGenerator.getMap());
    }
}
