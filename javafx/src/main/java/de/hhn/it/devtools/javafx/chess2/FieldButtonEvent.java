package de.hhn.it.devtools.javafx.chess2;

import de.hhn.it.devtools.apis.chess2.Coordinate;
import java.util.Arrays;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

/**
 * This class is just for me to know what actionPerformed Methods I need
 *
 * https://code.makery.ch/blog/javafx-2-event-handlers-and-change-listeners/
 */
public class FieldButtonEvent extends ActionEvent {


  public FieldButtonEvent() {


  }
  /*Arrays.stream(currentCoordinates).anyMatch(event.getEventType().getCoordinate())*/
  @FXML
  private void handleButtonAction(ActionEvent event, Coordinate[] currentCoordinates) {
    /*
    * Every Button in the beginning have the same Action:
    *
    * Call method getPossibleMoves in ChessGame check if the length is 63 or more than it
    * is a button that can't be moved right now.
    * Should everything work fine the pressed buttons color turns dark green and the other buttons
    * where we have a Coordinate for turn green as well but a little lighter.
    * And the Actions of the pressed buttons get switched with an if or with another buttonAction.
    *
    *
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

}
