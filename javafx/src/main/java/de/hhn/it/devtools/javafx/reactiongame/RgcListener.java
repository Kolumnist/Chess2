package de.hhn.it.devtools.javafx.reactiongame;

import de.hhn.it.devtools.apis.reactiongame.AimTargetDescriptor;
import de.hhn.it.devtools.apis.reactiongame.GameState;
import de.hhn.it.devtools.apis.reactiongame.ObstacleDescriptor;
import de.hhn.it.devtools.apis.reactiongame.ReactiongameListener;
import de.hhn.it.devtools.components.reactiongame.provider.RgcAimTarget;
import de.hhn.it.devtools.components.reactiongame.provider.RgcRun;
import de.hhn.it.devtools.components.reactiongame.provider.RgcService;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class RgcListener implements ReactiongameListener {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(RgcListener.class);

  private final AnchorPane pane;
  private final RgcService service;


  public RgcListener(AnchorPane pane, RgcService service) {
    this.pane = pane;
    this.service = service;

    service.addCallback(this);
  }

  @Override
  public void addAimTarget(AimTargetDescriptor aimTarget) {
    logger.info("Added aim target " + aimTarget.getId());


    RgcAimTargetFx aimTargetFx = new RgcAimTargetFx(aimTarget, service);

    Platform.runLater(() -> pane.getChildren().add(aimTargetFx));
  }

  @Override
  public void removeAimTarget(int aimTargetId) {
    for (Node n :
        pane.getChildren()) {
      if (n instanceof RgcAimTargetFx) {
        if (n.getId() != null && n.getId().equals(aimTargetId + "")) {
          pane.getChildren().remove(n);
        }
        return;
      }
    }
  }

  @Override
  public void addObstacle(ObstacleDescriptor obstacle) {
    logger.info("Added obstacle " + obstacle.getId());
    Platform.runLater(() -> pane.getChildren().add(new RgcObstacleFx(obstacle, service)));

  }

  @Override
  public void removeObstacle(int obstacleId) {

  }

  @Override
  public void hitObstacle(int obstacleId) {

  }

  @Override
  public void currentLife(int numberOfLifes) {

  }

  @Override
  public void aimTargetHit(char keyPressed, int aimTargetId) {

  }

  @Override
  public void changeGameState(GameState state) {

  }

  @Override
  public void changeScore(int score) {

  }

  @Override
  public void pauseRun() {

  }

  @Override
  public void continueRun() {

  }

  @Override
  public void gameOver() {

  }
}
