package de.hhn.it.devtools.components.connectfour.provider.helper;

import de.hhn.it.devtools.apis.connectfour.enums.GameState;
import de.hhn.it.devtools.apis.connectfour.exceptions.IllegalNameException;
import de.hhn.it.devtools.apis.connectfour.exceptions.IllegalOperationException;
import de.hhn.it.devtools.apis.connectfour.enums.MatchState;
import de.hhn.it.devtools.apis.connectfour.enums.Mode;
import de.hhn.it.devtools.apis.connectfour.helper.Profile;
import de.hhn.it.devtools.components.connectfour.provider.ConnectFour;
import java.util.LinkedList;

/**
 * This class models the game.
 */
public class Game {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(Game.class);

  private final ConnectFour connectFour;
  private final Board board = new Board();
  private final LinkedList<Disc> discs = new LinkedList<>();

  /**
   * Create a new game.
   *
   * @param connectFour the game interface
   */
  public Game(ConnectFour connectFour) {
    logger.info("New Game Instance!");
    this.connectFour = connectFour;
  }

  /**
   * Add a player disc.
   *
   * @param owner the player, the disc belongs to
   * @param color the color of the disc
   */
  public void addDisc(Profile owner, Color color) {
    discs.add(new Disc(owner, color));
  }

  /**
   * Place disc in specified column.
   *
   * @param column the column, the disk is to be placed in
   */
  public void placeDiscIn(int column) {
    Disc disc = discs.getFirst();
    try {
      board.placeDiscInColumn(column, disc);
      // game won
      if (board.check()) {
        // by A
        if (discs.getFirst().owner() == connectFour.getProfileA()) {
          connectFour.setMatchState(MatchState.PLAYER_A_WON);
          if (connectFour.getMode() == Mode.SINGLEPLAYER) {
            try {
              connectFour.getProfileA().addSingleplayerWin();
              connectFour.getProfileB().addSingleplayerLoose();
            } catch (IllegalNameException e) {
              e.printStackTrace();
            }
          } else {
            try {
              connectFour.getProfileA().addMultiplayerWin();
              connectFour.getProfileB().addMultiplayerLoose();
            } catch (IllegalNameException e) {
              e.printStackTrace();
            }
          }
        } else {
          // By B
          connectFour.setMatchState(MatchState.PLAYER_B_WON);
          if (connectFour.getMode() == Mode.SINGLEPLAYER) {
            try {
              connectFour.getProfileB().addSingleplayerWin();
              connectFour.getProfileA().addSingleplayerLoose();
            } catch (IllegalNameException e) {
              e.printStackTrace();
            }
          } else {
            try {
              connectFour.getProfileB().addMultiplayerWin();
              connectFour.getProfileA().addMultiplayerLoose();
            } catch (IllegalNameException e) {
              e.printStackTrace();
            }
          }
        }
        connectFour.setGameState(GameState.FINISHED);
        return;
      }
      // Draw
      if (board.isFull()) {
        connectFour.setMatchState(MatchState.DRAW);
        connectFour.setGameState(GameState.FINISHED);
        for (Disc d : discs) {
          if (connectFour.getMode() == Mode.SINGLEPLAYER) {
            try {
              d.owner().addSingleplayerDraw();
            } catch (IllegalNameException e) {
              e.printStackTrace();
            }
          } else {
            try {
              d.owner().addMultiplayerDraw();
            } catch (IllegalNameException e) {
              e.printStackTrace();
            }
          }
        }
        return;
      }
      next();
    } catch (IllegalOperationException e) {
      logger.info("Placing disc(" + disc.color() + ") in column " + column + "...");
    }
  }

  /**
   * Get the disc of the player who is next.
   */
  private void next() {
    if (discs.getFirst().owner() == connectFour.getProfileA()) {
      connectFour.setMatchState(MatchState.PLAYER_B_IS_PLAYING);
    } else {
      connectFour.setMatchState(MatchState.PLAYER_A_IS_PLAYING);
    }
    discs.add(discs.pop());
  }

  /*
  Change Methods to production values!!!!!!!!!!!!!!!!!!!!!
   */

  /**
   * Get the players discs.
   *
   * @return the players discs
   */
  public Disc[][] getBoard() {
    return board.getDiscs();
  }

  /**
   * Get the color of the current player's disc.
   *
   * @return disc color
   */
  public String getColor() {
    return Color.RED.toString();
  }

  /**
   * Get the text which ist to be displayed in the board screen.
   *
   * @return match info
   */
  public String getText() {
    if (connectFour.getMatchState() == MatchState.DRAW) {
      return "It's a draw!";
    }
    if (connectFour.getMatchState() == MatchState.PLAYER_A_WON) {
      return discs.getFirst().owner() + " won!";
    }
    if (connectFour.getMatchState() == MatchState.PLAYER_B_WON) {
      return discs.getFirst().owner() + " won!";
    }
    if (connectFour.getMatchState() == MatchState.PLAYER_A_IS_PLAYING) {
      return discs.getFirst().owner() + " is playing.";
    }
    if (connectFour.getMatchState() == MatchState.PLAYER_B_IS_PLAYING) {
      return discs.getFirst().owner() + " is playing.";
    }
    logger.info("Something went wrong...");
    return "Ups...";
  }

  public LinkedList<Disc> getDiscs() {
    return discs;
  }
}
