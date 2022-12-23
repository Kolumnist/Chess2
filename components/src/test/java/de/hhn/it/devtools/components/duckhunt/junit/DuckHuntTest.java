package de.hhn.it.devtools.components.duckhunt.junit;

import de.hhn.it.devtools.apis.duckhunt.*;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.components.duckhunt.DuckHunt;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class DuckHuntTest {
    DuckHunt game;
    DuckHuntListenerTest testListener;

    @BeforeEach
    void setUp() {
        game = new DuckHunt();
        testListener = new DuckHuntListenerTest();
    }

    @AfterEach
    void tearDown() {
        game.stopGame();
    }

    @Test
    @Order(0)
    void addCallbackTest() {
        assertDoesNotThrow(() -> game.addCallback(testListener));

        assertThrowsExactly(IllegalParameterException.class, () -> game.addCallback(null));
    }

    @Test
    @Order(1)
    void removeCallbackTest() throws IllegalParameterException {
        game.addCallback(testListener);

        assertDoesNotThrow(() -> game.removeCallback(testListener));

        assertThrowsExactly(IllegalParameterException.class, () -> game.removeCallback(null));
    }

    @Test
    void shootTest() {
    }

    @Test
    void shootObstacleTest() {
    }

    @Test
    void reloadTest() throws IllegalParameterException, InterruptedException {
        game.addCallback(testListener);
        game.shootObstacle();
        game.reload();
        Thread.sleep(1000);
        assertEquals(3, testListener.gameInfo.getAmmo());
    }

    @Test
    void startGameTest() throws IllegalParameterException, InterruptedException {
        game.addCallback(testListener);
        game.startGame();
        int callCount = testListener.newStateCallCount;
        Thread.sleep(1000);
        assertNotNull(testListener.gameInfo);
        assertNotNull(testListener.duckPosition);
        assertEquals(1, testListener.duckPosition.duckData().length);
        assertTrue(callCount < testListener.newStateCallCount);
    }

    @Test
    void stopGameTest() throws IllegalParameterException, InterruptedException {
        game.addCallback(testListener);
        game.startGame();
        Thread.sleep(1000);
        game.stopGame();
        Thread.sleep(1000);
        int callCount = testListener.newStateCallCount;
        Thread.sleep(1000);
        assertEquals(callCount, testListener.newStateCallCount);
    }

    @Test
    void pauseGameTest() throws IllegalParameterException, InterruptedException {
        game.addCallback(testListener);
        game.startGame();
        Thread.sleep(1000);
        game.pauseGame();
        Thread.sleep(1000);
        int callCount = testListener.newStateCallCount;
        Thread.sleep(1000);
        assertEquals(callCount, testListener.newStateCallCount);
        game.continueGame();
        Thread.sleep(1000);
        game.pauseGame();
        Thread.sleep(1000);
        callCount = testListener.newStateCallCount;
        Thread.sleep(1000);
        assertEquals(callCount, testListener.newStateCallCount);
    }

    @Test
    void continueGameTest() throws IllegalParameterException, InterruptedException {
        game.addCallback(testListener);
        game.startGame();
        Thread.sleep(1000);
        game.pauseGame();
        Thread.sleep(1000);
        int callCount = testListener.newStateCallCount;
        Thread.sleep(1000);
        assertEquals(callCount, testListener.newStateCallCount);
        game.continueGame();
        Thread.sleep(1000);
        game.pauseGame();
        Thread.sleep(1000);
        assertTrue(callCount < testListener.newStateCallCount);
    }

    @Test
    void changeGameSettingsTest() {
    }


}

class DuckHuntListenerTest implements DuckHuntListener {
    public GameInfo gameInfo;
    public int newStateCallCount = 0;
    public DucksInfo duckPosition;
    public int newDuckPositionCallCount = 0;

    @Override
    public void newState(GameInfo gameInfo) throws IllegalGameInfoException {
        this.gameInfo = gameInfo;
        newStateCallCount++;
    }

    @Override
    public void newDuckPosition(DucksInfo duckPosition) throws IllegalDuckPositionException {
        this.duckPosition = duckPosition;
        System.out.println(duckPosition.duckData()[0]);
        newDuckPositionCallCount++;
    }

    @Override
    public void duckHit(int id) throws IllegalDuckIdException {

    }
}