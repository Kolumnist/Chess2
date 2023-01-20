package de.hhn.it.devtools.javafx.controllers.connectfour;

import de.hhn.it.devtools.apis.connectfour.interfaces.IConnectFourListener;
import de.hhn.it.devtools.components.connectfour.provider.ConnectFour;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.Instance;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.board.Tile;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.io.FileIo;
import java.net.URL;
import java.util.ResourceBundle;
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

  private final FileIo file = new FileIo();
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

  @Override
  public String getName() {
    logger.info("getName: no params");
    return this.getClass().getSimpleName();
  }

  @Override
  public void updateDescription(String description) {
    logger.info("setDescription: description = {}", description);
    output.setText(description);
  }

  @Override
  public void updateTile(int column, int row, String color) {
    logger.info("updateTile: column = {}, row = {}, color = {}", color, row, color);
    tiles[column][row].setColor(Color.valueOf(color));
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
    // Since we only use this as a listener, the callbacks map can be cleared.
    instance.removeAllCallbacks();
    instance.addCallback(this);
  }
}
