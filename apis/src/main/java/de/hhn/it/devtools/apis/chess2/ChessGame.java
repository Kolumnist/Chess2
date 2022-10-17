package de.hhn.it.devtools.apis.chess2;

/**
 * This class give all the information the players needs to know
 *
 * @author Collin, Lara, Michel
 * @version 0.1
 */

public class ChessGame {

    private Player player1;
    private Player player2;

    private Player currentPlayer;
    private Field fieldStatus;
    private Field currentFieldPos;
    private Piece currentPiece;


    protected boolean running = false;
    protected boolean startingPlayer = true;

    /**
     * Returns the player who's at the beginning of the game on turn
     *
     * @return the player who's at the beginning of the game on turn
     */
    public boolean getStartingPlayer(){
        return this.startingPlayer;
    }

    /**
     * Returns the player who's on turn
     *
     * @return the player who's on turn
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Returns the player who's turn is next
     *
     * @return the player who's turn is next
     */
    public void switchCurrentPlayer(Player player1, Player player2){

    }

    /**
     * Returns the status of a field who's clicked on (status = example: is this field free?)
     *
     * @return the status of a field who's clicked on
     */
    public Field getFieldStatus() {
        return fieldStatus;
    }

    /**
     * set the new status of the field
     *
     * @set the new status of the field
     */
    public void setFieldStatus(Field fieldStatus) {
        this.fieldStatus = fieldStatus;
    }

    /**
     * Returns the field who's clicked on
     *
     * @return the field who's clicked on
     */
    public Field getCurrentFieldPos() {
        return currentFieldPos;
    }

    public void setCurrentFieldPos(int x, int y) {
        this.currentFieldPos = currentFieldPos;
    }

    public void setCurrentPiece(int x, int y) {
        this.currentPiece = currentPiece;
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
