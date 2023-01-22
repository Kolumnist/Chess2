package de.hhn.it.devtools.components.minesweeper.provider;

import de.hhn.it.devtools.apis.minesweeper.MinesweeperCoordinates;
import de.hhn.it.devtools.apis.minesweeper.MinesweeperService;
import de.hhn.it.devtools.apis.minesweeper.Status;
import de.hhn.it.devtools.components.chess2.ChessGame;

/**
 * Service class to get information from Frontend and return needed Values.
 *
 * @author Lara Weller, Jason Herrmann
 * @version 0.2
 */
public class LjMinesweeperService implements MinesweeperService {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(LjMinesweeperService.class);
  private int fieldSize;
  private  int bombCount;
  private int time;
  Handler handler;

  public LjMinesweeperService() {

  }

  /**
   * Constructor, which will automatically create and fill the Field.
   *
   * @param fieldSize size of the Field
   * @param time time which the Player has to complete the game
   * @param bombCount how many bombs will be created
   */
  public LjMinesweeperService(int fieldSize, int time, int bombCount) {
    logger.info("LjMinesweeperService Constructor: {} {} {}", fieldSize, time, bombCount);
    this.fieldSize = fieldSize;
    this.bombCount = bombCount;
    this.time = time;
    startGame(fieldSize, time, bombCount);
  }

  @Override
  public void startGame(int fieldSize, int time, int bombCount) {
    logger.info("startGame: {} {} {}", fieldSize, time, bombCount);
    handler = new Handler(fieldSize, bombCount);
  }

  @Override
  public void restart() {
    logger.info("restart");
    startGame(fieldSize, time, bombCount);
  }

  @Override
  public Status clickField(MinesweeperCoordinates coords) {
    logger.info("clickField: {}", coords);
    return handler.clickField(coords);
  }

  @Override
  public Status markField(MinesweeperCoordinates coords) {
    logger.info("markField: {}", coords);
    return handler.markField(coords);
  }

  public Handler getHandler() {
    logger.info("getHandler");
    return handler;
  }
}