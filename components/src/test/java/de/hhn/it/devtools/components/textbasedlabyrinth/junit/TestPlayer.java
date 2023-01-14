package de.hhn.it.devtools.components.textbasedlabyrinth.junit;

import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.Map;
import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.Game;
import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.GameService;
import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.Seed;
import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.OutputListener;
import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.*;
import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.exceptions.RoomFailedException;
import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.exceptions.NoSuchItemFoundException;
import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.exceptions.InvalidSeedException;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPlayer {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(TestPlayer.class);

    private GameService gameService;
    private LayoutGenerator layoutGenerator;
    private Player player;

    @BeforeEach
    void setup() throws InvalidSeedException {
        gameService = new Game();
        gameService.startup();
        gameService.start();
        gameService.setPlayerName("Joe");
        player = gameService.getPlayer();
    }

    @Test
    @DisplayName("Test if correct Player name")
    public void checkPlayerName() {
        assertEquals("Joe", gameService.getPlayerName());
    }

    @Test
    @DisplayName("Test if Player starts in correct Room")
    public void checkPlayerStartPosition(){
        assertEquals(layoutGenerator.getStartRoom(), player.getCurrentRoomOfPlayer());
    }

    @Test
    @DisplayName("Test if Player starts with empty inventory")
    public void checkPlayerStartInventory(){
        assertEquals(0, player.getInventory().size());
    }

    @Test
    @DisplayName("Test if Player picks up items correctly")
    public void checkPlayerPickUpItem() {
        NoSuchItemFoundException exception = assertThrows(NoSuchItemFoundException.class,
                () -> gameService.pickUpItem(1));
    }

    @Test
    @DisplayName("Test if Player picks up items correctly")
    public void checkPlayerRemoveItem() {
        NoSuchItemFoundException exception = assertThrows(NoSuchItemFoundException.class,
                () -> gameService.dropItem(1));
    }

    @Test
    @DisplayName("Test if Player picks up items correctly")
    public void checkPlayerRoomFailedException() {
        RoomFailedException exception = assertThrows(RoomFailedException.class,
                () -> gameService.move(Direction.SOUTH));
    }
}
