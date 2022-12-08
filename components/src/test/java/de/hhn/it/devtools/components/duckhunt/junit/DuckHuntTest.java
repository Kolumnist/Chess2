package de.hhn.it.devtools.components.duckhunt.junit;

import de.hhn.it.devtools.apis.duckhunt.*;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.components.duckhunt.DuckHunt;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DuckHuntTest {
    DuckHunt game;
    DuckHuntListener testListener;

    @BeforeEach
    void setUp() {
      game = new DuckHunt();
      testListener = new DuckHuntListenerTest();
    }

    @AfterEach
    void tearDown() {
    }

    @Test @Order(0)
    void addCallbackTest() {
        assertDoesNotThrow(() -> game.addCallback(testListener));

        assertThrowsExactly(IllegalParameterException.class, () -> game.addCallback(null));
    }

    @Test @Order(1)
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
    void reloadTest() {
    }

    @Test
    void startGameTest() {

    }

    @Test
    void stopGameTest() {
    }

    @Test
    void pauseGameTest() {
    }

    @Test
    void continueGameTest() {
    }

    @Test
    void changeGameSettingsTest() {
    }


}

class DuckHuntListenerTest implements DuckHuntListener {
    public GameInfo gameInfo;
    public DucksInfo duckPosition;

    @Override
    public void newState(GameInfo gameInfo) throws IllegalGameInfoException {
        this.gameInfo = gameInfo;
    }

    @Override
    public void newDuckPosition(DucksInfo duckPosition) throws IllegalDuckPositionException {
        this.duckPosition = duckPosition;
    }

    @Override
    public void duckHit(int id) throws IllegalDuckIdException {

    }
}