package de.hhn.it.devtools.components.minesweeper.junit;

import de.hhn.it.devtools.apis.minesweeper.MinesweeperCoordinates;
import de.hhn.it.devtools.apis.minesweeper.Status;
import de.hhn.it.devtools.components.minesweeper.provider.Handler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Testing Handler")
public class TestHandler {

    @Test
    void testInitField() {
        int size = 10;
        int bombCount = 10;
        Handler handler = new Handler(size, bombCount);
        //Act
        int[][] logicField = handler.getMatrix();
        //Assert
        assertAll("initField",
                () -> assertEquals(size, logicField.length),
                () -> assertEquals(size, logicField[0].length)
        );
    }
    @Test
    void testBombsInFillMatrixWithBombs(){
        int size = 10;
        int bombCount = 10;
        Handler handler = new Handler(size, bombCount);
        int[][] logicField = handler.getMatrix();

        int bombCounter = 0;
        for (int[] row : logicField) {
            for (int cell : row) {
                if (cell == -1) {
                    bombCounter++;
                }
            }
        }
        assertEquals(bombCount, bombCounter);
    }

    @Test
    void adjustStatusForAdjustingFieldsBombNeighboursIncremented() {
        int x = 4;
        int y = 4;

        Handler handler = new Handler(8, 8);
        MinesweeperCoordinates coordinates = new MinesweeperCoordinates(x, y);
        handler.adjustStatusForAdjustingFieldsWrapper(coordinates);

        // get the matrix after bomb is placed
        int[][] matrix = handler.getMatrix();

        // check the matrix has the expected values
        assertAll("adjustStatusForAdjustingFields",
                () -> assertTrue(matrix[x-1][y+1] > 0 || matrix [x-1][y+1] == -1),
                () -> assertTrue(matrix[x+1][y] > 0 || matrix [x+1][y] == -1),
                () -> assertTrue(matrix[x][y+1] > 0 || matrix [x][y+1] == -1),
                () -> assertTrue(matrix[x+1][y+1] > 0 || matrix [x+1][y+1] == -1),
                () -> assertTrue(matrix[x-1][y] > 0 || matrix [x-1][y] == -1),
                () -> assertTrue(matrix[x][y-1] > 0 || matrix [x][y-1] == -1),
                () -> assertTrue(matrix[x-1][y-1] > 0 || matrix [x-1][y-1] == -1),
                () -> assertTrue(matrix[x+1][y-1] > 0 || matrix [x+1][y-1] == -1)
        );
    }

    @Test
    void testAdjustStatusForAdjustingFieldsCorner(){
        Handler handler = new Handler(3,3);

    }

    @Test
    void testAdjustStatusForAdjustingFieldsNegativeValue() {
        Handler handler = new Handler(5,5);
        boolean errorThrown = false;
        try {
            handler.adjustStatusForAdjustingFieldsWrapper(new MinesweeperCoordinates(-2,-2));
        } catch (IndexOutOfBoundsException e) {
            errorThrown = true;
        }
        assertTrue(errorThrown);
    }

    @Test
    void testCreateVisualMatrix(){
        Handler handler = new Handler(5, 0);
        Status[][] expectedMatrix = {
                {Status.BOMB   , Status.ONE    , Status.NOTHING, Status.NOTHING , Status.NOTHING},
                {Status.ONE    , Status.TWO    , Status.ONE    , Status.ONE     , Status.NOTHING},
                {Status.NOTHING, Status.ONE    , Status.BOMB   , Status.ONE     , Status.NOTHING},
                {Status.NOTHING, Status.ONE    , Status.ONE    , Status.ONE     , Status.NOTHING},
                {Status.NOTHING, Status.NOTHING, Status.NOTHING, Status.NOTHING , Status.NOTHING}
        };
        handler.adjustStatusForAdjustingFieldsWrapper(new MinesweeperCoordinates(0,0));
        handler.adjustStatusForAdjustingFieldsWrapper(new MinesweeperCoordinates(2,2));
        handler.createFieldForVisualsWrapper();
        Status[][] actualMatrix = handler.getVisualMatrix();
        assertArrayEquals(expectedMatrix, actualMatrix);
    }

