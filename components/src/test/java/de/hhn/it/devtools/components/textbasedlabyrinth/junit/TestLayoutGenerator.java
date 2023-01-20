package de.hhn.it.devtools.components.textbasedlabyrinth.junit;

import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.*;
import de.hhn.it.devtools.apis.textbasedlabyrinth.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class TestLayoutGenerator {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(TestMap.class);

    private Map map;
    private Seed seedLow;
    private Seed seedHigh;
    private LayoutGenerator layoutGenerator;

    @BeforeEach
    void setup() throws InvalidSeedException {
     map = Map.Grave_of_the_Mad_King;

    ArrayList<Integer> integersLow = new ArrayList<>();
    integersLow.add(1);
    integersLow.add(1);
    seedLow = new Seed(integersLow);

    ArrayList<Integer> integersHigh = new ArrayList<>();
    integersHigh.add(6);
    integersHigh.add(6);
    seedHigh = new Seed(integersHigh);

    layoutGenerator = new LayoutGenerator(map, seedLow);
    }

    @Test
    @DisplayName("Test if LayoutGenerator will be correctly created")
    public void checkLayoutGeneratorCreation() {
        assertEquals(Map.Grave_of_the_Mad_King, layoutGenerator.getMap());
        assertEquals(seedLow, layoutGenerator.getSeed());
        assertEquals(13, layoutGenerator.getAllRooms().size());
        assertEquals(layoutGenerator.getStartRoom(), layoutGenerator.getAllRooms().get(0));
        assertTrue(layoutGenerator.isPrepared());
        assertTrue(layoutGenerator.getAllRooms().get(12).isExit());
    }

    @Test
    @DisplayName("Test if LayoutGenerator created a correct Room Assignment, Puzzle and Item Placement" +
            "(we only make some samples for the sake of everyone's sanity")
    public void checkLayoutGeneratorGenerateLayoutGraveOfMadKing() throws RoomFailedException {
        layoutGenerator.generateLayout();
        assertEquals(layoutGenerator.getStartRoom().getRoom(Direction.WEST), layoutGenerator.getAllRooms().get(1));
        assertEquals(layoutGenerator.getStartRoom().getRoom(Direction.NORTH), layoutGenerator.getAllRooms().get(4));
        assertEquals(layoutGenerator.getStartRoom().getRoom(Direction.EAST), layoutGenerator.getAllRooms().get(9));

        assertEquals(layoutGenerator.getAllRooms().get(7).getRoom(Direction.SOUTH),
                layoutGenerator.getAllRooms().get(8));

        assertEquals(layoutGenerator.getAllRooms().get(10).getRoom(Direction.EAST),
                layoutGenerator.getAllRooms().get(11));

        assertEquals(layoutGenerator.getAllRooms().get(11).getRoom(Direction.NORTH),
                layoutGenerator.getAllRooms().get(12));

        assertTrue(layoutGenerator.getAllRooms().get(7).getDoor(Direction.SOUTH).hasPuzzle());
        // Exit Key should be here
        assertTrue(layoutGenerator.getAllRooms().get(3).getItems().containsKey(1));
        // a Treasure should be here
        assertTrue(layoutGenerator.getAllRooms().get(7).getItems().containsKey(100));
    }

    @Test
    @DisplayName("Test if LayoutGenerator created a correct Room Assignment, Puzzle and Item Placement" +
            "(we only make some samples for the sake of everyone's sanity")
    public void checkLayoutGeneratorGenerateLayoutAncientDungeon() throws RoomFailedException {
        LayoutGenerator layoutGenerator1 = new LayoutGenerator(Map.Ancient_Dungeon, seedHigh);
        layoutGenerator1.generateLayout();

        assertEquals(layoutGenerator1.getStartRoom().getRoom(Direction.WEST), layoutGenerator1.getAllRooms().get(1));
        assertEquals(layoutGenerator1.getStartRoom().getRoom(Direction.NORTH), layoutGenerator1.getAllRooms().get(2));
        assertEquals(layoutGenerator1.getStartRoom().getRoom(Direction.EAST), layoutGenerator1.getAllRooms().get(3));

        assertEquals(layoutGenerator1.getAllRooms().get(7).getRoom(Direction.EAST),
                layoutGenerator1.getAllRooms().get(8));

        assertEquals(layoutGenerator1.getAllRooms().get(10).getRoom(Direction.NORTH),
                layoutGenerator1.getAllRooms().get(11));

        assertEquals(layoutGenerator1.getAllRooms().get(11).getRoom(Direction.EAST),
                layoutGenerator1.getAllRooms().get(12));

        // 2 Puzzles and Keys
        assertTrue(layoutGenerator1.getAllRooms().get(11).getDoor(Direction.SOUTH).hasPuzzle());
        assertTrue(layoutGenerator1.getAllRooms().get(9).getItems().containsKey(1));

        assertTrue(layoutGenerator1.getAllRooms().get(1).getDoor(Direction.WEST).hasPuzzle());
        assertTrue(layoutGenerator1.getAllRooms().get(9).getItems().containsKey(2));

        // Treasure here
        assertTrue(layoutGenerator1.getAllRooms().get(7).getItems().containsKey(100));
    }

    @Test
    @DisplayName("Test if LayoutGenerator created a correct Room Assignment, Puzzle and Item Placement" +
            "(we only make some samples for the sake of everyone's sanity")
    public void checkLayoutGeneratorGenerateLayoutUnknownSewers() throws RoomFailedException {
        LayoutGenerator layoutGenerator1 = new LayoutGenerator(Map.Unknown_Sewers, seedLow);
        layoutGenerator1.generateLayout();
        assertEquals(layoutGenerator1.getStartRoom().getRoom(Direction.NORTH), layoutGenerator1.getAllRooms().get(1));

        assertEquals(layoutGenerator1.getAllRooms().get(7).getRoom(Direction.WEST),
                layoutGenerator1.getAllRooms().get(8));

        assertEquals(layoutGenerator1.getAllRooms().get(10).getRoom(Direction.NORTH),
                layoutGenerator1.getAllRooms().get(11));

        assertEquals(layoutGenerator1.getAllRooms().get(11).getRoom(Direction.NORTH),
                layoutGenerator1.getAllRooms().get(12));

        assertTrue(layoutGenerator1.getAllRooms().get(7).getDoor(Direction.NORTH).hasPuzzle());
        // Exit Key should be here
        assertTrue(layoutGenerator1.getAllRooms().get(3).getItems().containsKey(1));
        // a Treasure should be here
        assertTrue(layoutGenerator1.getAllRooms().get(7).getItems().containsKey(100));
    }
}
