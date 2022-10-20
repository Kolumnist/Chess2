package battleship;

import de.hhn.it.devtools.apis.battleship.*;
/**
 * This usage demo is not runnable because in this module there is no possibility to access the
 * implementation. The runnable demo is accessible in the components module.
 */


public class DemoBattleshipUsage  {
    static BattleshipService game = null;

    public static void main(String[] args) throws Exception{

        //User wants to play against computer and chooses Player vs Computer
        game.setGameMode(GameMode.PVC);

        //Field size is set and the fields are created
        //9x9 field means that 5 ships will be generated for each player
        game.createFields(9, game.getGameMode());

        //User wants to read the rules before he begins to play
        game.displayRules();

        //User selects a ship to move
        //User wants to rotate the selected ship
        game.rotateShip();

        //User places the selected ship and moves the other ships as well
        game.placeShip();
        game.placeShip();
        game.placeShip();
        game.placeShip();
        game.placeShip();

        //User starts the game by bombing the enemy panel and misses
        game.bombPanel();

        //Sound volume was too loud, user wants to adjust it
        game.adjustSoundVolume(30);

        //Enemy (here: computer) bombs the users panel and hits
        game.bombPanel();

        //Enemy bombs the users panel and hits
        game.bombPanel();

        //User wants to save the current game state
        game.saveGame();

        //User loads the last game state
        game.loadGame(null);

        //User thinks that he doesn't have any chance and concede
        game.concede();
    }
}
