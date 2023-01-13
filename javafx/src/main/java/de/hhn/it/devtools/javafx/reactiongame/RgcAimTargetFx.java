package de.hhn.it.devtools.javafx.reactiongame;

import de.hhn.it.devtools.apis.reactiongame.AimTargetDescriptor;
import de.hhn.it.devtools.components.reactiongame.provider.RgcAimTarget;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public class RgcAimTargetFx extends Circle {

  private final AimTargetDescriptor descriptor;

  private final AnchorPane pane;

  public RgcAimTargetFx(AimTargetDescriptor descriptor, AnchorPane pane) {
    this.descriptor = descriptor;
    this.pane = pane;

    setRadius(RgcAimTarget.RADIUS);

    setId(String.valueOf(descriptor.getId()));

  }



}
