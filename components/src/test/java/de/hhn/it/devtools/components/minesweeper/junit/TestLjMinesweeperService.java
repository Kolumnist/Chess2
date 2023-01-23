package de.hhn.it.devtools.components.minesweeper.junit;

import de.hhn.it.devtools.apis.minesweeper.MinesweeperCoordinates;
import de.hhn.it.devtools.apis.minesweeper.Status;
import de.hhn.it.devtools.components.minesweeper.provider.LjMinesweeperService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Testing LjMinesweeperService")
public class TestLjMinesweeperService {
    LjMinesweeperService service;

    @BeforeEach
    void init(){
        service = new LjMinesweeperService(5,100,5);
    }

    @Test
    void testCreateGame() {
        assertAll("createGame",
                () -> Assertions.assertNotNull(service.getHandler().getVisualMatrix()),
                () -> Assertions.assertNotNull(service.getHandler().getVisualMatrix())
        );
    }

    @Test
    void testStartGame(){
        service.startGame(10,100,10);
        testCreateGame();
    }

    @Test
    void testRestartGame(){
        service.restart();
        testCreateGame();
    }

    @Test
    void testClickField(){
        Status status = service.clickField(new MinesweeperCoordinates(0, 0));
        Assertions.assertNotNull(status);
    }

    @Test
    void testMarkField() {
        Status status = service.markField(new MinesweeperCoordinates(0, 0));
        Assertions.assertNotNull(status);
    }
}