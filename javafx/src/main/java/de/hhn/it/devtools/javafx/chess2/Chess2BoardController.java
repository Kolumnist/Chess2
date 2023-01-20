package de.hhn.it.devtools.javafx.chess2;

import de.hhn.it.devtools.apis.chess2.Board;
import de.hhn.it.devtools.apis.chess2.Chess2Service;
import de.hhn.it.devtools.apis.chess2.Coordinate;
import de.hhn.it.devtools.apis.chess2.Field;
import de.hhn.it.devtools.apis.chess2.FieldState;
import de.hhn.it.devtools.apis.chess2.GameState;
import de.hhn.it.devtools.apis.chess2.IllegalStateException;
import de.hhn.it.devtools.apis.chess2.InvalidMoveException;
import de.hhn.it.devtools.apis.chess2.WinningPlayerState;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.components.chess2.ChessGame;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * This class handles all communication between buttons and components.
 *
 * @author Collin Hoss, Lara Mangi, Michel Jouaux
 * @version 1.0
 */
public class Chess2BoardController {

  // button border color silver: #474747

  //region Buttons in Board
  @FXML
  private Button button00;

  @FXML
  private Button button01;

  @FXML
  private Button button02;

  @FXML
  private Button button03;

  @FXML
  private Button button04;

  @FXML
  private Button button05;

  @FXML
  private Button button06;

  @FXML
  private Button button07;

  @FXML
  private Button button10;

  @FXML
  private Button button11;

  @FXML
  private Button button12;

  @FXML
  private Button button13;

  @FXML
  private Button button14;

  @FXML
  private Button button15;

  @FXML
  private Button button16;

  @FXML
  private Button button17;

  @FXML
  private Button button20;

  @FXML
  private Button button21;

  @FXML
  private Button button22;

  @FXML
  private Button button23;

  @FXML
  private Button button24;

  @FXML
  private Button button25;

  @FXML
  private Button button26;

  @FXML
  private Button button27;

  @FXML
  private Button button30;

  @FXML
  private Button button31;

  @FXML
  private Button button32;

  @FXML
  private Button button33;

  @FXML
  private Button button34;

  @FXML
  private Button button35;

  @FXML
  private Button button36;

  @FXML
  private Button button37;

  @FXML
  private Button button40;

  @FXML
  private Button button41;

  @FXML
  private Button button42;

  @FXML
  private Button button43;

  @FXML
  private Button button44;

  @FXML
  private Button button45;

  @FXML
  private Button button46;

  @FXML
  private Button button47;

  @FXML
  private Button button50;

  @FXML
  private Button button51;

  @FXML
  private Button button52;

  @FXML
  private Button button53;

  @FXML
  private Button button54;

  @FXML
  private Button button55;

  @FXML
  private Button button56;

  @FXML
  private Button button57;

  @FXML
  private Button button60;

  @FXML
  private Button button61;

  @FXML
  private Button button62;

  @FXML
  private Button button63;

  @FXML
  private Button button64;

  @FXML
  private Button button65;

  @FXML
  private Button button66;

  @FXML
  private Button button67;

  @FXML
  private Button button70;

  @FXML
  private Button button71;

  @FXML
  private Button button72;

  @FXML
  private Button button73;

  @FXML
  private Button button74;

  @FXML
  private Button button75;

  @FXML
  private Button button76;

  @FXML
  private Button button77;

  @FXML
  private Button buttonjailLy3;

  @FXML
  private Button buttonjailLy4;

  @FXML
  private Button buttonjailRy3;

  @FXML
  private Button buttonjailRy4;

  //endregion Buttons in Board


  // Needed static variables to check for things
  private static Chess2Service chessGame = new ChessGame();
  private static Board gameBoard;

  String currentPlayerName = "Red";
  private boolean currentSelected = false;


  private boolean redKingInJail = false;
  //checked King stuff
  private Button checkedButton;
  private String checkedStyle = "";


  //All variables for the selected Button
  private Button selectedButton;
  private Coordinate selectedCoordinate;
  private FieldState selectedFieldState;
  private String selectedStyle = "";

