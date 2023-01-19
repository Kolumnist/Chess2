package de.hhn.it.devtools.javafx.controllers.connectfour;

import de.hhn.it.devtools.apis.connectfour.enums.GameState;
import de.hhn.it.devtools.components.connectfour.provider.helper.Disc;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.FileIO;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.Instance;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.Tile;
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
public class GameController implements Initializable {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(GameController.class);

  private final Tile[][] tiles = new Tile[7][6];

  private FileIO file = new FileIO();

  @FXML
  Pane root;
  @FXML
  GridPane board;
  @FXML
  TextArea output;

  Timer t = new Timer(true);

  @FXML
  void onQuit() {
    logger.info("Quitting...");
    file.saveProfileData();
    SceneChanger.changeScene(root, "/fxml/ConnectFour.fxml");
  }

  @FXML
  void onRestart() {
    logger.info("Restart game");
    Instance.getConnectFour().startGame();
    initialize(null, null);
  }

  public void updateTiles() {
    Disc[][] b = Instance.getConnectFour().getDiscs();
    // column
    for (int i = 0; i < b.length; i++) {
      // row
      for (int j = 0; j < b[i].length; j++) {
        if (b[i][j] != null) {
          tiles[i][5 - j].setColor(Color.valueOf(b[i][j].color().toString()));
        }
      }
    }
  }

  public void update() {
    lock();
    logger.info("update board");
    updateText();
    updateTiles();
    if (Instance.getConnectFour().getGameState() == GameState.FINISHED
        || Instance.getConnectFour().getGameState() == GameState.CANCELED) {
      return;
    }
    if (Instance.getConnectFour().computerIsNext()) {
      logger.info("Computer is next");
      Instance.getConnectFour().play();
      t.schedule(new TimerTask() {
        @Override
        public void run() {
          update();
        }
      }, 500);
    } else {
      unlock();
    }
  }

  public void updateText() {
    output.setText(Instance.getConnectFour().getText());
  }

  public void lock() {
    board.setDisable(true);
  }

  public void unlock() {
    board.setDisable(false);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    lock();
    logger.info("Initializing...");
    for (int column = 0; column < 7; column++) {
      for (int row = 0; row < 6; row++) {
        Tile tile = new Tile(column, row, this);
        board.add(tile, column, row);
        tiles[column][row] = tile;
      }
    }
    Instance.getConnectFour().startGame();
    if (Instance.getConnectFour().computerIsNext()) {
      logger.info("Computer begins");
      Instance.getConnectFour().play();
      t.schedule(new TimerTask() {
        @Override
        public void run() {
          update();
        }
      }, 500);
    } else {
      update();
    }
  }
}
