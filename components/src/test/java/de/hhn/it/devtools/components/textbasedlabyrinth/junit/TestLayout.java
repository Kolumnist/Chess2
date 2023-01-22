package de.hhn.it.devtools.components.textbasedlabyrinth.junit;

import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.*;
import de.hhn.it.devtools.apis.textbasedlabyrinth.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestLayout {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(TestLayout.class);

    private Layout layout;
    private Player player;
    private ArrayList<Room> rooms;

    @BeforeEach
    void setup() {
        player = new Player("Joe");
        layout = new Layout(player);
        rooms = new ArrayList<>();
        int i = 0;
        while(i <= 5){
            Room room = new Room(i, "x");
            rooms.add(room);
            i++;
        }
    }

    @Test
    @DisplayName("Test if Layout will be correctly created")
    public void checkLayoutCreation() {
        assertEquals(player, layout.getPlayer());
        assertEquals(0, layout.getAllRooms().size());
    }

    @Test
    @DisplayName("Test if Layout sets all rooms correctly")
    public void checkLayoutSetAllRooms() {
        layout.setAllRooms(rooms);
        assertEquals(rooms, layout.getAllRooms());
    }

    @Test
    @DisplayName("Test if Layout sets startroom correctly")
    public void checkLayoutSetStartRoom() {
        layout.setAllRooms(rooms);
        layout.setStartRoom();
        assertEquals(layout.getAllRooms().get(0), layout.getStartRoom());
        assertEquals(player.getCurrentRoomOfPlayer(), layout.getStartRoom());
    }
}
