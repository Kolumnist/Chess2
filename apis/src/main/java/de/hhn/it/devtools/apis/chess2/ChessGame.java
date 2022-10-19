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
     * @return currentPlayer on turn
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Returns the player who's turn is next
     *
     * @return the player who's turn is next
     */
    public void switchCurrentPlayer(){
        this.currentPlayer = (currentPlayer == player1) ? player2 : player1;
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
    void startNewGame()
    //erstellt ChessGame Instanz
    //erstellt Spieler und legt Anfangsspieler fest
    //erstellt Board
    //setzt Felder, deren Positionen und deren Status
    //setzt Figuren, deren Position (((und deren Rolle)))
    //wechsel aktuelle Szene (Endscreen = andere Szene?)

    void reset()
    //legt Anfangsspieler fest
    //setzt Status aller Felder zurück auf den Startwert
    //setzt Figuren, deren Position (((und deren Rolle)))

    void changeNavigation(ArrayList<int[]> fieldPos)
    //ändert alle States der bewegbaren Felder, von der ausgewählten Figur
    //

    FieldState getFieldState();

    ArrayList<int[]> getPossiblePiecePosition(Piece piece) ?????

    setPiecePos() -init?
    //removeOtherPiece()
    //Position von der aktuellen Figur wird geändert
    //beachte Affe!!!
    //switchCurrentPlayer()

    giveUp()
    //kürt Sieger
    //ruft reset auf oder beendet aktuelle Instanz von ChessGame


    NUR FELDER ALS "BUTTON", NICHT FIGUREN
    FIGUREN BEWEGUNG WIRD BEI ZUGSTART DES AKTUELLEN SPIELERS AKTUALISIERT
    AFFE IST BESONDERS, MUSS IMMER AKTUALISIERT WERDEN ;(

    TRY TO GET A PIECE FROM THE CURRENT FIELD

    openRules nachfragen
    */
}