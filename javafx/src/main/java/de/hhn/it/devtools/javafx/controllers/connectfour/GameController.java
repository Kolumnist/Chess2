package de.hhn.it.devtools.javafx.controllers.connectfour;

import de.hhn.it.devtools.apis.connectfour.enums.GameState;
import de.hhn.it.devtools.apis.connectfour.enums.MatchState;
import de.hhn.it.devtools.apis.connectfour.interfaces.IConnectFourListener;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.Instance;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.board.Tile;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.computer.Computer;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.io.FileIO;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * Controller for the game screen.
 */
public class GameController implements Initializable, IConnectFourListener {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(GameController.class);

  private final FileIO file = new FileIO();

  private final Timer timer = new Timer(true);

  private final Tile[][] tiles = new Tile[7][6];


  @FXML
  Pane root;
  @FXML
  GridPane board;
  @FXML
  TextArea output;

  @FXML
  void onQuit() {
    logger.info("Quitting...");
    file.saveProfileData();
    SceneChanger.changeScene(root, "/fxml/ConnectFour.fxml");
  }

  @FXML
  void onRestart() {
    logger.info("Restart game");
    Instance.getInstance().initializeGame();
    initialize(null, null);
  }

  public void update() {
    lock();
    logger.info("update board");
    updateText();
    updateTiles();
    if (Instance.getInstance().getGameState() == GameState.FINISHED
        || Instance.getInstance().getGameState() == GameState.CANCELED) {
      return;
    }
    if (Instance.getInstance().computerIsNext()) {
      logger.info("Computer is next");
      Instance.getInstance().play();
      timer.schedule(new TimerTask() {
        @Override
        public void run() {
          update();
        }
      }, 500);
    } else {
      unlock();
    }
  }

  public void lock() {
    board.setDisable(true);
  }

  public void unlock() {
    board.setDisable(false);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    logger.info("initialize: location = {}, resources = {}", location, resources);
    lock();
    for (int column = 0; column < 7; column++) {
      for (int row = 0; row < 6; row++) {
        Tile tile = new Tile(column, row, this);
        board.add(tile, column, row);
        tiles[column][row] = tile;
      }
    }
  }

  @Override
  public void update(MatchState matchState, String description, int column, int row, String color) {
    output.setText(description);
    tiles[column][row].setColor(Color.valueOf(color));
    if(matchState == MatchState.COMPUTER_IS_PLAYING){

    }
  }
}
