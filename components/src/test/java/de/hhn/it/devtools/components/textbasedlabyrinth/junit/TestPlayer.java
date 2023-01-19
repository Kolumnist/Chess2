package de.hhn.it.devtools.components.textbasedlabyrinth.junit;

import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.*;
import de.hhn.it.devtools.apis.textbasedlabyrinth.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPlayer {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(TestPlayer.class);

    private Game game;
    private Player player;
    private Item testItem;

    @BeforeEach
    void setup() {
        game = new Game();
        game.startup();
        game.start();
        game.setPlayerName("Joe");
        player = game.getPlayer();
        testItem = new Item(777, "testItem", "testInfo");
    }

    @Test
    @DisplayName("Test if correct Player name")
    public void checkPlayerName() {
        assertEquals("Joe", game.getPlayerName());
    }

    @Test
    @DisplayName("Test if Player starts in correct Room")
    public void checkPlayerStartPosition(){
        assertEquals(game.getCurrentRoom(), player.getCurrentRoomOfPlayer());
    }

    @Test
    @DisplayName("Test if Player starts with empty inventory")
    public void checkPlayerStartInventory(){
        assertEquals(0, player.getInventory().size());
    }

    @Test
    @DisplayName("Test if Player picks up items correctly")
    public void checkPlayerPickUpItem() {
        player.addItem(testItem);
        assertEquals(1, player.getInventory().size());
    }

    @Test
    @DisplayName("Test if Player removeItem invokes NoSuchItemException correctly")
    public void checkPlayerRemoveItemBadCase() {
        NoSuchItemFoundException exception = assertThrows(NoSuchItemFoundException.class,
                () -> player.removeItem(1));
    }

    @Test
    @DisplayName("Test if Player returns correct Item with getItem()")
    public void checkPlayerGetItem() throws NoSuchItemFoundException {
        player.addItem(testItem);
        assertEquals(testItem, player.getItem(777));
    }
}
