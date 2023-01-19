package de.hhn.it.devtools.components.textbasedlabyrinth.junit;

import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.*;
import de.hhn.it.devtools.apis.textbasedlabyrinth.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestPuzzle {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(TestPuzzle.class);

    private Door door;
    private Item key;
    private Item keyWrong;

    @BeforeEach
    void setup() {
        door = new Door();
        key = new Item(0, "TestKey", "KeyInfo");
        keyWrong = new Item(1, "TestKey", "Should not Open anything");
        door.setPuzzle(key);
    }

    @Test
    @DisplayName("Test if Door is locked after being assigned a Puzzle")
    public void checkPuzzleLocksDoor() throws RoomFailedException {
        assertTrue(door.checkIfLocked());
    }

    @Test
    @DisplayName("Test if Door is unlocked after using assigned Key")
    public void checkPuzzleIsSolvedWithKey() {
        door.unlock(key);
        assertTrue(door.unlock(key));
    }

    @Test
    @DisplayName("Test if Door is locked after trying to unlock it with wrong key")
    public void checkPuzzleIsSolvedWithKeyBadCase() {
        door.unlock(keyWrong);
        assertFalse(door.unlock(keyWrong));
    }
}
