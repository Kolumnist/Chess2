package de.hhn.it.devtools.javafx.controllers.connectfour;

import de.hhn.it.devtools.apis.connectfour.enums.MatchState;
import de.hhn.it.devtools.apis.connectfour.exceptions.IllegalOperationException;
import de.hhn.it.devtools.apis.connectfour.interfaces.IConnectFourListener;
import de.hhn.it.devtools.components.connectfour.provider.ConnectFour;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.Instance;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.board.Tile;
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
  private final ConnectFour instance = Instance.getInstance();

  @FXML
  Pane root;
  @FXML
  GridPane board;
  @FXML
  TextArea output;

  @FXML
  void onQuit() {
    logger.info("onQuit: no params");
    file.saveProfileData();
    SceneChanger.changeScene(root, "/fxml/ConnectFour.fxml");
  }

  @FXML
  void onRestart() {
    logger.info("onRestart: no params");
    instance.restart();
    initialize(null, null);
  }

  public void lock() {
    logger.info("onLock: no params");
    board.setDisable(true);
  }

  public void unlock() {
    logger.info("onUnlock: no params");
    board.setDisable(false);
  }

  @Override
  public void update(MatchState matchState, String description, int column, int row, String color) {
    logger.info(
        "onUpdate: matchState = {}, description = {}, column = {}, row = {}, color = {}",
        matchState, description, column, row, color
    );
    lock();
    output.setText(description);
    tiles[column][row].setColor(Color.valueOf(color));
    if (matchState == MatchState.COMPUTER_IS_PLAYING) {
      timer.schedule(new TimerTask() {
        @Override
        public void run() {
          while (true) {
            try {
              Instance.getInstance().placeDiscInColumn((int) (Math.random() * 6));
              unlock();
              break;
            } catch (IllegalOperationException ignore) {
            }
          }
        }
      }, 1000);
    } else {
      unlock();
    }
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
}