  //Needed variable arrays for the possible Move things
  private Coordinate[] possibleMoves = new Coordinate[64];
  private String[] possibleMovesStyles = new String[0];
  private Button[] possibleButtons = new Button[0];

  public Chess2BoardController() {
  }

  /**
   * Sets the King in Jail if the Queen thing would work she would get set too.
   *
   * @param newButton to get where the king/queen is placed at
   */
  private void setKingQueenInJail(Button newButton) {
    /* Red King gets send to this Jail on (8, 3)*/
    if (gameBoard.getSpecificField(new Coordinate(8, 3)).getFieldState()
        == FieldState.JAIL_KING && !redKingInJail) {
      redKingInJail = true;
      buttonjailLy3.setGraphic(newButton.getGraphic());

      /* Black King gets send to this Jail on (9, 4)*/
    } else if (gameBoard.getSpecificField(new Coordinate(9, 4)).getFieldState()
        == FieldState.JAIL_KING) {
      buttonjailRy4.setGraphic(newButton.getGraphic());
    }
  }

  /**
   * Handles Movement from the selected Button to the new Button.
   *
   * @param newButton     to assign it easier
   * @param newCoordinate to assign it easier as well
   */
  private void handleMove(Button newButton, Coordinate newCoordinate) {

    /* The Piece moves to the new Button */
    if (gameBoard.getSpecificField(newCoordinate).getFieldState() == FieldState.HAS_OTHER_PIECE
        || gameBoard.getSpecificField(newCoordinate).getFieldState() == FieldState.HAS_BEAR
        || gameBoard.getSpecificField(newCoordinate).getFieldState() == FieldState.OTHER_KING) {

      if (!(selectedCoordinate.compareCoordinates(newCoordinate))) {
        //Set Graphic of selected Button to null and new to selected
        newButton.setGraphic(selectedButton.getGraphic());
        selectedButton.setGraphic(null);
      }

      //Reset Styles of green Buttons
      selectedButton.setStyle(selectedStyle);
      for (int i = 0; i < possibleButtons.length; i++) {
        possibleButtons[i].setStyle(possibleMovesStyles[i]);
      }

      chessGame.getCurrentFields();
      currentSelected = false;
      return;
    }

    /* The Piece moved on Bear both Buttons are empty and Pieces null */
    if (gameBoard.getSpecificField(newCoordinate).getFieldState() == FieldState.FREE_FIELD) {

      //Set both Graphics to null
      newButton.setGraphic(null);
      selectedButton.setGraphic(null);

      //Reset Styles of green Buttons
      selectedButton.setStyle(selectedStyle);
      for (int i = 0; i < possibleButtons.length; i++) {
        possibleButtons[i].setStyle(possibleMovesStyles[i]);
      }

      /* It could be that the game is over now so check for that */
      if (chessGame.getGameState() == GameState.CHECKMATE) {
        Chess2PopUp p = new Chess2PopUp();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/chess2"
            + "/Chess2PopUp.fxml"));
        try {
          loader.load();
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        if (chessGame.getWinningPlayer() == WinningPlayerState.BLACK_WIN) {
          Label l = new Label("Congratulation Player Black! You has won!");
          p.initialize(l);
        }
        if (chessGame.getWinningPlayer() == WinningPlayerState.RED_WIN) {
          Label l = new Label("Congratulation Player Red! You has won!");
          p.initialize(l);
        }
      }

      chessGame.getCurrentFields();
      currentSelected = false;
      return;
    }

