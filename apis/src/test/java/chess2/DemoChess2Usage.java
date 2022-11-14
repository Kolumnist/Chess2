package chess2;

import de.hhn.it.devtools.apis.chess2.Chess2Service;
import de.hhn.it.devtools.apis.chess2.Coordinate;
import de.hhn.it.devtools.apis.chess2.FieldState;
import de.hhn.it.devtools.apis.chess2.Piece;

import java.util.ArrayList;

/**
 * This demo uses all methods and shows 2 different scenarios.
 *
 * @author Collin, Lara, Michel
 * @version 1.1
 */

public class DemoChess2Usage {
  public static void main(String[] args) throws Exception {
    Chess2Service service = null;

    service.startNewGame();

    Coordinate[] pieceCoordinates = new Coordinate[17];
    Coordinate[] possibleMoves = new Coordinate[0]; // A piece can have 0 - 61 possible moves
    Piece piece = null;
    FieldState fieldState = null;

    /* Starting player picks piece and moves it:
     * 1. We have to activate all current Buttons
     * 2. The GUI gets the FieldState
     * 3. Piece is moved on new position
     * More: Players get switched and all fields get their states updated */
    pieceCoordinates = service.getCurrentFields();
    possibleMoves = service.getPossibleMoves(pieceCoordinates[1]);
    fieldState = service.getFieldState(pieceCoordinates[2]);
    service.moveSelectedPiece(possibleMoves[2]);

    /* Second players turn:
     * 1. We have to activate all current pieceFieldButtons
     * 2. The GUI gets the FieldState
     * 3. Piece is moved on new position
     * 4. A king/queen gets send to jail before the players switch
     * More: Players get switched and all fields get their states updated */
    pieceCoordinates = service.getCurrentFields();
    possibleMoves = service.getPossibleMoves(pieceCoordinates[8]);
    fieldState = service.getFieldState(pieceCoordinates[10]);
    service.setPieceInJail(possibleMoves[10], pieceCoordinates[67]);
    service.moveSelectedPiece(possibleMoves[10]);

    /* Starting players turn:
     * 1. He gives up
     * 2. Win screen pops up and gives 2 options:
     *   #Play again
     *   #End
     * 3. Pressed on Play again, game resets */
    service.giveUp();
    service.getWinningPlayer();
    service.reset();

    //.......a couple standard turns are made

    /* Any player picks the monkey and moves with it:
     * 1. We have to activate all current pieceFieldButtons
     * 2. The GUI gets the FieldState
     * 3. Monkey is moved on new position
     * 4. The moved piece was a monkey and jumped over another piece:
     *   # We have to activate only the monkey
     *   # Player is not switched
     *   # Monkey's move can only be a jump
     * 5. Monkey is moved on new position that has a king on it
     * 6. The king gets send to jail before the players switch
     * More: A player loses, player ends the game completely */
    pieceCoordinates = service.getCurrentFields();
    possibleMoves = service.getPossibleMoves(pieceCoordinates[21]);
    fieldState = service.getFieldState(pieceCoordinates[37]);
    service.moveSelectedPiece(possibleMoves[37]);
    possibleMoves = service.getPossibleMoves(pieceCoordinates[37]);
    fieldState = service.getFieldState(pieceCoordinates[53]);
    service.setPieceInJail(possibleMoves[53], pieceCoordinates[67]);
    service.moveSelectedPiece(possibleMoves[53]);
    service.getWinningPlayer();
    service.endGame();
  }
}