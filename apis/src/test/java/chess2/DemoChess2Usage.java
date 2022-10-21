package chess2;

import de.hhn.it.devtools.apis.chess2.Chess2Service;
import de.hhn.it.devtools.apis.chess2.Piece;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

import java.util.ArrayList;

public class DemoChess2Usage {

  public static void main(String[] args) throws Exception {
    Chess2Service chess2 = null;

    chess2.openRules();
    chess2.startNewGame();

    ArrayList<int[]> piecePos = new ArrayList<>();
    int[] pos = new int[2];
    Piece piece = null;

    // first player choose piece and moves it:
    chess2.activatePieceButton(piecePos);  // we activate all Piece Buttons so the current Player can use his Pieces
    chess2.getFieldState();
    chess2.setPiecePosition(pos);   // the pice of the current Player get a new position
    // now the players get switched

    //second player's turn:
    chess2.activatePieceButton(piecePos);  // we deactivate all Piece Buttons of the player before and activate all piece buttons of the current player
    chess2.getFieldState();
    chess2.setPiecePosition(pos);   // the pice of the current Player get a new position
    chess2.setPieceInJail(piece,pos);  // the current player catch the king/queen of the other player and throws him into jail
    // now the players get switched

    chess2.giveUp();             // the current player give up
    chess2.showWinningPlayer();  // now the winning is showed up
    chess2.reset();              // question if the players like to play again

  }
}
