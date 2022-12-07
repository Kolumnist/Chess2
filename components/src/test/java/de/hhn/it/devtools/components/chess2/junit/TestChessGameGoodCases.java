package de.hhn.it.devtools.components.chess2.junit;

import de.hhn.it.devtools.components.chess2.ChessGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test ChessGame and Chess2Service interface with good cases.")
public class TestChessGameGoodCases {

  //Chess2Service chess2Service;

  @BeforeEach
  void setup() {
    ChessGame chessGame = new ChessGame();
    //chessGame = chess2Service;
  }

  @Test
  void TestStartGame()
  {

  }

}
