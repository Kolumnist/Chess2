package de.hhn.it.devtools.javafx.reactiongame;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.reactiongame.AimTargetDescriptor;
import de.hhn.it.devtools.components.reactiongame.provider.RgcAimTarget;
import de.hhn.it.devtools.components.reactiongame.provider.RgcService;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class RgcAimTargetFx extends StackPane {

  private final AimTargetDescriptor descriptor;

  private final RgcService service;

  public RgcAimTargetFx(AimTargetDescriptor descriptor, RgcService service) {
    setLayoutX(descriptor.getX());
    setLayoutY(descriptor.getY());

    Circle target = new Circle(RgcAimTarget.RADIUS);
    this.descriptor = descriptor;
    this.service = service;

    target.setFocusTraversable(true);
    target.setId(descriptor.getId() + "");
    target.setFill(Color.BLUE);
    target.setOnMouseEntered(e -> {
      try {
        service.playerEnteredAimTarget(descriptor.getId());
      } catch (IllegalParameterException ex) {
        throw new RuntimeException(ex);
      }
    });
    target.setOnMouseExited(e -> service.playerLeftGameObject());

    Text text = new Text(descriptor.getKey() + "");

    getChildren().add(target);
    getChildren().add(text);
  }



}
