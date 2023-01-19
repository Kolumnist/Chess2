package de.hhn.it.devtools.components.minesweeper.junit;

import components.minesweeper.provider.Handler;
import components.minesweeper.provider.LJMinesweeperService;
import de.hhn.it.devtools.apis.minesweeper.MinesweeperCoordinates;
import de.hhn.it.devtools.apis.minesweeper.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.Provider;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Testing LJMinesweeperService")
public class TestLJMinesweeperService {
    LJMinesweeperService service;

    @BeforeEach
    void init(){
        service = new LJMinesweeperService(5,100,5);
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
        service.startGame();
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
