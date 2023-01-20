package de.hhn.it.devtools.components.textbasedlabyrinth.junit;

import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.*;
import de.hhn.it.devtools.apis.textbasedlabyrinth.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestDoor {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(TestDoor.class);

    private Door door;


    @BeforeEach
    void setup() {
        door = new Door();
    }

    @Test
    @DisplayName("Test if Door will be correctly created")
    public void checkDoorCreation() {
        Door door1 = new Door();
        assertEquals("This is an open path. You could just walk through.", door1.getInspectMessage());
        assertFalse(door1.checkIfLocked());
        assertFalse(door1.hasPuzzle());
    }

    @Test
    @DisplayName("Test if Door will be correctly opened")
    public void checkDoorOpen() {
        assertEquals("You step into the next room through the opening.", door.open());
    }
}
