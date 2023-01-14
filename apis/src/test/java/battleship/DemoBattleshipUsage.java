package battleship;

import de.hhn.it.devtools.apis.battleship.*;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

import java.util.IllegalFormatException;

/**
 * This usage demo is not runnable because in this module there is no possibility to access the
 * implementation. The runnable demo is accessible in the component's module.
 */

    // Ask Professor how to handle Gamestate parameter in loadGame


public class DemoBattleshipUsage  {

    public static void main(String[] args) throws Exception{

        BattleshipService game = new BattleshipService() {
            @Override
            public void addCallBack(BattleshipListener listener) throws IllegalParameterException {

            }

            @Override
            public void removeCallback(BattleshipListener listener) throws IllegalParameterException {

            }

            @Override
            public boolean isPlacementPossible(Player player, Ship shipToPlace, int x1, int y1, boolean isVertical) throws IllegalGameStateException {
                return false;
            }

            @Override
            public void placeShip(Player player, Ship shipToPlace, int x1, int y1) throws IllegalPositionException, IllegalShipStateException, IllegalGameStateException {

            }

            @Override
            public void unPlace(Player player, Ship shipToMove) throws IllegalArgumentException, IllegalGameStateException {

            }

            @Override
            public void rotateShip(Player player, Ship shipToRotate) throws IllegalPositionException, IllegalShipStateException, IllegalGameStateException {

            }

            @Override
            public boolean bombPanel(Player attacker, Player victim, int x, int y) throws IllegalArgumentException, IllegalGameStateException {
                return false;
            }

            @Override
            public void createFields(int size) throws IllegalArgumentException, IllegalGameStateException, IllegalShipStateException, IllegalPositionException {

            }

            @Override
            public void adjustSoundVolume(int newVolume) throws IllegalArgumentException {

            }

            @Override
            public SavedGame saveGame() throws IllegalGameStateException {
                return null;
            }

            @Override
            public void loadGame(SavedGame savedGame) throws IllegalFormatException {

            }

            @Override
            public void concede() throws IllegalGameStateException {

            }

            @Override
            public String displayRules() {
                return null;
            }
        };

        Player player = new Player();

        Player computer = new Player();


        //Field size is set and the fields are created
        //9x9 field means that 5 ships will be generated for each player
        game.createFields(10);


        //User wants to read the rules before he begins to play
        game.displayRules();


        //User wants to rotate ship before placing it
        game.rotateShip(player, null);


        //User places his ships
        game.placeShip(player,null,1,2);
        game.placeShip(player,null,2,2);
        game.placeShip(player,null,3,2);
        game.placeShip(player,null,4,2);
        game.placeShip(player,null,6,2);


        //User starts the game by bombing the enemy panel and misses
        game.bombPanel(player, computer,4, 7);

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
