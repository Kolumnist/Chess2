package chess2;

import de.hhn.it.devtools.apis.chess2.Chess2Service;
import de.hhn.it.devtools.apis.chess2.Piece;

import java.util.ArrayList;

/**
 * This demo uses all methods and shows 2 different scenarios
 *
 * @author Collin, Lara, Michel
 * @version 1.0
 */

public class DemoChess2Usage {
  public static void main(String[] args) throws Exception {
    Chess2Service service = null;

    service.openRules();
    service.startNewGame();

    ArrayList<int[]> piecePos = new ArrayList<>();
    Piece piece = null;

    /* Starting player picks piece and moves it:
     * 1. We have to activate all current pieceFieldButtons
     * 2. The GUI gets the FieldState
     * 3. Piece is moved on new position
     * More: Players get switched and all fields get their states updated */
    service.activatePieceButton(piecePos);
    service.getFieldState();
    service.moveSelectedPiece(new int[2]);
    piecePos = new ArrayList<>();

    /* Second players turn:
     * 1. We have to activate all current pieceFieldButtons
     * 2. The GUI gets the FieldState
     * 3. Piece is moved on new position
     * 4. A king/queen gets send to jail before the players switch
     * More: Players get switched and all fields get their states updated */
    service.activatePieceButton(piecePos);
    service.getFieldState();
    service.moveSelectedPiece(new int[2]);
    service.setPieceInJail(piece, new int[2]);
    piecePos = new ArrayList<>();

    /* Starting players turn:
     * 1. He gives up
     * 2. Win screen pops up and gives 2 options:
     *   #Play again
     *   #End
     * 3. Pressed on Play again, game resets */
    service.giveUp();
    service.showWinningPlayer();
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
    service.activatePieceButton(piecePos);
    service.getFieldState();
    service.moveSelectedPiece(new int[2]);
    service.activatePieceButton(piecePos);
    service.getFieldState();
    service.moveSelectedPiece(new int[2]);
    service.setPieceInJail(piece, new int[2]);
    service.showWinningPlayer();
    service.endGame();

  }
}