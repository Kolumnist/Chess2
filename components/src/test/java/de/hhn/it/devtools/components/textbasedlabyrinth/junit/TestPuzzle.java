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
import static org.junit.jupiter.api.Assertions.*;

public class TestPuzzle {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(TestPuzzle.class);

    private Room room;
    private Room room1;
    private Door door;
    private Item key;

    @BeforeEach
    void setup() {
       room = new Room(0, "TestRoom");
       room1 = new Room(1, "TestRoom1");
       room.setNextDoorRoom(room1, Direction.WEST);
       room.setDoors();
       key = new Item(0, "TestKey", "KeyInfo");
    }

    @Test
    @DisplayName("Test if locked Door will open with assigned Key")
    public void check() throws RoomFailedException {
        room.getDoor(Direction.WEST).setPuzzle(key);
        assertTrue(room1.getDoor(Direction.EAST).unlock(key));
    }

    @Test
    @DisplayName("Test if Door is locked after being assigned a Puzzle")
    public void test() throws RoomFailedException {
        room.getDoor(Direction.WEST).setPuzzle(key);
        assertFalse(room.getDoor(Direction.WEST).checkIfLocked());
    }

}
