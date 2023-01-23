package de.hhn.it.devtools.components.minesweeper.junit;

import de.hhn.it.devtools.apis.minesweeper.MinesweeperCoordinates;
import de.hhn.it.devtools.apis.minesweeper.Status;
import de.hhn.it.devtools.components.minesweeper.provider.Handler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Testing Handler")
public class TestHandler {

    Handler handler;
    Method refCreateVisuals;
    Method refAdjustStatus;
    Method refIntToStatus;

    @BeforeEach
    void initReflections()
        throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        //Get Handler Class and instance
        handler = new Handler(10,5);
        Class refHandler = handler.getClass();

        //Get refAdjustStatus and set Public
        refAdjustStatus = refHandler.getDeclaredMethod("adjustStatusForAdjustingFields", MinesweeperCoordinates.class);
        refAdjustStatus.setAccessible(true);
        //Get refCreateVisuals and set Public
        refCreateVisuals = refHandler.getDeclaredMethod("createFieldForVisuals");
        refCreateVisuals.setAccessible(true);
        //Get refCreateVisuals and set Public
        refIntToStatus = refHandler.getDeclaredMethod("intToStatus", int.class);
        refIntToStatus.setAccessible(true);
    }

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
    void adjustStatusForAdjustingFieldsBombNeighboursIncremented()
        throws InvocationTargetException, IllegalAccessException {
        int x = 4;
        int y = 4;

        MinesweeperCoordinates coordinates = new MinesweeperCoordinates(x, y);

        refAdjustStatus.invoke(handler, coordinates);

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
    void testAdjustStatusForAdjustingFieldsCorner()
        throws InvocationTargetException, IllegalAccessException {
        MinesweeperCoordinates coordinates = new MinesweeperCoordinates(0, 0);
        refAdjustStatus.invoke(handler, coordinates);

        int[][] matrix = handler.getMatrix();
        assertAll("adjustStatusForAdjustingFields",
            () -> assertTrue(matrix[0][1] > 0 || matrix [0][1] == -1),
            () -> assertTrue(matrix[1][1] > 0 || matrix [1][1] == -1),
            () -> assertTrue(matrix[1][0] > 0 || matrix [1][0] == -1)
        );
    }

    @Test
    void testAdjustStatusForAdjustingFieldsNegativeValue()
        throws InvocationTargetException, IllegalAccessException {
        boolean errorThrown = false;
        try {
            refAdjustStatus.invoke(handler, new MinesweeperCoordinates(-2,-2));
        } catch (Exception e) {
            errorThrown = true;
        }
        assertTrue(errorThrown);
    }

    @Test
    void testCreateVisualMatrix()
        throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        handler = new Handler(5,0);
        Class myHandler = handler.getClass();

        refAdjustStatus = myHandler.getDeclaredMethod("adjustStatusForAdjustingFields", MinesweeperCoordinates.class);
        refAdjustStatus.setAccessible(true);

        refCreateVisuals = myHandler.getDeclaredMethod("createFieldForVisuals");
        refCreateVisuals.setAccessible(true);

        Status[][] expectedMatrix = {
                {Status.BOMB   , Status.ONE    , Status.NOTHING, Status.NOTHING , Status.NOTHING},
                {Status.ONE    , Status.TWO    , Status.ONE    , Status.ONE     , Status.NOTHING},
                {Status.NOTHING, Status.ONE    , Status.BOMB   , Status.ONE     , Status.NOTHING},
                {Status.NOTHING, Status.ONE    , Status.ONE    , Status.ONE     , Status.NOTHING},
                {Status.NOTHING, Status.NOTHING, Status.NOTHING, Status.NOTHING , Status.NOTHING}
        };
        refAdjustStatus.invoke(handler, new MinesweeperCoordinates(0,0));
        refAdjustStatus.invoke(handler, new MinesweeperCoordinates(2,2));
        refCreateVisuals.invoke(handler);
        Status[][] actualMatrix = handler.getVisualMatrix();
        assertArrayEquals(expectedMatrix, actualMatrix);
    }

    @Test
    void testIntToStatus(){
        assertAll("intToStatus",
                () -> assertEquals(Status.BOMB, refIntToStatus.invoke(handler, -1)),
                () -> assertEquals(Status.NOTHING, refIntToStatus.invoke(handler, 0)),
                () -> assertEquals(Status.ONE, refIntToStatus.invoke(handler, 1)),
                () -> assertEquals(Status.TWO, refIntToStatus.invoke(handler, 2)),
                () -> assertEquals(Status.THREE, refIntToStatus.invoke(handler, 3)),
                () -> assertEquals(Status.FOUR, refIntToStatus.invoke(handler, 4)),
                () -> assertEquals(Status.FIVE, refIntToStatus.invoke(handler, 5)),
                () -> assertEquals(Status.SIX, refIntToStatus.invoke(handler, 6)),
                () -> assertEquals(Status.SEVEN, refIntToStatus.invoke(handler, 7)),
                () -> assertEquals(Status.EIGHT, refIntToStatus.invoke(handler, 8)),
                () -> assertEquals(Status.BOMB, refIntToStatus.invoke(handler, -25352)),
                () -> assertEquals(Status.BOMB, refIntToStatus.invoke(handler, 99999)),
                () -> assertEquals(Status.BOMB, refIntToStatus.invoke(handler, -2))
        );
    }

    @Test
    void testClickFieldCorrectValues()
        throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        handler = new Handler(5,0);
        Class myHandler = handler.getClass();

        refAdjustStatus = myHandler.getDeclaredMethod("adjustStatusForAdjustingFields", MinesweeperCoordinates.class);
        refAdjustStatus.setAccessible(true);


        refAdjustStatus.invoke(handler, new MinesweeperCoordinates(0,0));
        refAdjustStatus.invoke(handler, new MinesweeperCoordinates(1,1));
        refAdjustStatus.invoke(handler, new MinesweeperCoordinates(2,0));
        refCreateVisuals.invoke(handler);
        assertAll("clickField",
                () -> assertEquals(Status.BOMB, handler.clickField(new MinesweeperCoordinates(0,0))),
                () -> assertEquals(Status.ONE, handler.clickField(new MinesweeperCoordinates(1,2))),
                () -> assertEquals(Status.TWO, handler.clickField(new MinesweeperCoordinates(0,1))),
                () -> assertEquals(Status.THREE, handler.clickField(new MinesweeperCoordinates(1,0)))
        );
    }

    @Test
    void clickOutOfBounds(){
        boolean errorThrown = false;
        try {
            handler.clickField(new MinesweeperCoordinates(-1,-1));
        } catch (IndexOutOfBoundsException e) {
            errorThrown = true;
        }
        assertTrue(errorThrown);
    }

    @Test
    void testMarkField()
        throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        handler = new Handler(5,0);
        Class myHandler = handler.getClass();

        refAdjustStatus = myHandler.getDeclaredMethod("adjustStatusForAdjustingFields", MinesweeperCoordinates.class);
        refAdjustStatus.setAccessible(true);

        refAdjustStatus.invoke(handler, new MinesweeperCoordinates(0,0));
        refAdjustStatus.invoke(handler, new MinesweeperCoordinates(1,1));
        refAdjustStatus.invoke(handler, new MinesweeperCoordinates(2,0));
        refCreateVisuals.invoke(handler);

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
        handler = new Handler(5, 3);
        int[][] newMatrix = {{1, 1, 1}, {1, -1, 1}, {1, 1, 1}};
        handler.setMatrix(newMatrix);
        assertArrayEquals(newMatrix, handler.getMatrix());
    }

    @Test
    void testSetGetVisualMatrix() throws InvocationTargetException, IllegalAccessException {
        handler = new Handler(5, 3);
        int[][] newMatrix = {{1, 1, 1}, {1, -1, 1}, {1, 1, 1}};
        Status[][] visMatrix = {{Status.ONE, Status.ONE, Status.ONE},
                {Status.ONE, Status.BOMB, Status.ONE},
                {Status.ONE, Status.ONE, Status.ONE}};
        handler.setMatrix(newMatrix);
        refCreateVisuals.invoke(handler);
        assertArrayEquals(visMatrix, handler.getVisualMatrix());
    }
}
