package de.hhn.it.devtools.components.textbasedlabyrinth.junit;

import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.*;
import de.hhn.it.devtools.apis.textbasedlabyrinth.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRoom {

    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(TestRoom.class);

    private Room room;
    private Room room1;
    private Item item;

    @BeforeEach
    void setup() {
        room = new Room(0, "Test");
        room1 = new Room(1, "Test1");
        item = new Item(0, "TestItem", "ExampleText");
    }

    @Test
    @DisplayName("Test RoomFailed Exception")
    public void checkRoomFailedException() {
        RoomFailedException exception = assertThrows(RoomFailedException.class,
                () -> room.getRoom(Direction.WEST));
    }

    @Test
    @DisplayName("Test if Rooms are correctly assigned to each other")
    public void checkSetNextDoorRoom() throws RoomFailedException {
        room.setNextDoorRoom(room1, Direction.NORTH);
        assertEquals(room1.getRoom(Direction.SOUTH), room);
    }

    @Test
    @DisplayName("Test if Room adds item correctly")
    public void checkAddItemToRoom() {
        room.addItem(item);
        assertEquals(1, room.search().size());
    }

}