    @Test
    void testIntToStatus(){
        Handler handler = new Handler(10,10);
        assertAll("intToStatus",
                () -> assertEquals(Status.BOMB, handler.intToStatusWrapper(-1)),
                () -> assertEquals(Status.NOTHING, handler.intToStatusWrapper(0)),
                () -> assertEquals(Status.ONE, handler.intToStatusWrapper(1)),
                () -> assertEquals(Status.TWO, handler.intToStatusWrapper(2)),
                () -> assertEquals(Status.THREE, handler.intToStatusWrapper(3)),
                () -> assertEquals(Status.FOUR, handler.intToStatusWrapper(4)),
                () -> assertEquals(Status.FIVE, handler.intToStatusWrapper(5)),
                () -> assertEquals(Status.SIX, handler.intToStatusWrapper(6)),
                () -> assertEquals(Status.SEVEN, handler.intToStatusWrapper(7)),
                () -> assertEquals(Status.EIGHT, handler.intToStatusWrapper(8)),
                () -> assertEquals(Status.BOMB, handler.intToStatusWrapper(-25352)),
                () -> assertEquals(Status.BOMB, handler.intToStatusWrapper(99999)),
                () -> assertEquals(Status.BOMB, handler.intToStatusWrapper(-2))
        );
    }

    @Test
    void testClickFieldCorrectValues() {
        Handler handler = new Handler(3, 0);
        handler.adjustStatusForAdjustingFieldsWrapper(new MinesweeperCoordinates(0,0));
        handler.adjustStatusForAdjustingFieldsWrapper(new MinesweeperCoordinates(1,1));
        handler.adjustStatusForAdjustingFieldsWrapper(new MinesweeperCoordinates(2,0));
        handler.createFieldForVisualsWrapper();
        assertAll("clickField",
                () -> assertEquals(Status.BOMB, handler.clickField(new MinesweeperCoordinates(0,0))),
                () -> assertEquals(Status.ONE, handler.clickField(new MinesweeperCoordinates(1,2))),
                () -> assertEquals(Status.TWO, handler.clickField(new MinesweeperCoordinates(0,1))),
                () -> assertEquals(Status.THREE, handler.clickField(new MinesweeperCoordinates(1,0)))
        );
    }

    @Test
    void clickOutOfBounds(){
        Handler handler = new Handler(5,5);
        boolean errorThrown = false;
        try {
            handler.clickField(new MinesweeperCoordinates(-1,-1));
        } catch (IndexOutOfBoundsException e) {
            errorThrown = true;
        }
        assertTrue(errorThrown);
    }

    @Test
    void testMarkField(){
        Handler handler = new Handler(3, 0);
        handler.adjustStatusForAdjustingFieldsWrapper(new MinesweeperCoordinates(0,0));
        handler.adjustStatusForAdjustingFieldsWrapper(new MinesweeperCoordinates(1,1));
        handler.adjustStatusForAdjustingFieldsWrapper(new MinesweeperCoordinates(2,0));
        handler.createFieldForVisualsWrapper();

        handler.markField(new MinesweeperCoordinates(0,0));
        handler.markField(new MinesweeperCoordinates(1,2));
        handler.markField(new MinesweeperCoordinates(0,1));
        handler.markField(new MinesweeperCoordinates(1,0));
        assertAll("markField",
                () -> assertEquals(Status.BOMB, handler.markField(new MinesweeperCoordinates(0,0))),
                () -> assertEquals(Status.ONE, handler.markField(new MinesweeperCoordinates(1,2))),
                () -> assertEquals(Status.TWO, handler.markField(new MinesweeperCoordinates(0,1))),
                () -> assertEquals(Status.THREE, handler.markField(new MinesweeperCoordinates(1,0))),
                () -> assertEquals(Status.FLAG, handler.markField(new MinesweeperCoordinates(2,2)))
        );
    }

    @Test
    void testMarkFieldOutOfBounds(){
        Handler handler = new Handler(5,5);
        boolean errorThrown = false;
        try {
            handler.markField(new MinesweeperCoordinates(-1,-1));
        } catch (IndexOutOfBoundsException e) {
            errorThrown = true;
        }
        assertTrue(errorThrown);
    }

    @Test
    void testSetGetMatrix(){
        Handler handler = new Handler(5, 3);
        int[][] newMatrix = {{1, 1, 1}, {1, -1, 1}, {1, 1, 1}};
        handler.setMatrix(newMatrix);
        assertArrayEquals(newMatrix, handler.getMatrix());
    }

    @Test
    void testSetGetVisualMatrix(){
        Handler handler = new Handler(5, 3);
        int[][] newMatrix = {{1, 1, 1}, {1, -1, 1}, {1, 1, 1}};
        Status[][] visMatrix = {{Status.ONE, Status.ONE, Status.ONE},
                {Status.ONE, Status.BOMB, Status.ONE},
                {Status.ONE, Status.ONE, Status.ONE}};
        handler.setMatrix(newMatrix);
        handler.createFieldForVisualsWrapper();
        assertArrayEquals(visMatrix, handler.getVisualMatrix());
    }
}
