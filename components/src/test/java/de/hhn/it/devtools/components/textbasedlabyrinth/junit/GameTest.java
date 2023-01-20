package de.hhn.it.devtools.components.textbasedlabyrinth.junit;


import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.*;
import de.hhn.it.devtools.apis.textbasedlabyrinth.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class GameTest {

    private Game game;
    private Player player;


    @BeforeEach
    @Test
    public void testStartup() {
        game = new Game();
        game.startup();
        player = game.getPlayer();
        assertNotNull(player);
        assertInstanceOf(Player.class, player);
        assertTrue(game.getPlayerName().contentEquals("Jones"));
    }


    @Test
    @DisplayName("Test simulated Game")
    public void testGameFunction() {
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


        game.move(Direction.WEST);
        //The player should be in room 1.
        assertEquals(game.getCurrentRoom(), roomsList.get(1));

        game.move(Direction.WEST);
        //The player should be in room 4.
        assertEquals(game.getCurrentRoom(), roomsList.get(4));

        game.move(Direction.WEST);
        //The player should still be in room 4.
        assertEquals(game.getCurrentRoom(), roomsList.get(4));

        game.move(Direction.NORTH);
        //The player should be in room 5.
        assertEquals(game.getCurrentRoom(), roomsList.get(5));

        game.move(Direction.SOUTH);
        //The player should be in room 4.
        assertEquals(game.getCurrentRoom(), roomsList.get(4));

        List<Item> itemList1 = game.searchRoom();

        //The list should contain one item.
        assertEquals(1, itemList1.size());

        Item keyItem = itemList1.get(0);


        game.move(Direction.WEST);
        //The player should still be in room 4.
        assertEquals(game.getCurrentRoom(), roomsList.get(4));


        //We will pick up the item now.
        try {
            game.pickUpItem(keyItem.getItemId());
        } catch (NoSuchItemFoundException e) {
            fail();
        }

        //The room should now have no item.
        itemList1 = game.searchRoom();
        assertEquals(0, itemList1.size());

        //The player should have one item in his inventory.
        assertEquals(1, player.getInventory().size());

        //The item should be the same as we have saved here.
        try {
            assertEquals(keyItem, player.getItem(keyItem.getItemId()));
        } catch (NoSuchItemFoundException e) {
            fail();
        }


        //We will now put the item down on the ground again.
        try {
            game.dropItem(keyItem.getItemId());
        } catch (NoSuchItemFoundException e) {
            fail();
        }


        //The room should now have one item.
        itemList1 = game.searchRoom();
        assertEquals(1, itemList1.size());

        //The player should now have no item in his inventory.
        assertEquals(0, player.getInventory().size());


        //Now we will pick up the item again.
        try {
            game.pickUpItem(keyItem.getItemId());
        } catch (NoSuchItemFoundException e) {
            fail();
        }

        //The room should now have no item again.
        itemList1 = game.searchRoom();
        assertEquals(0, itemList1.size());

        //The player should now have one item in his inventory again.
        assertEquals(1, player.getInventory().size());

        //The item should be the same as we have saved here.
        //Again.
        try {
            assertEquals(keyItem, player.getItem(keyItem.getItemId()));
        } catch (NoSuchItemFoundException e) {
            fail();
        }


        //Now we move further in the dungeon.
        game.move(Direction.NORTH);
        //The player should be in room 5.
        assertEquals(game.getCurrentRoom(), roomsList.get(5));

        game.move(Direction.NORTH);
        //The player should be in room 7.
        assertEquals(game.getCurrentRoom(), roomsList.get(7));


        List<Item> itemList2 = game.searchRoom();

        //The list should contain one item.
        assertEquals(1, itemList2.size());

        Item treasureItem = itemList2.get(0);

        //The item should be a treasure item.
        assertTrue(treasureItem.getIsTreasure());

        //We try to pick up the item we already have.
        assertThrows(NoSuchItemFoundException.class, () -> game.pickUpItem(keyItem.getItemId()));

        //Now we will pick up the item.
        try {
            game.pickUpItem(treasureItem.getItemId());
        } catch (NoSuchItemFoundException e) {
            fail();
        }

        //The score should be the same as the treasure point score now.
        assertEquals(((Treasure) treasureItem).getScorePoint(), game.getScore());

        //We will drop the item again.
        try {
            game.dropItem(treasureItem.getItemId());
        } catch (NoSuchItemFoundException e) {
            fail();
        }

        //The score should now be 0.
        assertEquals(0, game.getScore());

        //Now we will pick up the item again.
        try {
            game.pickUpItem(treasureItem.getItemId());
        } catch (NoSuchItemFoundException e) {
            fail();
        }

        //The score should be the same as the treasure point score now.
        assertEquals(((Treasure) treasureItem).getScorePoint(), game.getScore());


        game.move(Direction.EAST);
        //The player should be in room 8.
        assertEquals(game.getCurrentRoom(), roomsList.get(8));

        game.move(Direction.EAST);
        //The player should be in room 6.
        assertEquals(game.getCurrentRoom(), roomsList.get(6));

        game.move(Direction.SOUTH);
        //The player should be in room 2.
        assertEquals(game.getCurrentRoom(), roomsList.get(2));

        game.move(Direction.SOUTH);
        //The player should be in room 0.
        assertEquals(game.getCurrentRoom(), roomsList.get(0));
        assertEquals(player.getCurrentRoomOfPlayer(), roomsList.get(0));

        game.move(Direction.EAST);
        //The player should be in room 3.
        assertEquals(game.getCurrentRoom(), roomsList.get(3));

        game.move(Direction.EAST);
        //The player should be in room 10.
        assertEquals(game.getCurrentRoom(), roomsList.get(10));

        game.move(Direction.EAST);
        //The player should still be in room 10.
        assertEquals(game.getCurrentRoom(), roomsList.get(10));

        game.move(Direction.NORTH);
        //The player should still be in room 10. A puzzle is blocking the way.
        assertEquals(game.getCurrentRoom(), roomsList.get(10));


        //We will first try to solve the puzzle with the treasure.
        try {
            game.interaction(Direction.NORTH, treasureItem);
        } catch (RoomFailedException e) {
            fail();
        }

        //The door should still be closed.
        game.move(Direction.NORTH);
        //The player should still be in room 10.
        assertEquals(game.getCurrentRoom(), roomsList.get(10));

        //Now we use the correct item on the puzzle.
        try {
            game.interaction(Direction.NORTH, keyItem);
        } catch (RoomFailedException e) {
            fail();
        }

        //The door should now be open.
        game.move(Direction.NORTH);
        //The player should still be in room 11.
        assertEquals(game.getCurrentRoom(), roomsList.get(11));


        game.move(Direction.EAST);
        //The player should still be in room 12.
        assertEquals(game.getCurrentRoom(), roomsList.get(12));

        //This room should be the exit.
        assertTrue(player.getCurrentRoomOfPlayer().isExit());


    }


    @Test
    @DisplayName("Test Null Cases")
    public void testNullCases() {
        assertThrows(IllegalArgumentException.class, () -> game.move(null));
        assertThrows(IllegalArgumentException.class, () -> game.inspect(null));
        assertThrows(IllegalArgumentException.class, () -> game.interaction(null, null));
        assertThrows(IllegalArgumentException.class, () -> game.interaction(Direction.NORTH, null));
    }







}
