package de.hhn.it.devtools.javafx.controllers.connectfour.helper.board;

import de.hhn.it.devtools.apis.connectfour.exceptions.IllegalOperationException;
import de.hhn.it.devtools.apis.connectfour.interfaces.ConnectFourInterface;
import de.hhn.it.devtools.javafx.controllers.connectfour.GameController;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.Instance;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * This class models a tile of the board.
 */
public class Tile extends Pane {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(Tile.class);

  /**
   * Create a new Tile.
   *
   * @param column     the column of the tile
   * @param row        the row of the tile
   * @param controller the game controller
   */
  public Tile(int column, int row, GameController controller) {
    logger.info("Constructor - {}, {}, {}", column, row, controller);
    setMinSize(80, 80);
    setMaxSize(80, 80);
    setBorder(
        new Border(
            new BorderStroke(
                Color.BLACK,
                BorderStrokeStyle.SOLID,
                new CornerRadii(90),
                new BorderWidths(3),
                new Insets(5, 5, 5, 5)
            )
        )
    );
    setColor(Color.WHITE);
    setCursor(Cursor.HAND);
    setOnMouseClicked(event -> {
      controller.lock();
      try {
        ConnectFourInterface instance = Instance.getInstance();
        instance.placeDiscInColumn(column);
      } catch (IllegalOperationException e) {
        controller.unlock();
      }
    });
  }

  /**
   * Set the background color of the tile.
   *
   * @param color color of the disc
   */
  public void setColor(Color color) {
    logger.info("setColor: color = {}", color);
    setBackground(new Background(new BackgroundFill(
        color,
        new CornerRadii(90),
        getInsets()
    )));
  }
}