    /* The Piece is a Monkey and jumped, so its turn repeats */
    if (gameBoard.getSpecificField(newCoordinate).getFieldState() == FieldState.SELECTED) {

      //Set Graphic of selected Button to null and new to selected
      newButton.setGraphic(selectedButton.getGraphic());
      selectedButton.setGraphic(null);

      //Reset Styles of green Buttons
      selectedButton.setStyle(selectedStyle);
      for (int i = 0; i < possibleButtons.length; i++) {
        possibleButtons[i].setStyle(possibleMovesStyles[i]);
      }

      //region Monkeychaos
      /*
       * Do EVERYTHING that would normally happen
       * on a pure button click on the monkey
       */
      selectedButton = newButton;
      selectedCoordinate = new Coordinate(
          Integer.parseInt(selectedButton.getId().substring(6, 7)),
          Integer.parseInt(selectedButton.getId().substring(7, 8)));
      selectedFieldState = gameBoard.getSpecificField(selectedCoordinate).getFieldState();

      try {
        possibleMoves = chessGame.getPossibleMoves(newCoordinate);
      } catch (IllegalParameterException | IllegalStateException e) {
        throw new RuntimeException(e);
      }

      if (possibleMoves.length >= 63) {
        return;
      } else if (possibleMoves.length > 1) {
        possibleMoves = sortCoordinates(possibleMoves);
      }
      currentSelected = true;
      possibleMovesStyles = new String[possibleMoves.length];
      possibleButtons = new Button[possibleMoves.length];

      /* Les gooo button color */
      selectedStyle = newButton.getStyle();
      newButton.setStyle("-fx-background-color: #00ff77; -fx-border-color: #474747;");

      int max = 0;
      for (Button button : Arrays.asList(
          button00, button10, button20, button30, button40, button50, button60, button70,
          button01, button11, button21, button31, button41, button51, button61, button71,
          button02, button12, button22, button32, button42, button52, button62, button72,
          button03, button13, button23, button33, button43, button53, button63, button73,
          button04, button14, button24, button34, button44, button54, button64, button74,
          button05, button15, button25, button35, button45, button55, button65, button75,
          button06, button16, button26, button36, button46, button56, button66, button76,
          button07, button17, button27, button37, button47, button57, button67, button77,
          buttonjailLy3, buttonjailLy4, buttonjailRy3, buttonjailRy4)) {
        max = colorOnePossibleButton(button, max, "#099809");
      }
      //endregion
    }

