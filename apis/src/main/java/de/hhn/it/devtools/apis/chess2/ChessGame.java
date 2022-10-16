package de.hhn.it.devtools.apis.chess2;

/**
 * This class give all the information the players needs to know
 *
 * @author Collin, Lara, Michel
 * @version 0.1
 */

public class ChessGame {


    /**
     * Returns the player who's on turn
     *
     * @return the player who's on turn
     */
    public Player getStartingPlayer(){
        return this.startingPlayer;
    }


    /*
    startGame() - MainMenu
    openRules() - MainMenu
    closeRules() - MainMenu

    getCurrentPlayer() - ChessGame
    //both Players are local variables of ChessGame
    //after every Players Turn they get switched

    setCurrentPiece(int x, int y) - (as long as it is played locally) : ChessGame
    //Only if a Piece is clicked on:
    //The current piece is just a reference on the position
    //meaning it just sets a current position of the mouse clicked

    isCurrentPlayersPiece() - Player
    //When a click on a Piece is made it checks if
    //that piece is a Piece of the current Player

    ?changeFieldStatus()? //how can we get possible paths so that it gets displayed

    setCurrentFieldPos(int x, int y) - (as long as it is played locally) : ChessGame
    //A or two position variable gets set

    getFieldStatus() - ?ChessGame?

    setCurrentPiecePos(int x, int y) - Player


    setLostPiece() - Player
    //Sets the other Players boolean to true and
    //the currentPlayers to false

    switchCurrentPlayer() - ChessGame
    //changes the currentPlayer variable

    \\\isPossibleField() - Piece\\\
    */

}
