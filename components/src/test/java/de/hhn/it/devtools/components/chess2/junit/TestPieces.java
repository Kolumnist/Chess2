package de.hhn.it.devtools.components.chess2.junit;

import de.hhn.it.devtools.apis.chess2.Board;
import de.hhn.it.devtools.apis.chess2.Chess2Service;
import de.hhn.it.devtools.apis.chess2.Coordinate;
import de.hhn.it.devtools.apis.chess2.Piece;
import de.hhn.it.devtools.components.chess2.ChessGame;
import de.hhn.it.devtools.components.chess2.pieces.Bear;
import de.hhn.it.devtools.components.chess2.pieces.Crow;
import de.hhn.it.devtools.components.chess2.pieces.Elephant;
import de.hhn.it.devtools.components.chess2.pieces.Fish;
import de.hhn.it.devtools.components.chess2.pieces.Fishqueen;
import de.hhn.it.devtools.components.chess2.pieces.King;
import de.hhn.it.devtools.components.chess2.pieces.Queen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPieces {

  ChessGame chessGame;
  Chess2Service chess2Service;
  Board board;

  @BeforeEach
  public void setUp(){
    chessGame = new ChessGame();
    chess2Service = chessGame;
    board = chess2Service.startNewGame();
  }

  //region King
  @Test
  @DisplayName("Test if calculate produces the right coordinates if the king stands on the field 4, 4")
  public void testCalculateKingInTheMiddleOfTheBoard(){
    Piece king = new King('b',new Coordinate(4,4), true);
    Coordinate[] expected = {new Coordinate(3,3),new Coordinate(3,4),
            new Coordinate(3,5),new Coordinate(4,3),new Coordinate(4,5),
            new Coordinate(5,3),new Coordinate(5,4),new Coordinate(5,5)};
    king.calculate(board);
    for (int i = 0; i < king.getPossibleMove().length; i++) {
      assertEquals(expected[i].getX(),king.getPossibleMove()[i].getX());
      assertEquals(expected[i].getY(),king.getPossibleMove()[i].getY());
    }
  }

  @Test
  @DisplayName("Test if calculate produces the right coordinates if the king stands on the field 0, 6")
  public void testCalculateKingOnTheSideOfTheBoard(){
    Piece king = new King('b',new Coordinate(0,6), true);
    Coordinate[] expected = {new Coordinate(0,5),new Coordinate(0,7),
            new Coordinate(1,5),new Coordinate(1,6),new Coordinate(1,7)};
    king.calculate(board);
    for (int i = 0; i < king.getPossibleMove().length; i++) {
      assertEquals(expected[i].getX(),king.getPossibleMove()[i].getX());
      assertEquals(expected[i].getY(),king.getPossibleMove()[i].getY());
    }
  }

  @Test
  @DisplayName("Test if calculate produces the right coordinates if the king stands on the field 7, 7")
  public void testCalculateKingInTheCornerOfTheBoard(){
    Piece king = new King('b',new Coordinate(7,7), true);
    Coordinate[] expected = {new Coordinate(6,6),new Coordinate(6,7),
            new Coordinate(7,6)};
    king.calculate(board);
    for (int i = 0; i < king.getPossibleMove().length; i++) {
      assertEquals(expected[i].getX(),king.getPossibleMove()[i].getX());
      assertEquals(expected[i].getY(),king.getPossibleMove()[i].getY());
    }
  }

  @Test
  @DisplayName("Test if calculate produces the right coordinates if the king stands on an invalid field")
  public void testCalculateKingOnInvalidField(){
    Piece king = new King('b',new Coordinate(-5,9), true);
    king.calculate(board);
    for (int i = 0; i < king.getPossibleMove().length; i++) {
      assertEquals(null,king.getPossibleMove()[i]);
    }
  }
  //endregion

  //region Queen
  @Test
  @DisplayName("Test if calculate produces the right coordinates if the queen stand on the field 4, 4")
  public void testCalculateQueenInTheMiddleOfTheBoard(){
    Piece queen = new Queen('b',new Coordinate(4,4));
    Coordinate[] expected = {new Coordinate(3,3),new Coordinate(3,4),new Coordinate(3,5),new Coordinate(4,3),new Coordinate(4,5),new Coordinate(5,3),new Coordinate(5,4),new Coordinate(5,5),new Coordinate(2,2),new Coordinate(2,4),new Coordinate(2,6),new Coordinate(4,2),new Coordinate(4,6),new Coordinate(6,2),new Coordinate(6,4),new Coordinate(6,6),new Coordinate(1,1),new Coordinate(1,4),new Coordinate(1,7),new Coordinate(4,1),new Coordinate(4,7),new Coordinate(7,1),new Coordinate(7,4),new Coordinate(7,7),new Coordinate(0,0),new Coordinate(0,4),new Coordinate(4,0)};
    queen.calculate(board);
    for (int i = 0; i < queen.getPossibleMove().length; i++) {
      assertEquals(expected[i].getX(), queen.getPossibleMove()[i].getX());
      assertEquals(expected[i].getY(), queen.getPossibleMove()[i].getY());
    }
  }

  @Test
  @DisplayName("Test if calculate produces the right coordinates if the queen stand on the field 0, 6")
  public void testCalculateQueenOnTheSideOfTheBoard(){
    Piece queen = new Queen('b',new Coordinate(0,6));
    Coordinate[] expected = {new Coordinate(0,5),new Coordinate(0,7),new Coordinate(1,5),new Coordinate(1,6),new Coordinate(1,7),new Coordinate(0,4),new Coordinate(2,4),new Coordinate(2,6),new Coordinate(0,3),new Coordinate(3,3),new Coordinate(3,6),new Coordinate(0,2),new Coordinate(4,2),new Coordinate(4,6),new Coordinate(0,1),new Coordinate(5,1),new Coordinate(5,6),new Coordinate(0,0),new Coordinate(6,0),new Coordinate(6,6),new Coordinate(7,6)};
    queen.calculate(board);
    for (int i = 0; i < queen.getPossibleMove().length; i++) {
      assertEquals(expected[i].getX(), queen.getPossibleMove()[i].getX());
      assertEquals(expected[i].getY(), queen.getPossibleMove()[i].getY());
    }
  }

  @Test
  @DisplayName("Test if calculate produces the right coordinates if the queen stand on the field 7, 7")
  public void testCalculateQueenInTheCornerOfTheBoard(){
    Piece queen = new Queen('b',new Coordinate(7,7));
    Coordinate[] expected = {new Coordinate(6,6),new Coordinate(6,7),new Coordinate(7,6),new Coordinate(5,5),new Coordinate(5,7),new Coordinate(7,5),new Coordinate(4,4),new Coordinate(4,7),new Coordinate(7,4),new Coordinate(3,3),new Coordinate(3,7),new Coordinate(7,3),new Coordinate(2,2),new Coordinate(2,7),new Coordinate(7,2),new Coordinate(1,1),new Coordinate(1,7),new Coordinate(7,1),new Coordinate(0,0),new Coordinate(0,7),new Coordinate(7,0)};
    queen.calculate(board);
    for (int i = 0; i < queen.getPossibleMove().length; i++) {
      assertEquals(expected[i].getX(), queen.getPossibleMove()[i].getX());
      assertEquals(expected[i].getY(), queen.getPossibleMove()[i].getY());
    }
  }
  //endregion Queen

  //region Fishqueen
  @Test
  @DisplayName("Test if calculate produces the right coordinates if the fishqueen stand on the field 4, 4")
  public void testCalculateFishqueenInTheMiddleOfTheBoard(){
    Piece fishqueen = new Fishqueen('b',new Coordinate(4,4));
    Coordinate[] expected = {new Coordinate(3,3),new Coordinate(3,4),new Coordinate(3,5),new Coordinate(4,3),new Coordinate(4,5),new Coordinate(5,3),new Coordinate(5,4),new Coordinate(5,5),new Coordinate(2,2),new Coordinate(2,4),new Coordinate(2,6),new Coordinate(4,2),new Coordinate(4,6),new Coordinate(6,2),new Coordinate(6,4),new Coordinate(6,6),new Coordinate(1,1),new Coordinate(1,4),new Coordinate(1,7),new Coordinate(4,1),new Coordinate(4,7),new Coordinate(7,1),new Coordinate(7,4),new Coordinate(7,7),new Coordinate(0,0),new Coordinate(0,4),new Coordinate(4,0)};
    fishqueen.calculate(board);
    for (int i = 0; i < fishqueen.getPossibleMove().length; i++) {
      assertEquals(expected[i].getX(), fishqueen.getPossibleMove()[i].getX());
      assertEquals(expected[i].getY(), fishqueen.getPossibleMove()[i].getY());
    }
  }

  @Test
  @DisplayName("Test if calculate produces the right coordinates if the fishqueen stand on the field 0, 6")
  public void testCalculateFishqueenOnTheSideOfTheBoard(){
    Piece fishqueen = new Fishqueen('b',new Coordinate(0,6));
    Coordinate[] expected = {new Coordinate(0,5),new Coordinate(0,7),new Coordinate(1,5),new Coordinate(1,6),new Coordinate(1,7),new Coordinate(0,4),new Coordinate(2,4),new Coordinate(2,6),new Coordinate(0,3),new Coordinate(3,3),new Coordinate(3,6),new Coordinate(0,2),new Coordinate(4,2),new Coordinate(4,6),new Coordinate(0,1),new Coordinate(5,1),new Coordinate(5,6),new Coordinate(0,0),new Coordinate(6,0),new Coordinate(6,6),new Coordinate(7,6)};
    fishqueen.calculate(board);
    for (int i = 0; i < fishqueen.getPossibleMove().length; i++) {
      assertEquals(expected[i].getX(), fishqueen.getPossibleMove()[i].getX());
      assertEquals(expected[i].getY(), fishqueen.getPossibleMove()[i].getY());
    }
  }

  @Test
  @DisplayName("Test if calculate produces the right coordinates if the fishqueen stand on the field 7, 7")
  public void testCalculateFishqueenInTheCornerOfTheBoard(){
    Piece fishqueen = new Fishqueen('b',new Coordinate(7,7));
    Coordinate[] expected = {new Coordinate(6,6),new Coordinate(6,7),new Coordinate(7,6),new Coordinate(5,5),new Coordinate(5,7),new Coordinate(7,5),new Coordinate(4,4),new Coordinate(4,7),new Coordinate(7,4),new Coordinate(3,3),new Coordinate(3,7),new Coordinate(7,3),new Coordinate(2,2),new Coordinate(2,7),new Coordinate(7,2),new Coordinate(1,1),new Coordinate(1,7),new Coordinate(7,1),new Coordinate(0,0),new Coordinate(0,7),new Coordinate(7,0)};
    fishqueen.calculate(board);
    for (int i = 0; i < fishqueen.getPossibleMove().length; i++) {
      assertEquals(expected[i].getX(), fishqueen.getPossibleMove()[i].getX());
      assertEquals(expected[i].getY(), fishqueen.getPossibleMove()[i].getY());
    }
  }
  //endregion Fishqueen

  //region Elephant
  @Test
  @DisplayName("Test if calculate produces the right coordinates if the elephant stand on the field 4, 4")
  public void testCalculateElephantInTheMiddleOfTheBoard(){
    Piece elephant = new Elephant('b',new Coordinate(4,4));
    Coordinate[] expected = {new Coordinate(2,2),new Coordinate(2,4),new Coordinate(2,6),new Coordinate(4,2),new Coordinate(4,6),new Coordinate(6,2),new Coordinate(6,4),new Coordinate(6,6)};
    elephant.calculate(board);
    for (int i = 0; i < elephant.getPossibleMove().length; i++) {
      assertEquals(expected[i].getX(), elephant.getPossibleMove()[i].getX());
      assertEquals(expected[i].getY(), elephant.getPossibleMove()[i].getY());
    }
  }

  @Test
  @DisplayName("Test if calculate produces the right coordinates if the elephant stand on the field 0, 6")
  public void testCalculateElephantOnTheSideOfTheBoard(){
    Piece elephant = new Elephant('b',new Coordinate(0,6));
    Coordinate[] expected = {new Coordinate(0,4),new Coordinate(2,4),new Coordinate(2,6)};
    elephant.calculate(board);
    for (int i = 0; i < elephant.getPossibleMove().length; i++) {
      assertEquals(expected[i].getX(), elephant.getPossibleMove()[i].getX());
      assertEquals(expected[i].getY(), elephant.getPossibleMove()[i].getY());
    }
  }

  @Test
  @DisplayName("Test if calculate produces the right coordinates if the elephant stand on the field 7, 7")
  public void testCalculateElephantInTheCornerOfTheBoard(){
    Piece elephant = new Elephant('b',new Coordinate(7,7));
    Coordinate[] expected = {new Coordinate(5,5),new Coordinate(5,7),new Coordinate(7,5)};
    elephant.calculate(board);
    for (int i = 0; i < elephant.getPossibleMove().length; i++) {
      assertEquals(expected[i].getX(), elephant.getPossibleMove()[i].getX());
      assertEquals(expected[i].getY(), elephant.getPossibleMove()[i].getY());
    }
  }
  //endregion Elephant

  //region black Fish
  @Test
  @DisplayName("Test if calculate produces the right coordinates if the black fish stand on the field 4, 4")
  public void testCalculateBlackFishInTheMiddleOfTheBoard(){
    Piece fish = new Fish('b',new Coordinate(4,4));
    Coordinate[] expected = {new Coordinate(3,3),new Coordinate(3,4),new Coordinate(5,3),new Coordinate(5,4)};
    fish.calculate(board);
    for (int i = 0; i < fish.getPossibleMove().length; i++) {
      assertEquals(expected[i].getX(), fish.getPossibleMove()[i].getX());
      assertEquals(expected[i].getY(), fish.getPossibleMove()[i].getY());
    }
  }

  @Test
  @DisplayName("Test if calculate produces the right coordinates if the black fish stand on the field 0, 6")
  public void testCalculateBlackFishOnTheSideOfTheBoard(){
    Piece fish = new Fish('b',new Coordinate(0,6));
    Coordinate[] expected = {new Coordinate(1,5),new Coordinate(1,6)};
    fish.calculate(board);
    for (int i = 0; i < fish.getPossibleMove().length; i++) {
      assertEquals(expected[i].getX(), fish.getPossibleMove()[i].getX());
      assertEquals(expected[i].getY(), fish.getPossibleMove()[i].getY());
    }
  }

  @Test
  @DisplayName("Test if calculate produces the right coordinates if the black fish stand on the field 7, 7")
  public void testCalculateBlackFishInTheCornerOfTheBoard(){
    Piece fish = new Fish('b',new Coordinate(7,7));
    Coordinate[] expected = {new Coordinate(6,6),new Coordinate(6,7)};
    fish.calculate(board);
    for (int i = 0; i < fish.getPossibleMove().length; i++) {
      assertEquals(expected[i].getX(), fish.getPossibleMove()[i].getX());
      assertEquals(expected[i].getY(), fish.getPossibleMove()[i].getY());
    }
  }
  //endregion black Fish

  //region white Fish
  @Test
  @DisplayName("Test if calculate produces the right coordinates if the white fish stand on the field 4, 4")
  public void testCalculateWhiteFishInTheMiddleOfTheBoard(){
    Piece fish = new Fish('w',new Coordinate(4,4));
    Coordinate[] expected = {new Coordinate(3,4),new Coordinate(3,5),new Coordinate(5,4),new Coordinate(5,5)};
    fish.calculate(board);
    for (int i = 0; i < fish.getPossibleMove().length; i++) {
      assertEquals(expected[i].getX(), fish.getPossibleMove()[i].getX());
      assertEquals(expected[i].getY(), fish.getPossibleMove()[i].getY());
    }
  }

  @Test
  @DisplayName("Test if calculate produces the right coordinates if the white fish stand on the field 0, 6")
  public void testCalculateWhiteFishOnTheSideOfTheBoard(){
    Piece fish = new Fish('w',new Coordinate(0,6));
    Coordinate[] expected = {new Coordinate(1,6),new Coordinate(1,7)};
    fish.calculate(board);
    for (int i = 0; i < fish.getPossibleMove().length; i++) {
      assertEquals(expected[i].getX(), fish.getPossibleMove()[i].getX());
      assertEquals(expected[i].getY(), fish.getPossibleMove()[i].getY());
    }
  }

  @Test
  @DisplayName("Test if calculate produces the right coordinates if the white fish stand on the field 7, 7")
  public void testCalculateWhiteFishInTheCornerOfTheBoard(){
    Piece fish = new Fish('w',new Coordinate(7,7));
    Coordinate[] expected = {new Coordinate(6,7)};
    fish.calculate(board);
    for (int i = 0; i < fish.getPossibleMove().length; i++) {
      assertEquals(expected[i].getX(), fish.getPossibleMove()[i].getX());
      assertEquals(expected[i].getY(), fish.getPossibleMove()[i].getY());
    }
  }
  //endregion white Fish

  //region Crow
  @Test
  @DisplayName("Test if calculate produces the right coordinates at the beginning of the game for all crows")
  public void testCalculateCrowsFlyAtStart(){
    Piece crow1White = board.getSpecificField(new Coordinate(0,0)).getPiece();
    Piece crow2White = board.getSpecificField(new Coordinate(7,0)).getPiece();
    Piece crow1Black = board.getSpecificField(new Coordinate(0,7)).getPiece();
    Piece crow2Black = board.getSpecificField(new Coordinate(7,7)).getPiece();
    Piece[] pieces = {crow1White,crow2White,crow1Black,crow2Black};

    Coordinate[] expected = {new Coordinate(0,2),new Coordinate(0,3),new Coordinate(0,4),new Coordinate(0,5),
            new Coordinate(1,2),new Coordinate(1,3),new Coordinate(1,4),new Coordinate(1,5),
            new Coordinate(2,2),new Coordinate(2,3),new Coordinate(2,4),new Coordinate(2,5),
            new Coordinate(3,2),new Coordinate(3,3),new Coordinate(3,4),new Coordinate(3,5),
            new Coordinate(4,2),new Coordinate(4,3),new Coordinate(4,4),new Coordinate(4,5),
            new Coordinate(5,2),new Coordinate(5,3),new Coordinate(5,4),new Coordinate(5,5),
            new Coordinate(6,2),new Coordinate(6,3),new Coordinate(6,4),new Coordinate(6,5),
            new Coordinate(7,2),new Coordinate(7,3),new Coordinate(7,4),new Coordinate(7,5)};

    for (Piece p:pieces) {
      for (int i = 0; i < p.getPossibleMove().length; i++) {
        assertEquals(expected[i].getX(),p.getPossibleMove()[i].getX());
        assertEquals(expected[i].getY(),p.getPossibleMove()[i].getY());
      }
    }
  }

  @Test
  @DisplayName("Test if defeatPieceMove produces the right coordinates if the crow stands on the field 4, 4")
  public void testDefeatPieceMoveInTheMiddleOfTheBoard(){
    Crow crow = new Crow('b', new Coordinate(4,4));
    crow.defeatPieceMove(board);

    Coordinate[] expected = {new Coordinate(3,3),new Coordinate(3,4),
            new Coordinate(3,5),new Coordinate(4,3),new Coordinate(4,5),
            new Coordinate(5,3),new Coordinate(5,4),new Coordinate(5,5)};

    for (int i = 0; i < crow.getPossibleMove().length; i++) {
      assertEquals(expected[i].getX(),crow.getPossibleMove()[i].getX());
      assertEquals(expected[i].getY(),crow.getPossibleMove()[i].getY());
    }
  }

  @Test
  @DisplayName("Test if defeatPieceMove produces the right coordinates if the crow stands on the field 0, 0")
  public void testDefeatPieceMoveInTheCornerOfTheBoard(){
    Crow crow = new Crow('b', new Coordinate(0,0));
    crow.defeatPieceMove(board);

    Coordinate[] expected = {new Coordinate(3,3),new Coordinate(3,4),
            new Coordinate(3,5),new Coordinate(4,3),new Coordinate(4,5),
            new Coordinate(5,3),new Coordinate(5,4),new Coordinate(5,5)};

    for (int i = 0; i < crow.getPossibleMove().length; i++) {
      assertEquals(expected[i].getX(),crow.getPossibleMove()[i].getX());
      assertEquals(expected[i].getY(),crow.getPossibleMove()[i].getY());
    }
  }

  //TODO: test Crow after it got moeved and a piece defeted

  //endregion

  //region Bear
  @Test
  @DisplayName("Test if calculate produces the right coordinates if the bear stands on the field 4, 4")
  public void testCalculateBearInTheMiddleOfTheBoard(){
    Piece bear = new Bear('g',new Coordinate(4,4));
    bear.calculate(board);

    Coordinate[] expected = {new Coordinate(3,3),new Coordinate(3,4),
            new Coordinate(3,5),new Coordinate(4,3),new Coordinate(4,5),
            new Coordinate(5,3),new Coordinate(5,4),new Coordinate(5,5)};

    for (int i = 0; i < bear.getPossibleMove().length; i++) {
      assertEquals(expected[i].getX(),bear.getPossibleMove()[i].getX());
      assertEquals(expected[i].getY(),bear.getPossibleMove()[i].getY());
    }
  }

  @Test
  @DisplayName("Test if calculate produces the right coordinates if the bear stands on the field 0, 4")
  public void testCalculateBearOnTheSideOfTheBoard(){
    Piece bear = new Bear('g',new Coordinate(0,4));
    bear.calculate(board);

    Coordinate[] expected = {new Coordinate(0,3),new Coordinate(0,5),
            new Coordinate(1,3),new Coordinate(1,4),new Coordinate(1,5)};

    for (int i = 0; i < bear.getPossibleMove().length; i++) {
      assertEquals(expected[i].getX(),bear.getPossibleMove()[i].getX());
      assertEquals(expected[i].getY(),bear.getPossibleMove()[i].getY());
    }
  }

  @Test
  @DisplayName("Test if calculate produces the right coordinates if the bear stands near other pieces and on the field 4, 5")
  public void testCalculateBearNearOtherPieces(){
    Piece bear = new Bear('g',new Coordinate(4,5));
    bear.calculate(board);

    Coordinate[] expected = {new Coordinate(3,4),new Coordinate(3,5),
            new Coordinate(4,4),new Coordinate(5,4),new Coordinate(5,5)};

    for (int i = 0; i < bear.getPossibleMove().length; i++) {
      assertEquals(expected[i].getX(),bear.getPossibleMove()[i].getX());
      assertEquals(expected[i].getY(),bear.getPossibleMove()[i].getY());
    }
  }

  @Test
  @DisplayName("Test if calculate produces the right coordinates if the bear stands on an invalid field")
  public void testCalculateBearOnInvalidField(){
    Piece bear = new Bear('g',new Coordinate(-5,9));
    bear.calculate(board);

    for (int i = 0; i < bear.getPossibleMove().length; i++) {
      assertEquals(null,bear.getPossibleMove()[i]);
    }
  }
  //endregion

  //region Monkey
  //endregion
}
