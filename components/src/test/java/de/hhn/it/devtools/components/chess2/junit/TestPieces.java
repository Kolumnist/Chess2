package de.hhn.it.devtools.components.chess2.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

public class TestPieces {

  @BeforeEach
  public void setUp() {
    ChessGame chessGame = new ChessGame();
  }

  //region King
  @Test
  @DisplayName("Test if calculate produces the right coordinates if the king stand on the field 4, 4")
  public void testCalculateKingInTheMiddleOfTheBoard() {
    Piece king = new King('b', new Coordinate(4, 4), true);
    Coordinate[] expected = {new Coordinate(3, 5), new Coordinate(4, 5), new Coordinate(5, 5),
        new Coordinate(3, 4), new Coordinate(5, 4), new Coordinate(3, 3), new Coordinate(4, 3),
        new Coordinate(5, 3)};
    king.calculate();
    for (int i = 0; i < king.getPossibleMove().length; i++) {
      assertEquals(expected[i].getX(), king.getPossibleMove()[i].getX());
      assertEquals(expected[i].getY(), king.getPossibleMove()[i].getY());
    }
  }

  @Test
  @DisplayName("Test if calculate produces the right coordinates if the king stand on the field 0, 6")
  public void testCalculateKingOnTheSideOfTheBoard() {
    Piece king = new King('b', new Coordinate(0, 6), true);
    Coordinate[] expected = {new Coordinate(0, 5), new Coordinate(0, 7),
        new Coordinate(1, 5), new Coordinate(1, 6), new Coordinate(1, 7)};
    king.calculate();
    for (int i = 0; i < king.getPossibleMove().length; i++) {
      assertEquals(expected[i].getX(), king.getPossibleMove()[i].getX());
      assertEquals(expected[i].getY(), king.getPossibleMove()[i].getY());
    }
  }

  @Test
  @DisplayName("Test if calculate produces the right coordinates if the king stand on the field 7, 7")
  public void testCalculateKingInTheCornerOfTheBoard() {
    Piece king = new King('b', new Coordinate(7, 7), true);
    Coordinate[] expected = {new Coordinate(6, 7), new Coordinate(6, 6), new Coordinate(7, 6)};
    king.calculate();
    for (int i = 0; i < king.getPossibleMove().length; i++) {
      assertEquals(expected[i].getX(), king.getPossibleMove()[i].getX());
      assertEquals(expected[i].getY(), king.getPossibleMove()[i].getY());
    }
  }

/*  @Test
  @DisplayName("Test if calculate produces the right coordinates if the king stand on an invalid field")
  public void testCalculateKingOnInvalidField() {
    Piece king = new King('b', new Coordinate(-5, 9), true);
    king.calculate();
    for (int i = 0; i < king.getPossibleMove().length; i++) {
      assertEquals(null, king.getPossibleMove()[i]);
    }
  }*/
  //endregion King

  //region Queen
  @Test
  @DisplayName("Test if calculate produces the right coordinates if the queen stand on the field 4, 4")
  public void testCalculateQueenInTheMiddleOfTheBoard(){
    Piece queen = new Queen('b',new Coordinate(4,4));
    Coordinate[] expected = {new Coordinate(3,3),new Coordinate(3,4),new Coordinate(3,5),new Coordinate(4,3),new Coordinate(4,5),new Coordinate(5,3),new Coordinate(5,4),new Coordinate(5,5),new Coordinate(2,2),new Coordinate(2,4),new Coordinate(2,6),new Coordinate(4,2),new Coordinate(4,6),new Coordinate(6,2),new Coordinate(6,4),new Coordinate(6,6),new Coordinate(1,1),new Coordinate(1,4),new Coordinate(1,7),new Coordinate(4,1),new Coordinate(4,7),new Coordinate(7,1),new Coordinate(7,4),new Coordinate(7,7),new Coordinate(0,0),new Coordinate(0,4),new Coordinate(4,0)};
    queen.calculate();
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
    queen.calculate();
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
    queen.calculate();
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
    fishqueen.calculate();
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
    fishqueen.calculate();
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
    fishqueen.calculate();
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
    elephant.calculate();
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
    elephant.calculate();
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
    elephant.calculate();
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
    fish.calculate();
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
    fish.calculate();
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
    fish.calculate();
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
    fish.calculate();
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
    fish.calculate();
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
    fish.calculate();
    for (int i = 0; i < fish.getPossibleMove().length; i++) {
      assertEquals(expected[i].getX(), fish.getPossibleMove()[i].getX());
      assertEquals(expected[i].getY(), fish.getPossibleMove()[i].getY());
    }
  }
  //endregion white Fish
}
