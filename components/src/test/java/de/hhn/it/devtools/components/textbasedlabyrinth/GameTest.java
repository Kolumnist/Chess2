package de.hhn.it.devtools.components.textbasedlabyrinth;



import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.Game;
import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.Player;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class GameTest {



    private Game game;
    private Player player;





    @BeforeAll
    public void preTest() {
        game = new Game();
        player = null;

    }

    @BeforeAll
    @Test
    public void testInitilization() {
        game.startup();

    }









}
