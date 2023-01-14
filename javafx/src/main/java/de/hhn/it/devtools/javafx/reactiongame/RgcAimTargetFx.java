package de.hhn.it.devtools.javafx.reactiongame;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.reactiongame.AimTargetDescriptor;
import de.hhn.it.devtools.components.reactiongame.provider.RgcAimTarget;
import de.hhn.it.devtools.components.reactiongame.provider.RgcService;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class RgcAimTargetFx extends Circle {

  private final AimTargetDescriptor descriptor;

  private final RgcService service;

  public RgcAimTargetFx(AimTargetDescriptor descriptor, RgcService service) {
    super(descriptor.getX(), descriptor.getY(), RgcAimTarget.RADIUS);
    this.descriptor = descriptor;
    this.service = service;

    setId(descriptor.getId() + "");
    setFill(Color.BLUE);


    setOnMouseEntered(e -> {
      try {
        service.playerEnteredAimTarget(descriptor.getId());
      } catch (IllegalParameterException ex) {
        throw new RuntimeException(ex);
      }
    });

    setOnMouseExited(e -> service.playerLeftGameObject());
  }



}
