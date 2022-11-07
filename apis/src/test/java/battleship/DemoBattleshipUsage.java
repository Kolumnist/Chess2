package battleship;

import de.hhn.it.devtools.apis.battleship.*;
/**
 * This usage demo is not runnable because in this module there is no possibility to access the
 * implementation. The runnable demo is accessible in the components module.
 */

    // Ask Professor how to handle Gamestate parameter in loadGame


public class DemoBattleshipUsage  {
    static BattleshipService game = null;

    public static void main(String[] args) throws Exception{


        //Field size is set and the fields are created
        //9x9 field means that 5 ships will be generated for each player
        game.createFields(9);


        //User wants to read the rules before he begins to play
        game.displayRules();


        //User wants to rotate ship before placing it
        game.rotateShip(null);


        //User places his ships
        game.placeShip(null,1,2);
        game.placeShip(null,2,2);
        game.placeShip(null,3,2);
        game.placeShip(null,4,2);
        game.placeShip(null,6,2);


        //User starts the game by bombing the enemy panel and misses
        game.bombPanel(4,7);

        //Sound volume was too loud, user wants to adjust it
        game.adjustSoundVolume(30);


        //User wants to save the current game state
        game.saveGame();

        //User loads the last game state
        game.loadGame(null);

        //User thinks that he doesn't have any chance and concede
        game.concede();
    }
}
