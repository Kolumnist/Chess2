package de.hhn.it.devtools.javafx.chess2;

import de.hhn.it.devtools.apis.chess2.Board;
import de.hhn.it.devtools.apis.chess2.Chess2Service;
import de.hhn.it.devtools.apis.chess2.Coordinate;
import de.hhn.it.devtools.apis.chess2.FieldState;
import de.hhn.it.devtools.apis.chess2.GameState;
import de.hhn.it.devtools.apis.chess2.IllegalStateException;
import de.hhn.it.devtools.apis.chess2.InvalidMoveException;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.components.chess2.ChessGame;
import java.util.Arrays;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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

  private Coordinate[] currentCoordinates = new Coordinate[64];
  private boolean currentSelected = false;
  private boolean redKingInJail = false;

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
   * Sets the King in Jail if the Queen thing would work she would get set too!
   *
   * @param newButton to get where the king/queen is placed at
   */
  private void setKingQueenInJail(Button newButton) {
    if (gameBoard.getSpecificField(new Coordinate(8, 3)).getFieldState()
        == FieldState.JAIL_KING && !redKingInJail) {
      redKingInJail = true;
      buttonjailLy3.setGraphic(newButton.getGraphic());
    }
    else if (gameBoard.getSpecificField(new Coordinate(9, 4)).getFieldState()
        == FieldState.JAIL_KING) {
      buttonjailRy4.setGraphic(newButton.getGraphic());
    }
  }

  /**
   * Handles Movement from the selected Button to the new Button.
   *
   * @param newButton to assign it easier
   * @param newCoordinate to assign it easier as well
   */
  private void handleMove(Button newButton, Coordinate newCoordinate) {

    /* The Piece moves to the new Button */
    if (gameBoard.getSpecificField(newCoordinate).getFieldState() == FieldState.HAS_OTHER_PIECE
        || gameBoard.getSpecificField(newCoordinate).getFieldState() == FieldState.HAS_BEAR
        || gameBoard.getSpecificField(newCoordinate).getFieldState() == FieldState.OTHER_KING) {

      if(!(selectedCoordinate.compareCoordinates(newCoordinate)))
      {
        newButton.setGraphic(selectedButton.getGraphic());
        selectedButton.setGraphic(null);
      }

      selectedButton.setStyle(selectedStyle);
      for (int i = 0; i < possibleButtons.length; i++) {
        possibleButtons[i].setStyle(possibleMovesStyles[i]);
      }
      currentCoordinates = chessGame.getCurrentFields();
      currentSelected = false;
      return;
    }

    /* The Piece moved on Bear both Buttons are empty and Pieces null */
    if (gameBoard.getSpecificField(newCoordinate).getFieldState() == FieldState.FREE_FIELD) {
      newButton.setGraphic(null);
      selectedButton.setGraphic(null);
      selectedButton.setStyle(selectedStyle);

      for (int i = 0; i < possibleButtons.length; i++) {
        possibleButtons[i].setStyle(possibleMovesStyles[i]);
      }
      currentCoordinates = chessGame.getCurrentFields();
      currentSelected = false;
      return;
    }

    /* The Piece is a Monkey and jumped, so its turn repeats */
    if (gameBoard.getSpecificField(newCoordinate).getFieldState() == FieldState.SELECTED) {
      newButton.setGraphic(selectedButton.getGraphic());
      selectedButton.setGraphic(null);
      selectedButton.setStyle(selectedStyle);
      for (int i = 0; i < possibleButtons.length; i++) {
        possibleButtons[i].setStyle(possibleMovesStyles[i]);
      }

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
      String background = newButton.getStyle();
      selectedStyle = background;
      newButton.setStyle("-fx-background-color: " + background.substring(22, 30)
          + "; -fx-border-color: #00ff77;");

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
    }

    if (gameBoard.getSpecificField(newCoordinate).getFieldState() == FieldState.HAS_CURRENT_PIECE
      && chessGame.getGameState() == GameState.CHECKMATE) {
      newButton.setGraphic(selectedButton.getGraphic());
      selectedButton.setGraphic(null);
      selectedButton.setStyle(selectedStyle);

      for (int i = 0; i < possibleButtons.length; i++) {
        possibleButtons[i].setStyle(possibleMovesStyles[i]);
      }
      currentCoordinates = chessGame.getCurrentFields();
      currentSelected = false;
    }

    /*TODO: Checkmate and Check and such things*/
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
    if (!(((Button) event.getSource()).getStyle().substring(48, 57).contains("#00ff77"))
        && !(((Button) event.getSource()).getStyle().substring(48, 57).contains("#099809"))) {

      /* Remove the color of green buttons BUT only if certain conditions are met */
      if (currentSelected && selectedButton != null
          && selectedFieldState != FieldState.FREE_FIELD
          && selectedFieldState != FieldState.HAS_OTHER_PIECE
          && selectedFieldState != FieldState.OTHER_KING) {
        selectedButton.setStyle(selectedStyle);
        for (int i = 0; i < possibleButtons.length && possibleButtons[i] != null; i++) {
          possibleButtons[i].setStyle(possibleMovesStyles[i]);
        }
      }

      selectedButton = (Button) event.getSource();
      selectedCoordinate = new Coordinate(
          Integer.parseInt(selectedButton.getId().substring(6, 7)),
          Integer.parseInt(selectedButton.getId().substring(7, 8)));
      selectedFieldState = gameBoard.getSpecificField(selectedCoordinate).getFieldState();

      try {
        possibleMoves = chessGame.getPossibleMoves(selectedCoordinate);
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
      String background = ((Button) event.getSource()).getStyle();
      selectedStyle = background;
      ((Button) event.getSource()).setStyle("-fx-background-color: " + background.substring(22, 30)
          + "; -fx-border-color: #00ff77;");

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
    } else if (((Button) event.getSource()).getStyle().substring(48, 57).contains("#00ff77")
        || ((Button) event.getSource()).getStyle().substring(48, 57).contains("#099809")) {

      /*
       * Every Button in the beginning have the same Action:
       *
       * Call method getPossibleMoves in ChessGame check if the length is 63 or more than it
       * is a button that can't be moved right now.
       * Should everything work fine the pressed buttons color turns dark green and the other buttons
       * where we have a Coordinate for turn green as well but a little lighter.
       * And the Actions of the pressed buttons get switched with an if or with another buttonAction.
       */

      Button newButton = (Button) event.getSource();
      Coordinate newCoordinate = new Coordinate(
          Integer.parseInt(newButton.getId().substring(6, 7)),
          Integer.parseInt(newButton.getId().substring(7, 8)));
      boolean toJail =
          gameBoard.getSpecificField(newCoordinate).getFieldState() == FieldState.OTHER_KING;

      /*TODO: at the moment it is not possible to set the queen in jail...*/

      try {
        gameBoard = chessGame.moveSelectedPiece(selectedCoordinate, newCoordinate);
      } catch (IllegalParameterException | InvalidMoveException | IllegalStateException e) {
        throw new RuntimeException(e);
      }

      if (toJail) {
        setKingQueenInJail(newButton);
      }

      if (gameBoard.getSpecificField(selectedCoordinate).getFieldState() != FieldState.SELECTED) {
        handleMove(newButton, newCoordinate);
      }

      if (gameBoard.getSpecificField(selectedCoordinate).getFieldState() == FieldState.SELECTED) {
        return;
      }

      if (gameBoard.getSpecificField(newCoordinate).getFieldState() == FieldState.SELECTED) {
        handleMove(newButton, newCoordinate);
      }
    }

    /*
     * The dark green button has the same action as the other green buttons:
     *
     * Call method moveSelectedPiece, check the FieldState of the currentlySelected Field on the
     * Coordinate of the dark green button if it is still SELECTED then do nothing, if it should
     * not be selected but the field on the moved coordinate is SELECTED, then we know the monkey
     * jumped and can move again so we automatically place pieces correctly and undo all green
     * changes and then call method getPossibleMoves with the coordinate we moved to, and
     * do everything that we did in the other button action before then continue the turn.
     * SO if the fieldstate is not SELECTED anymore we undo all green changes, set the images of the
     * pieces correctly and switch turns after checking if a player won and calling getCurrentFields.
     * Don't forget to set the button action back to the other one
     */
  }

  private int colorOnePossibleButton(Button button, int max, String color) {
    if (max < possibleMoves.length
        && button.getId().substring(6, 8).equals(possibleMoves[max].coordinateToString())) {
      String background = button.getStyle();
      possibleMovesStyles[max] = button.getStyle();
      /*TODO: Background instead of border*/
      button.setStyle("-fx-background-color: " + background.substring(22, 30)
          + "; -fx-border-color: " + color);

      possibleButtons[max] = button;
      max++;
    }
    return max;
  }

  private Coordinate[] sortCoordinates(Coordinate[] coordinates) {
    int current;
    int oneMore;

    for (int i = coordinates.length - 1; i > 0; i--) {
      boolean test = true;
      for (int j = 0; j < i; j++) {
        current = coordinates[j].getX() + 8 * coordinates[j].getY();
        oneMore = coordinates[j + 1].getX() + 8 * coordinates[j + 1].getY();
        if (current > oneMore) {
          // swap temp and arr[i]
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

  public static void giveChessGameAndBoard(Chess2Service chessGame, Board board) {
    Chess2BoardController.chessGame = chessGame;
    gameBoard = board;
  }

}
