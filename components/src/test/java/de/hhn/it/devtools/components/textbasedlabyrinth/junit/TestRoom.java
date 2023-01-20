package de.hhn.it.devtools.components.textbasedlabyrinth.junit;

import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.*;
import de.hhn.it.devtools.apis.textbasedlabyrinth.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    @DisplayName("Test if Room will be correctly created")
    public void checkRoomCreation() {
        Room testRoom = new Room(10, "A Dark and Cold Room.");
        assertEquals(10, testRoom.getRoomId());
        assertEquals("A Dark and Cold Room.", testRoom.getDescription());
        assertEquals(0, testRoom.getRoomMap().size());
        assertEquals(0, testRoom.getItems().size());
        assertEquals(0, testRoom.getDoorMap().size());
        assertEquals(4, testRoom.getDirections().size());
    }

    @Test
    @DisplayName("Test if Room adds Items correctly")
    public void checkRoomAddItem() {
        room.addItem(item);
        assertEquals(1, room.getItems().size());
        assertEquals(item, room.getItems().get(0));
    }

    @Test
    @DisplayName("Test if Room adds Items correctly (Good Case)")
    public void checkRoomRemoveItemGoodCase() throws NoSuchItemFoundException {
        room.addItem(item);
        assertEquals(1, room.getItems().size());
        room.removeItem(0);
        assertEquals(0, room.getItems().size());
    }

    @Test
    @DisplayName("Test if Room adds Items correctly (Good Case)")
    public void checkRoomRemoveItemBadCase() {
        NoSuchItemFoundException exception = assertThrows(NoSuchItemFoundException.class,
                () -> room.removeItem(0));
    }

    @Test
    @DisplayName("Test if Room search correctly returns a List of Items in the Room")
    public void checkRoomSearch() {
        Item item2 = new Item(1, "TestItem1", "ExampleText1");
        Treasure treasure = new Treasure(2, "TestTreasure", "ExampleText2");
        room.addItem(item2);
        room.addItem(item);
        room.addItem(treasure);
        assertTrue(room.search().contains(item));
        assertTrue(room.search().contains(item2));
        assertTrue(room.search().contains(treasure));
    }

    @Test
    @DisplayName("Test if Rooms are correctly assigned to each other")
    public void checkSetNextDoorRoomGoodCase() throws RoomFailedException {
        room.setNextDoorRoom(room1, Direction.NORTH);
        assertEquals(room.getRoom(Direction.NORTH), room1);
        assertEquals(room1.getRoom(Direction.SOUTH), room);
    }

    @Test
    @DisplayName("Test if Rooms are correctly re-assigned to each other even if the Room got assigned prior")
    public void checkSetNextDoorRoomBadCase() throws RoomFailedException {
        room.setNextDoorRoom(room1, Direction.NORTH);
        Room testRoom = new Room(20, "Test20");
        room1.setNextDoorRoom(testRoom, Direction.SOUTH);
        assertEquals(testRoom, room1.getRoom(Direction.SOUTH));
        assertEquals(room1, testRoom.getRoom(Direction.NORTH));
        assertFalse(room.getRoomMap().containsKey(Direction.NORTH));
    }

    @Test
    @DisplayName("Test Room if Doors are correctly set")
    public void checkRoomSetDoors() {
        room.setNextDoorRoom(room1, Direction.NORTH);
        room.setDoors();
        assertTrue(room.getDoorMap().containsKey(Direction.NORTH));
    }

    @Test
    @DisplayName("Test Room if correct Room will be returned (Good Case)")
    public void checkRoomGetRoomGoodCase() throws RoomFailedException {
        room.setNextDoorRoom(room1, Direction.NORTH);
        assertEquals(room1, room.getRoom(Direction.NORTH));
    }

    @Test
    @DisplayName("Test Room if correct Room will be returned (Bad Case)")
    public void checkRoomGetRoomBadCase() {
        RoomFailedException exception = assertThrows(RoomFailedException.class,
                () -> room.getRoom(Direction.WEST));
    }

    @Test
    @DisplayName("Test Room if correct Door will be returned (Good Case)")
    public void checkRoomGetDoorGoodCase() throws RoomFailedException {
        room.setNextDoorRoom(room1, Direction.NORTH);
        room.setDoors();
        assertEquals(room.getDoorMap().get(Direction.NORTH), room.getDoor(Direction.NORTH));
    }

    @Test
    @DisplayName("Test Room if correct Door will be returned (Bad Case)")
    public void checkRoomGetDoorBadCase() throws RoomFailedException {
        RoomFailedException exception = assertThrows(RoomFailedException.class,
                () -> room.getDoor(Direction.WEST));
    }

    @Test
    @DisplayName("Test Room if it gets the Exit Boolean value correctly)")
    public void checkRoomIsExit() {
        room.setExit();
        assertTrue(room.isExit());
    }

}
