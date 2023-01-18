package de.hhn.it.devtools.javafx.reactiongame;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.reactiongame.ObstacleDescriptor;
import de.hhn.it.devtools.components.reactiongame.provider.RgcService;
import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RgcObstacleFx extends Rectangle {

  public RgcObstacleFx(ObstacleDescriptor descriptor, RgcService service) {

    setId(descriptor.getId() + "");
    setX(descriptor.getX1());
    setY(descriptor.getY1());

    setWidth(descriptor.getX2() - descriptor.getX1());

    setHeight(descriptor.getY2() - descriptor.getY1());

    setFill(Color.BLACK);


    setCursor(Cursor.CROSSHAIR);


    setOnMouseEntered(e -> {
      try {
        service.playerEnteredObstacle(descriptor.getId());
      } catch (IllegalParameterException ex) {
        throw new RuntimeException(ex);
      }
    });

    setOnMouseExited(e -> service.playerLeftGameObject());
  }

}
