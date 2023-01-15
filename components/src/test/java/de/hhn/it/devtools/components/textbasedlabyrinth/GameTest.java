package de.hhn.it.devtools.components.textbasedlabyrinth;


import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.Game;
import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.Player;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class GameTest {



    private Game game;
    private Player player;





    @BeforeAll
    void preTest() {
        game = new Game();
        player = game.getPlayer();

    }


    @Test
    public void testStartup() {
        game.startup();
        assertNotNull(player);
        assertInstanceOf(Player.class, player);
    }









}
