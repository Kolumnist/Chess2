package de.hhn.it.devtools.components.textbasedlabyrinth;


import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.*;
import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.exceptions.InvalidSeedException;
import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.exceptions.RoomFailedException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class GameTest {



    private Game game;
    private Player player;





    @BeforeEach
    void preTest() {
        game = new Game();


    }


    @BeforeEach
    @Test
    public void testStartup() {
        game.startup();
        player = game.getPlayer();
        assertNotNull(player);
        assertInstanceOf(Player.class, player);
        assertTrue(game.getPlayerName().contentEquals("Jones"));
    }



    @Test
    public void testLayoutGeneration() {
        ArrayList<Integer> seedList1 = new ArrayList<>();
        seedList1.add(0);
        seedList1.add(0);

        try {
            game.setCurrentLayout(Map.Ancient_Dungeon, new Seed(seedList1));
        } catch (InvalidSeedException | RoomFailedException e) {
            fail(e.getMessage());
        }

        List<Room> roomsList = game.getCurrentLayout().getAllRooms();

        //Check that it has as many rooms as the LayoutGenerator has defined in the maxRoom variable.
        try {
            LayoutGenerator generator = new LayoutGenerator(Map.Ancient_Dungeon, new Seed(seedList1));
            assertEquals(generator.getMaxRoomCount(), roomsList.size());
        } catch (InvalidSeedException e) {
            fail();
        }

        //The player should be in room 0, the startRoom.
        assertEquals(game.getCurrentRoom(), roomsList.get(0));

    }






}