    /* A Game ending move was performed */
    if (gameBoard.getSpecificField(newCoordinate).getFieldState() == FieldState.HAS_CURRENT_PIECE
        && chessGame.getGameState() == GameState.CHECKMATE) {

      //Set Graphic of selected Button to null and new to selected
      newButton.setGraphic(selectedButton.getGraphic());
      selectedButton.setGraphic(null);

      //Reset Styles of green Buttons
      selectedButton.setStyle(selectedStyle);
      for (int i = 0; i < possibleButtons.length; i++) {
        possibleButtons[i].setStyle(possibleMovesStyles[i]);
      }

      chessGame.getCurrentFields();
      currentSelected = false;

      //Popup window of win
      Chess2PopUp p = new Chess2PopUp();
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/chess2"
          + "/Chess2PopUp.fxml"));
      try {
        loader.load();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      if (chessGame.getWinningPlayer() == WinningPlayerState.BLACK_WIN) {
        Label l = new Label("Congratulation Player Black! You have won!");
        p.initialize(l);
      }
      if (chessGame.getWinningPlayer() == WinningPlayerState.RED_WIN) {
        Label l = new Label("Congratulation Player White! You have won!");
        p.initialize(l);
      }
    }
  }

  /**
   * Handles the press of any field button.
   *
   * @param event is the event that triggered this action
   */
  @FXML
  private void handleButtonAction(ActionEvent event) {

    /* GameState should be RUNNING or else it just won't do anything*/
    if (chessGame.getGameState() == GameState.CHECKMATE) {
      return;
    }

    /* The pressed Button is not green anywhere */
    if (!(((Button) event.getSource()).getStyle().substring(22, 30).contains("#00ff77"))
        && !(((Button) event.getSource()).getStyle().substring(22, 30).contains("#099809"))) {

      /* Reset the color of green buttons BUT only if certain conditions are met */
      if (currentSelected && selectedButton != null
          && selectedFieldState != FieldState.FREE_FIELD
          && selectedFieldState != FieldState.HAS_OTHER_PIECE
          && selectedFieldState != FieldState.OTHER_KING) {
        selectedButton.setStyle(selectedStyle);
        for (int i = 0; i < possibleButtons.length && possibleButtons[i] != null; i++) {
          possibleButtons[i].setStyle(possibleMovesStyles[i]);
        }
      }
      /* Reset the kings button back to normal under circumstances */
      if (checkedButton != null) {
        checkedButton.setStyle(checkedStyle);
      }

      //selected Things are set to the pressed button values
      selectedButton = (Button) event.getSource();
      selectedCoordinate = new Coordinate(
          Integer.parseInt(selectedButton.getId().substring(6, 7)),
          Integer.parseInt(selectedButton.getId().substring(7, 8)));
      selectedFieldState = gameBoard.getSpecificField(selectedCoordinate).getFieldState();

      /* call getPossibleMoves check for good -> set length of possible Styles & Buttons */
      try {
        possibleMoves = chessGame.getPossibleMoves(selectedCoordinate);
      } catch (IllegalParameterException | IllegalStateException e) {
        throw new RuntimeException(e);
      }
      if (possibleMoves.length >= 63) {
        return;
      } else if (possibleMoves.length > 1) {
        //sort the array
        possibleMoves = sortCoordinates(possibleMoves);
      }
      currentSelected = true;
      possibleMovesStyles = new String[possibleMoves.length];
      possibleButtons = new Button[possibleMoves.length];

      /* Les gooo button color */
      selectedStyle = ((Button) event.getSource()).getStyle();
      selectedButton.setStyle("-fx-background-color: #00ff77; -fx-border-color: #474747;");
      int max = 0;
      for (Button button : Arrays.asList(
          button00, button10, button20, button30, button40, button50, button60, button70,
          button01, button11, button21, button31, button41, button51, button61, button71,
          button02, button12, button22, button32, button42, button52, button62, button72,
          button03, button13, button23, button33, button43, button53, button63, button73,
          button04, button14, button24, button34, button44, button54, button64, button74,
          button05, button15, button25, button35, button45, button55, button65, button75,
          button06, button16, button26, button36, button46, button56, button66, button76,
          button07, button17, button27, button37, button47, button57, button67, button77,
          buttonjailLy3, buttonjailLy4, buttonjailRy3, buttonjailRy4)) {
        max = colorOnePossibleButton(button, max, "#099809");
      }

      /* The pressed button is a green one */
    } else if (((Button) event.getSource()).getStyle().substring(22, 30).contains("#00ff77")
        || ((Button) event.getSource()).getStyle().substring(22, 30).contains("#099809")) {

      /* Set possiblyCheck Coordinate */
      makeCheck();

      //Set newButton and newCoordinate for later use
      Button newButton = (Button) event.getSource();
      Coordinate newCoordinate = new Coordinate(
          Integer.parseInt(newButton.getId().substring(6, 7)),
          Integer.parseInt(newButton.getId().substring(7, 8)));
      boolean toJail =
          gameBoard.getSpecificField(newCoordinate).getFieldState() == FieldState.OTHER_KING;
      /*ERR: At the moment it is not possible to set the queen in jail...*/

      /* Call moveSelectedPiece and check FieldState on the newCoordinate -> react according */
      try {
        gameBoard = chessGame.moveSelectedPiece(selectedCoordinate, newCoordinate);
      } catch (IllegalParameterException | InvalidMoveException | IllegalStateException e) {
        throw new RuntimeException(e);
      }
      /* Set King in Jail */
      if (toJail) {
        setKingQueenInJail(newButton);
      }

      if (gameBoard.getSpecificField(selectedCoordinate).getFieldState() != FieldState.SELECTED) {
        handleMove(newButton, newCoordinate);
      }
      if (gameBoard.getSpecificField(selectedCoordinate).getFieldState() == FieldState.SELECTED) {
        return;
      }
      /* Should normally be monkey */
      if (gameBoard.getSpecificField(newCoordinate).getFieldState() == FieldState.SELECTED) {
        handleMove(newButton, newCoordinate);
        return;
      }

      /* After a move the gamestate could be CHECK and then the king gets highlighted. */
      if (chessGame.getGameState() == GameState.CHECK) {
        checkedButton.setStyle("-fx-background-color: #f9ff02; -fx-border-color: #474747;");
      }

      /* Switch Player "Name" in the top right! */
      if (Objects.equals(currentPlayerName, "Red")) {
        currentPlayerName = "Black";
      } else {
        currentPlayerName = "Red";
      }
      //Chess2ServiceController.setPlayerTextField(currentPlayerName);
    }
  }

  /**
   * Makes the possiblyChecked Coordinate to the Coordinate of OTHER_KING. Then searches for the
   * corresponding button and sets it to the checkedButton.
   */
  private void makeCheck() {
    Coordinate possiblyChecked = null;
    Field[] fields = gameBoard.getFields();
    for (int i = 0; i < 64; i++) {
      if (fields[i].getFieldState() == FieldState.OTHER_KING) {
        possiblyChecked = fields[i].getCoordinate();
      }
    }
    if (possiblyChecked == null) {
      return;
    }

    String buttonId = possiblyChecked.getX() + "";
    buttonId = buttonId + possiblyChecked.getY();

    for (Button button : Arrays.asList(
        button00, button10, button20, button30, button40, button50, button60, button70,
        button01, button11, button21, button31, button41, button51, button61, button71,
        button02, button12, button22, button32, button42, button52, button62, button72,
        button03, button13, button23, button33, button43, button53, button63, button73,
        button04, button14, button24, button34, button44, button54, button64, button74,
        button05, button15, button25, button35, button45, button55, button65, button75,
        button06, button16, button26, button36, button46, button56, button66, button76,
        button07, button17, button27, button37, button47, button57, button67, button77,
        buttonjailLy3, buttonjailLy4, buttonjailRy3, buttonjailRy4)) {
      if (button.getId().substring(6, 8).equals(buttonId)) {
        checkedButton = button;
        checkedStyle = checkedButton.getStyle();
        break;
      }
    }
  }

  /**
   * This method colors a Button and is mostly used with a loop through all button therefore the use
   * of the max variable.
   *
   * @param button the button which should get colored
   * @param max    maximum number of buttons that can get colored this turn
   * @param color  the new color of the button
   * @return max
   */
  private int colorOnePossibleButton(Button button, int max, String color) {
    if (max < possibleMoves.length
        && button.getId().substring(6, 8).equals(possibleMoves[max].coordinateToString())) {

      //Set styles of the button and safe the button and his old style
      possibleMovesStyles[max] = button.getStyle();
      button.setStyle("-fx-background-color: " + color + "; -fx-border-color: #474747");
      possibleButtons[max] = button;

      max++;
    }
    return max;
  }

  /**
   * Basically a bubble Sort but for a Coordinate array.
   *
   * @param coordinates that need to get sorted
   * @return sorted Coordinate array
   */
  private Coordinate[] sortCoordinates(Coordinate[] coordinates) {
    int current;
    int oneMore;

    for (int i = coordinates.length - 1; i > 0; i--) {
      boolean test = true;
      for (int j = 0; j < i; j++) {
        current = coordinates[j].getX() + 8 * coordinates[j].getY();
        oneMore = coordinates[j + 1].getX() + 8 * coordinates[j + 1].getY();
        if (current > oneMore) {
          Coordinate temp = coordinates[j];
          coordinates[j] = coordinates[j + 1];
          coordinates[j + 1] = temp;
          test = false;
        }
      }
      if (test) {
        break;
      }
    }
    return coordinates;
  }

  /**
   * Chess2BoardController needs to have a chess2Service and Board to call certain methods and check
   * for a lot of stuff.
   *
   * @param chessGame newest chessGame version
   * @param board     newest board version
   */
  public static void giveChessGameAndBoard(Chess2Service chessGame, Board board) {
    Chess2BoardController.chessGame = chessGame;
    gameBoard = board;
  }

}
